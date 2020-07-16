package logic.loan_classes;

import logic.file_management.loan_crud.UpdateLoan;

import java.util.Date;

public class PaymentManager {
    private final Loan loan;
    private final Dates loanDates;
    private final Date paymentDate;
    private double moneyToCapital;
    private double moneyToInterests;
    private double moneyToEmployee;

    public PaymentManager(Loan loan, Date paymentDate) {
        this.loan = loan;
        loanDates = loan.getDates();
        this.paymentDate = paymentDate;
    }

    /**
     * It sets the last unpaid installment to paid, and calculates the money distributions.
     */
    public void pay() {
        getDistributeMoney(DISTRIBUTE_MONEY_OPTION.PAY).distributeMoney();
        setInstallmentPaid();
        UpdateLoan.update(loan);
    }

    private void setInstallmentPaid() {
        loanDates.getPaymentDates().get(getLastNotPaidDate()).pay(paymentDate);
    }

    /**
     * It pays all the loan's installment, but charges only the ones which were unpaid.
     */
    public void payAll() {
        getDistributeMoney(DISTRIBUTE_MONEY_OPTION.PAY_ALL).distributeMoney();
        setAllInstallmentsPaid();
        UpdateLoan.update(loan);
    }

    private void setAllInstallmentsPaid() {
        for (Date paymentDate : loanDates.getDatesSorted())
            if (loanDates.getPaymentDates().get(paymentDate).isNotPaid())
                loanDates.getPaymentDates().get(paymentDate).pay(this.paymentDate);
    }

    /**
     * Every time it pays an installment, the company collects some interests.<br>
     *     <ul>
     *     <li>If the payment isn't delayed, it charges 15%. <br></li>
     *     <li>If the payment is delayed, it changes 15% plus a 5% for each day that has been delayed.<br></li>
     *     <li>If the payment is in advance, it charges 5% of the total paid.<br></li>
     *     </ul>
     * @return The interests collected in the actual payment.
     */
    public double getMoneyToInterests() {
        return moneyToInterests;
    }

    /**
     * Capital is the money that will go to the loan itself. <br>
     *     It takes the installment price, and the interests are subtracted. <br>
     *         The bigger the interest, the less money will be added to the capital.
     * @return The money that was added to the capital in the actual payment.
     */
    public double getMoneyToCapital() {
        return moneyToCapital;
    }

    private DistributeMoneyInterface getDistributeMoney(DISTRIBUTE_MONEY_OPTION option) {
        return switch (option) {
            case PAY -> () -> {
                double interests = getInterests();

                moneyToInterests = (interests - (loan.getBalance() * 0.01));
                moneyToCapital = loan.getInstallmentsPrice() - interests;
                moneyToEmployee = loan.getBalance() * 0.01;

                loan.sumToCapital(moneyToCapital);
                loan.sumToInterests(moneyToInterests);
            };
            case PAY_ALL -> () -> {
                int notPaidInstallments = getNotPaidInstallments();

                moneyToCapital = loan.getInstallmentsPrice() * notPaidInstallments;
                moneyToInterests = moneyToCapital * 0.04;
                moneyToEmployee = moneyToCapital * 0.01;

                loan.sumToInterests(moneyToInterests);
                loan.sumToCapital(moneyToCapital);
            };
        };
    }

    private double getInterests() {
        if (isDelayed()) {
            long daysDelayed = (getLastNotPaidDate().getTime() - paymentDate.getTime()) / (1000*60*60*24);
            return loan.getBalance() * ((daysDelayed * 0.05) + 0.15);
        }
        else
            return loan.getBalance() * 0.15;
    }

    private boolean isDelayed() {
        return getLastNotPaidDate().before(paymentDate);
    }

    private Date getLastNotPaidDate() {
        for (Date paymentDate : loanDates.getDatesSorted())
            if (loanDates.getPaymentDates().get(paymentDate).isNotPaid())
                return paymentDate;
        throw new NullPointerException();
    }

    private int getNotPaidInstallments() {
        int notPaidInstallmentsCounter = 0;
        for (Date paymentDate : loanDates.getDatesSorted())
            if (loanDates.getPaymentDates().get(paymentDate).isNotPaid())
                notPaidInstallmentsCounter++;
        return notPaidInstallmentsCounter;
    }

    public double getMoneyToEmployee() {
        return moneyToEmployee;
    }

    private enum DISTRIBUTE_MONEY_OPTION {
        PAY,
        PAY_ALL
    }
}

@FunctionalInterface
interface DistributeMoneyInterface {
    void distributeMoney();
}


