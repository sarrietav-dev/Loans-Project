package logic.loan_classes;

import logic.FetchInformation;
import logic.IDGetterSetter;

import java.util.Objects;

public class Loan implements IDGetterSetter, FetchInformation {
    private int loanNumber;
    private double amount;
    private final Borrower borrower;
    private final Dates dates;
    private boolean isPaid = false;

    public Loan(double amount, Borrower borrower, String authDate) {
        setAmount(amount);
        dates = new Dates(authDate);
        this.borrower = borrower;
    }

    public Loan(int loanNumber, double amount, Borrower borrower, Dates dates, boolean isPaid) {
        setID(loanNumber);
        this.amount = amount;
        this.borrower = borrower;
        this.dates = dates;
        this.isPaid = isPaid;
    }

    private void setAmount(double amount) {
        if (amount > 0)
            this.amount = amount;
        else
            throw new IllegalArgumentException("Invalid amount. It should be greater than 0!");
    }

    public Object[] getInfo() {
        String[] loanInfo = new String[] {
                String.valueOf(loanNumber),
                String.valueOf(amount),
                String.valueOf(borrower.getId()),
                dates.getAuthorizationDate(),
                String.valueOf(isPaid)
        };
        return new Object[] {
                loanInfo,
                borrower,
        };
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

    @Override
    public void setID(int id) {
        this.loanNumber = id;
    }

    @Override
    public int getID() {
        return loanNumber;
    }
}
