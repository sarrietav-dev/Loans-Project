package logic.loan_classes;

import java.io.Serializable;
import java.util.Calendar;

public class PaymentStatus implements Serializable {
    private boolean isPaid;
    private Calendar paymentDate;
    private boolean isDelayed;

    public PaymentStatus() {
        isPaid = false;
    }

    public void pay() {

    }

    public void pay(Calendar paymentDate) {

    }

    public boolean isPaid() {
        return isPaid;
    }

    public Calendar getPaymentDate() {
        return paymentDate;
    }

    public boolean isDelayed() {
        return isDelayed;
    }

    @Override
    public String toString() {
        return "PaymentStatus{" +
                "isPaid=" + isPaid +
                ", paymentDate=" + paymentDate +
                ", isDelayed=" + isDelayed +
                '}';
    }
}
