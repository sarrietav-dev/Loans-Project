package logic.pdf;

import logic.company_members.Employee;
import logic.loan_classes.Loan;

import java.util.Date;

/**
 * It stores the info of a payment to print it on a receipt.
 */
public class InformationPack {
    private final Loan loan;
    private final Date paymentDate;
    private final Employee collector;
    private final double moneyToCapital;
    private final double moneyToInterests;
    private final double loanBalance;
    

    public InformationPack(Loan loan, Date paymentDate, Employee collector, double moneyToCapital,
                           double moneyToInterests) {
        this.loan = loan;
        this.paymentDate = paymentDate;
        this.collector = collector;
        this.moneyToCapital = moneyToCapital;
        this.moneyToInterests = moneyToInterests;
        this.loanBalance = loan.getBalance();
    }

    public Loan getLoan() {
        return loan;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public Employee getCollector() {
        return collector;
    }

    public double getMoneyToCapital() {
        return moneyToCapital;
    }

    public double getMoneyToInterests() {
        return moneyToInterests;
    }

    public double getLoanBalance() {
        return loanBalance;
    }
}
