package logic.loan_classes;

import logic.FetchInformation;
import logic.IDGetterSetter;
import logic.exceptions.DateOutOfLimitException;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Objects;

public class Dates implements IDGetterSetter, FetchInformation, Serializable {
    private int ID;
    private Calendar authorizationDate;
    private Calendar deliveryDate;
    private final Calendar[] paymentDates = new Calendar[]{
            Calendar.getInstance(),
            Calendar.getInstance(),
            Calendar.getInstance(),
            Calendar.getInstance(),
            Calendar.getInstance(),
            Calendar.getInstance()
    };

    public Dates(String date) {
        setAuthorizationDate(date);
        setDeliveryDate();
        setPaymentDates();
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
        for (Calendar paymentDate : paymentDates) {
            paymentDate.setTime(deliveryDate.getTime());
            paymentDate.add(Calendar.DATE, days);
            days += 30;
        }
    }

    public String[] getInfo() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return new String[] {
                String.valueOf(ID),
                dateFormat.format(authorizationDate.getTime()),
        };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dates dates = (Dates) o;
        return ID == dates.ID &&
                authorizationDate.equals(dates.authorizationDate) &&
                deliveryDate.equals(dates.deliveryDate) &&
                Arrays.equals(paymentDates, dates.paymentDates);
    }

    @Override
    public String toString() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return "Dates{" +
                "ID=" + ID +
                ", authorizationDate=" + dateFormat.format(authorizationDate.getTime()) +
                ", deliveryDate=" + dateFormat.format(deliveryDate.getTime()) +
                ", paymentDates=" + Arrays.toString(getPaymentDatesFormatted()) +
                '}';
    }

    private String[] getPaymentDatesFormatted() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return new String[] {
                dateFormat.format(paymentDates[0].getTime()),
                dateFormat.format(paymentDates[1].getTime()),
                dateFormat.format(paymentDates[2].getTime()),
                dateFormat.format(paymentDates[3].getTime()),
                dateFormat.format(paymentDates[4].getTime()),
                dateFormat.format(paymentDates[5].getTime()),
        };
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(ID, authorizationDate, deliveryDate);
        result = 31 * result + Arrays.hashCode(paymentDates);
        return result;
    }

    public String getAuthorizationDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(authorizationDate.getTime());
    }

    public Calendar[] getPaymentDates() {
        return paymentDates;
    }

    @Override
    public void setID(int id) {
        this.ID = id;
    }

    public Calendar getDeliveryDate() {
        return deliveryDate;
    }

    @Override
    public int getID() {
        return ID;
    }
}
