package logic.loan_classes;

import java.util.Calendar;
import java.util.HashMap;

public interface PaymentMethods {
    boolean arePaymentsPaid();
    void pay();
    void pay(Calendar calendar);
    // TODO: 28/06/20 Declare payAll()
}
