package logic.loan_classes.setpayment_implementation;

import logic.loan_classes.PaymentStatus;

import java.util.Date;
import java.util.HashMap;

public class SetPayment {
    SetPaymentOptions option;
    protected final HashMap<Date, PaymentStatus> paymentDates;

    public SetPayment(SetPaymentOptions option, HashMap<Date, PaymentStatus> paymentDates) {
        this.option = option;
        this.paymentDates = paymentDates;
    }

    public SetPaymentInterface getSetter() {
        switch (option) {
            case PAY -> {
                return  (expected, actual) -> {
                    paymentDates.get(expected).pay(actual);
                    setDelayed(expected);
                };
            }
            case PAY_ALL -> {
                return (expected, actual) -> paymentDates.get(expected).pay(actual);
            }
            default -> throw new IllegalStateException("Unexpected value: " + option);
        }
    }

    private void setDelayed(Date expectedPaymentDate) {
        if (isDelayed(expectedPaymentDate))
            paymentDates.get(expectedPaymentDate).setDelayed(true);
    }

    private boolean isDelayed(Date expectedPaymentDate) {
        PaymentStatus status = paymentDates.get(expectedPaymentDate);
        return expectedPaymentDate.before(status.getPaymentDate());
    }
}
