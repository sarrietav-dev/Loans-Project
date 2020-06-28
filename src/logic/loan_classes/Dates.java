package logic.loan_classes;

import logic.exceptions.DateOutOfLimitException;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class Dates implements Serializable {
    private Calendar authorizationDate;
    private Calendar deliveryDate;
    private HashMap<Calendar, PaymentStatus> paymentDates = new HashMap<>() {
        {
            put(Calendar.getInstance(), new PaymentStatus());
            put(Calendar.getInstance(), new PaymentStatus());
            put(Calendar.getInstance(), new PaymentStatus());
            put(Calendar.getInstance(), new PaymentStatus());
            put(Calendar.getInstance(), new PaymentStatus());
            put(Calendar.getInstance(), new PaymentStatus());
        }
    };

    public Dates(String date) {
        setAuthorizationDate(date);
        setDeliveryDate();
        setPaymentDates();
    }

    public boolean arePaymentsPaid() {
        for (PaymentStatus status : paymentDates.values())
            if (!status.isPaid())
                return false;
        return true;
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
        for (Calendar paymentDate : paymentDates.keySet()) {
            paymentDate.setTime(deliveryDate.getTime());
            paymentDate.add(Calendar.DATE, days);
            days += 30;
        }
    }

    public String getAuthorizationDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(authorizationDate.getTime());
    }

    public HashMap<Calendar, PaymentStatus> getPaymentDates() {
        return paymentDates;
    }

    public Calendar getDeliveryDate() {
        return deliveryDate;
    }

}
