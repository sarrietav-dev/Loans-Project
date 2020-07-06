package logic.loan_classes;

import java.io.Serializable;
import java.util.Date;

public class PaymentStatus implements Serializable {
    private boolean isPaid;
    private Date paymentDate;

    public PaymentStatus() {
        isPaid = false;
    }

    public void pay(Date paymentDate) {
        isPaid = true;
        this.paymentDate = paymentDate;
    }

    public boolean isNotPaid() {
        return !isPaid;
    }

    @Override
    public String toString() {
        return "PaymentStatus{" +
                "isPaid=" + isPaid +
                ", paymentDate=" + (isPaid ? paymentDate : "NOT PAID YET") +
                '}';
    }
}
