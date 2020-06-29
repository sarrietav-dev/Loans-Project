package logic.loan_classes;

import java.io.Serializable;
import java.util.Objects;

public class Loan implements Serializable {
    private int loanNumber;
    private double amount;
    private Borrower borrower;
    private final Dates dates;
    private boolean isPaid = false;
    private double installmentsPrice;

    public Loan(double amount, String authDate) {
        this.amount = amount;
        dates = new Dates(authDate);
    }

    public Loan(double amount, Borrower borrower, String authDate) {
        setAmount(amount);
        dates = new Dates(authDate);
        this.borrower = borrower;
        installmentsPrice = amount / 6;
    }

    private Loan(int loanNumber, double amount, Borrower borrower, Dates dates, boolean isPaid) {
        this.loanNumber = loanNumber;
        setAmount(amount);
        this.borrower = borrower;
        this.dates = dates;
        this.isPaid = isPaid;
        installmentsPrice = amount / 6;
    }

    private void setAmount(double amount) {
        if (amount > 0)
            this.amount = amount;
        else
            throw new IllegalArgumentException("Invalid amount. It should be greater than 0!");
    }

    public Borrower getBorrower() {
        return borrower;
    }

    public void setBorrower(Borrower borrower) {
        this.borrower = borrower;
    }

    public Dates getDates() {
        return dates;
    }

    public int getLoanNumber() {
        return loanNumber;
    }

    public void setLoanNumber(int loanNumber) {
        this.loanNumber = loanNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Loan loan = (Loan) o;
        return loanNumber == loan.loanNumber &&
                Double.compare(loan.amount, amount) == 0 &&
                isPaid == loan.isPaid &&
                borrower.equals(loan.borrower) &&
                dates.equals(loan.dates);
    }

    @Override
    public String toString() {
        return "Loan{" +
                "loanNumber=" + loanNumber +
                ", amount=" + amount +
                (borrower == null ? "NULL" : ", borrower=" + borrower.toString()) +
                ", dates=" + dates.toString() +
                ", isPaid=" + isPaid +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(loanNumber, amount, borrower, dates, isPaid);
    }
}
