package logic.loan_classes;

import java.io.Serializable;
import java.util.Date;

/**
 * This class exists to contain the info of each Installment:
 * <ul>
 *     <li>If it's paid.</li>
 *     <li>When it was paid</li>
 * </ul>
 */
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
    
    public Date getPaymentDate() {
	    return paymentDate;
    }

    @Override
    public String toString() {
        return "PaymentStatus{" +
                "isPaid=" + isPaid +
                ", paymentDate=" + (isPaid ? paymentDate : "NOT PAID YET") +
                '}';
    }
}
