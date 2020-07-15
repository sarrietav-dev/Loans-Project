package logic.loan_classes;

import java.io.Serializable;
import java.util.Date;

/**
 * This class is the concept of a Loan. <br>
 * It belongs to a client, and it has a unique ID, the amount borrowed, <br>
 *     and the dates in which each installment needs to be paid.
 *     It also manages the capital and the interest collected by each time the client pays.
 */
public class Loan implements Serializable {
    private int loanNumber;
    private double amount;
    private double balance;
    private Client client;
    private final Dates dates;
    private boolean isPaid = false;
    private double capital;
    private double interestCollected;

    public Loan(double amount, String authDate) {
        this.amount = amount;
        balance = amount;
        dates = new Dates(authDate);
    }

    public Loan(double amount, Client client, String authDate) {
        setAmount(amount);
        dates = new Dates(authDate);
        this.client = client;
        balance = amount;
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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
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

    /**
     * Calculates and returns the price of each installment (monthly).
     * It uses the <a href="https://en.wikipedia.org/wiki/Equated_monthly_installment">EMI formula.</a>
     * @return A double with the monthly price.
     */
    public double getInstallmentsPrice() {
        final double AMOUNT_BORROWED = amount;
        final int FINANCING_PERIOD = 6;
        final double INTEREST_RATE = 0.15;

        return (INTEREST_RATE * AMOUNT_BORROWED) / (1 - (Math.pow(1 + INTEREST_RATE, -1 * FINANCING_PERIOD)));
    }

    public void sumToInterests(double amount) {
        interestCollected += amount;
    }

    public void sumToCapital(double amount) {
        capital += amount;
        subtractToBalance(amount);
        setPaid();
    }

    public double getInterestsCollected() {
        return interestCollected;
    }

    public double getBalance() {
        return balance;
    }

    private void subtractToBalance(double amount) {
        balance -= amount;
    }

    /**
     * Looks if any installment is delayed according to the machine's date.
     * @return If the loan is delayed or not.
     */
    public boolean isDelayed() {
        return dates.getDatesSorted().stream()
                .filter(paymentDate -> dates.getPaymentDates().get(paymentDate).isNotPaid())
                .anyMatch(paymentDate -> paymentDate.before(new Date()));
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid() {
        if (capital >= amount)
            isPaid = true;
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
                (client == null ? ", NULL" : ", client=" + client.toString()) +
                ", dates=" + dates +
                ", isPaid=" + isPaid +
                ", capital=" + capital +
                ", interestCollected=" + interestCollected +
                '}';
    }

}
