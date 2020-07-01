package logic.loan_classes;

import java.io.Serializable;
import java.util.Date;

public class PaymentStatus implements Serializable {
    private boolean isPaid;
    private Date paymentDate;
    private boolean isDelayed;

    public PaymentStatus() {
        isPaid = false;
    }

    public void pay(Date paymentDate) {
        isPaid = true;
        this.paymentDate = paymentDate;
    }

    public void setDelayed(boolean delayed) {
        isDelayed = delayed;
    }

    public boolean isNotPaid() {
        return !isPaid;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public boolean isDelayed() {
        return isDelayed;
    }

    @Override
    public String toString() {
        return "PaymentStatus{" +
                "isPaid=" + isPaid +
                ", paymentDate=" + (isPaid ? paymentDate : "NOT PAID YET") +
                ", isDelayed=" + isDelayed +
                '}';
    }
}
