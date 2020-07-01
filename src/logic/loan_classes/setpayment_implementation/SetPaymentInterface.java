package logic.loan_classes.setpayment_implementation;

import java.util.Date;

@FunctionalInterface
public interface SetPaymentInterface {
    void pay(Date expected, Date actual);
}
