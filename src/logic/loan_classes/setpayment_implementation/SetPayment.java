package logic.loan_classes.setpayment_implementation;

import logic.loan_classes.Dates;
import logic.loan_classes.PaymentStatus;

import java.util.Date;

public class SetPayment extends Dates {
    SetPaymentOptions option;

    public SetPayment(SetPaymentOptions option) {
        this.option = option;
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
                return (actual, toBeModified) -> paymentDates.get(toBeModified).pay(actual);
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
