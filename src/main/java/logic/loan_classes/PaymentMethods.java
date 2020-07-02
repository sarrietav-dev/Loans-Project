package logic.loan_classes;

import java.util.Date;

public interface PaymentMethods {
    boolean arePaymentsPaid();
    void pay();
    void pay(Date date);
    void payAll();
    void payAll(Date date);
}
