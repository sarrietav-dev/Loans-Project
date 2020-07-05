package logic.loan_classes;

import java.util.Date;

public class PaymentManager {
    private final Loan loan;
    private final Dates loanDates;
    private final Date paymentDate;

    private double moneyToCapital;
    private double moneyToInterests;

    public PaymentManager(Loan loan, Date paymentDate) {
        this.loan = loan;
        loanDates = loan.getDates();
        this.paymentDate = paymentDate;
    }

    public void pay() {
        getDistributeMoney(DISTRIBUTE_MONEY_OPTION.PAY).distributeMoney();
        setInstallmentPaid();
    }

    private void setInstallmentPaid() {
        loanDates.getPaymentDates().get(getLastNotPaidDate()).pay(paymentDate);
    }

    public void payAll() {
        getDistributeMoney(DISTRIBUTE_MONEY_OPTION.PAY_ALL).distributeMoney();
        setAllInstallmentsPaid();
    }

    private void setAllInstallmentsPaid() {
        for (Date paymentDate : loanDates.getDatesSorted())
            if (loanDates.getPaymentDates().get(paymentDate).isNotPaid())
                loanDates.getPaymentDates().get(paymentDate).pay(paymentDate);
    }

    public double getMoneyToInterests() {
        return moneyToInterests;
    }

    public double getMoneyToCapital() {
        return moneyToCapital;
    }

    private DistributeMoneyInterface getDistributeMoney(DISTRIBUTE_MONEY_OPTION option) {
        switch (option) {
            case PAY -> {
                return () -> {
                    double interests = getInterests();

                    moneyToInterests = interests;
                    moneyToCapital = loan.getInstallmentsPrice() - interests;

                    loan.sumToCapital(moneyToCapital);
                    loan.sumToInterests(moneyToInterests);
                };
            }
            case PAY_ALL -> {
                return () -> {
                    int notPaidInstallments = getNotPaidInstallments();

                    moneyToCapital = loan.getInstallmentsPrice() * notPaidInstallments;
                    moneyToInterests = moneyToCapital * .05;

                    loan.sumToInterests(moneyToInterests);
                    loan.sumToCapital(moneyToCapital);
                };
            }
            default -> throw new IllegalStateException("Unexpected value: " + option);
        }
    }

    private double getInterests() {
        if (isDelayed()) {
            long daysDelayed = (getLastNotPaidDate().getTime() - paymentDate.getTime()) / (1000*60*60*24);
            return loan.getBalance() * ((daysDelayed * .05) + .15);
        }
        else
            return loan.getBalance() * .15;
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

    private enum DISTRIBUTE_MONEY_OPTION {
        PAY,
        PAY_ALL
    }
}

@FunctionalInterface
interface DistributeMoneyInterface {
    void distributeMoney();
}


