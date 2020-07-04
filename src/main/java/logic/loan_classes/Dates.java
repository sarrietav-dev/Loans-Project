package logic.loan_classes;

import logic.exceptions.DateOutOfLimitException;

import java.io.Serializable;
import java.util.*;

public class Dates implements Serializable {
    private Date authorizationDate;
    private Date deliveryDate;
    protected final HashMap<Date, PaymentStatus> paymentDates = new HashMap<>();

    protected Dates() {
    }

    public Dates(String date) {
        setAuthorizationDate(date);
        setDeliveryDate();
        setPaymentDates();
    }

    private void setAuthorizationDate(String authorizationDate) {
        Date authDate = DateFormatter.format(authorizationDate);
        if (isAuthDateWithinLimits(authDate))
            this.authorizationDate = authDate;
        else
            throw new DateOutOfLimitException("Date out of limits! Only between the first 20 days of the month!");
    }

    private boolean isAuthDateWithinLimits(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH) <= 20;
    }

    private void setDeliveryDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(authorizationDate);
        calendar.add(Calendar.DATE, 7);
        deliveryDate = calendar.getTime();
    }

    private void setPaymentDates() {
        int days = 30;
        Calendar paymentDate = Calendar.getInstance();
        paymentDate.setTime(deliveryDate);
        for (int i = 0; i < 6; i++) {
            paymentDate.add(Calendar.DATE, days);
            paymentDates.put(paymentDate.getTime(), new PaymentStatus());
        }
    }

    public List<Date> getDatesSorted() {
        List<Date> datesSorted = new ArrayList<>(paymentDates.keySet());
        datesSorted.sort(Date::compareTo);
        return datesSorted;
    }

    public boolean isDelayed() {
        return paymentDates.values().stream().anyMatch(PaymentStatus::isDelayed);
    }

    public HashMap<Date, PaymentStatus> getPaymentDates() {
        return paymentDates;
    }

    @Override
    public String toString() {
        return "Dates{" +
                "authorizationDate=" + DateFormatter.format(authorizationDate)  +
                ", deliveryDate=" + DateFormatter.format(deliveryDate)  +
                ", paymentDates=" + paymentDatesToString() +
                '}';
    }

    private String paymentDatesToString() {
        StringBuilder toString = new StringBuilder();
        for (Map.Entry<Date, PaymentStatus> entry : paymentDates.entrySet()) {
            String tempString = String.format("%s:%s, ",
                    entry.getKey(),
                    entry.getValue().toString());
            toString.append(tempString);
        }
        return toString.toString();
    }
}
