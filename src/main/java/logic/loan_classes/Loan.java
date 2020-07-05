package logic.loan_classes;

import java.io.Serializable;

public class Loan implements Serializable {
    private int loanNumber;
    private double amount;
    private Borrower borrower;
    private final Dates dates;
    private boolean isPaid = false;
    private double capital;
    private double interestCollected;

    public Loan(double amount, String authDate) {
        this.amount = amount;
        dates = new Dates(authDate);
    }

    public Loan(double amount, Borrower borrower, String authDate) {
        setAmount(amount);
        dates = new Dates(authDate);
        this.borrower = borrower;
    }

    private void setAmount(double amount) {
        if (amount > 0)
            this.amount = amount;
        else
            throw new IllegalArgumentException("Invalid amount. It should be greater than 0!");
    }

    public double getAmount() {
        return amount;
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

    public double getInstallmentsPrice() {
        return amount / 6;
    }

    public void sumToInterests(double amount) {
        interestCollected += amount;
    }

    public void sumToCapital(double amount) {
        capital += amount;
        setPaid();
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid() {
        if (capital == amount)
            isPaid = true;
    }

    public double getCapital() {
        return capital;
    }

    public double getInterestCollected() {
        return interestCollected;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Loan loan = (Loan) o;
        return loanNumber == loan.loanNumber;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "loanNumber=" + loanNumber +
                ", amount=" + amount +
                (borrower == null ? "NULL" : ", borrower=" + borrower.toString()) +
                ", dates=" + dates +
                ", isPaid=" + isPaid +
                ", capital=" + capital +
                ", interestCollected=" + interestCollected +
                '}';
    }

}
