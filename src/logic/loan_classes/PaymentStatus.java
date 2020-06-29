package logic.loan_classes;

import java.io.Serializable;
import java.util.Calendar;

public class PaymentStatus implements Serializable {
    private boolean isPaid;
    private String paymentDate;
    private boolean isDelayed;

    public PaymentStatus() {
        isPaid = false;
    }

    public void pay() {
        isPaid = true;
        paymentDate = CalendarFormatter.format(Calendar.getInstance().getTime());
    }

    public void pay(Calendar paymentDate) {
        isPaid = true;
        this.paymentDate = CalendarFormatter.format(paymentDate.getTime());
    }

    public void setDelayed(boolean delayed) {
        isDelayed = delayed;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public boolean isDelayed() {
        return isDelayed;
    }

    @Override
    public String toString() {
        return "PaymentStatus{" +
                "isPaid=" + isPaid +
                ", paymentDate=" + (isPaid ? paymentDate: "NOT PAID YET") +
                ", isDelayed=" + isDelayed +
                '}';
    }
}
