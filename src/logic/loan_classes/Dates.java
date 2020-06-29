package logic.loan_classes;

import logic.exceptions.DateOutOfLimitException;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Dates implements Serializable, PaymentMethods {
    private Calendar authorizationDate;
    private Calendar deliveryDate;
    // FIXME: 28/06/20 The keys of this HashMap appear unordered. Order them according to their month.
    //  Don't worry about the values, all are equal.
    private final HashMap<String, PaymentStatus> paymentDates = new HashMap<>();

    public Dates(String date) {
        setAuthorizationDate(date);
        setDeliveryDate();
        setPaymentDates();
    }

    @Override
    public boolean arePaymentsPaid() {
        for (PaymentStatus status : paymentDates.values())
            if (!status.isPaid())
                return false;
        return true;
    }

    @Override
    public void pay() {
        for (Map.Entry<String, PaymentStatus> entry : paymentDates.entrySet()) {
            if (!entry.getValue().isPaid()) {
                entry.getValue().pay();
                if (isDelayed(entry.getKey(), entry.getValue()))
                    entry.getValue().setDelayed(true);
            }
        }
    }

    @Override
    public void pay(Calendar calendar) {
        for (Map.Entry<String, PaymentStatus> entry : paymentDates.entrySet()) {
            if (!entry.getValue().isPaid()) {
                entry.getValue().pay(calendar);
                if (isDelayed(entry.getKey(), entry.getValue()))
                    entry.getValue().setDelayed(true);
            }
        }
    }

    private boolean isDelayed(String paymentDate, PaymentStatus status) {
        return CalendarFormatter.format(paymentDate).getTime().equals(CalendarFormatter.format(status.getPaymentDate()).getTime());
    }

    private void setAuthorizationDate(String authorizationDate) {
        Calendar authDate = CalendarFormatter.format(authorizationDate);
        if (isAuthDateWithinLimits(authDate))
            this.authorizationDate = authDate;
        else
            throw new DateOutOfLimitException("Date out of limits! Only between the first 20 days of the month!");
    }

    private boolean isAuthDateWithinLimits(Calendar tempCalendar) {
        return tempCalendar.get(Calendar.DAY_OF_MONTH) <= 20;
    }

    private void setDeliveryDate() {
        deliveryDate = Calendar.getInstance();
        deliveryDate.setTime(authorizationDate.getTime());
        deliveryDate.add(Calendar.DATE, 7);
    }

    private void setPaymentDates() {
        int days = 30;
        Calendar paymentDate = Calendar.getInstance();
        paymentDate.setTime(deliveryDate.getTime());
        for (int i = 0; i < 6; i++) {
            paymentDate.add(Calendar.DATE, days);
            paymentDates.put(CalendarFormatter.format(paymentDate.getTime()), new PaymentStatus());
        }
    }

    public String getAuthorizationDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(authorizationDate.getTime());
    }

    public Calendar getDeliveryDate() {
        return deliveryDate;
    }

    @Override
    public String toString() {
        return "Dates{" +
                "authorizationDate=" + CalendarFormatter.format(authorizationDate.getTime())  +
                ", deliveryDate=" + CalendarFormatter.format(deliveryDate.getTime())  +
                ", paymentDates=" + paymentDatesToString() +
                '}';
    }

    private String paymentDatesToString() {
        StringBuilder toString = new StringBuilder();
        for (Map.Entry<String, PaymentStatus> entry : paymentDates.entrySet()) {
            String tempString = String.format("%s:%s, ",
                    entry.getKey(),
                    entry.getValue().toString());
            toString.append(tempString);
        }
        return toString.toString();
    }
}
