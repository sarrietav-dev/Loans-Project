package logic.loan_classes;

import java.io.Serializable;
import java.util.Objects;

public class Loan implements Serializable {
    private int loanNumber;
    private double amount;
    private final Borrower borrower;
    private final Dates dates;
    private boolean isPaid = false;
    private final double installmentsPrice;

    public Loan(double amount, Borrower borrower, String authDate) {
        setAmount(amount);
        dates = new Dates(authDate);
        this.borrower = borrower;
        installmentsPrice = amount / 6;
    }

    public Loan(int loanNumber, double amount, Borrower borrower, Dates dates, boolean isPaid) {
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
                ", borrower=" + borrower.toString() +
                ", dates=" + dates.toString() +
                ", isPaid=" + isPaid +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(loanNumber, amount, borrower, dates, isPaid);
    }
}
