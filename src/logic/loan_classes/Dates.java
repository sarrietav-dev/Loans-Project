package logic.loan_classes;

import logic.exceptions.DateOutOfLimitException;
import logic.exceptions.ObjectNotFoundException;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static logic.loan_classes.DateSorter.dateQuickSort;

public class Dates implements Serializable, PaymentMethods {
    private Date authorizationDate;
    private Date deliveryDate;
    private final HashMap<Date, PaymentStatus> paymentDates = new HashMap<>();
    private Date[] orderedDates;

    public Dates(String date) {
        setAuthorizationDate(date);
        setDeliveryDate();
        setPaymentDates();
        sortDates();
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

    private void sortDates() {
        Date[] dates = new Date[paymentDates.keySet().size()];
        int i = 0;
        for (Date date : paymentDates.keySet()) {
            dates[i] = date;
            i++;
        }
        dateQuickSort(dates, 0, dates.length - 1);
        orderedDates = dates;
    }

    @Override
    public boolean arePaymentsPaid() {
        for (PaymentStatus status : paymentDates.values())
            if (status.isNotPaid())
                return false;
        return true;
    }

    @Override
    public void payAll() {
        for (Date orderedDate : orderedDates)
            for (Date paymentDate : paymentDates.keySet())
                if (paymentDates.get(paymentDate).isNotPaid() && orderedDate.equals(paymentDate)) {
                    setPaymentForPayAll(new Date(), paymentDate);
                }
        setAllDelayedStatus(new Date());
    }

    @Override
    public void payAll(Date date) {
        for (Date orderedDate : orderedDates)
            for (Date paymentDate : paymentDates.keySet())
                if (paymentDates.get(paymentDate).isNotPaid() && orderedDate.equals(paymentDate)) {
                    setPaymentForPayAll(date, paymentDate);
                }
        setAllDelayedStatus(date);
    }

    private void setPaymentForPayAll(Date actualPaymentDate, Date dateToBeModified) {
        paymentDates.get(dateToBeModified).pay(actualPaymentDate);
    }

    private void setAllDelayedStatus(Date actualPaymentDate) {
        if (isLastPaymentDelayed(actualPaymentDate))
            for (PaymentStatus status : paymentDates.values())
                status.setDelayed(true);
        else
            for (PaymentStatus status : paymentDates.values())
                status.setDelayed(false);
    }

    private boolean isLastPaymentDelayed(Date actualPaymentDate) {
        return getLastPayment().before(actualPaymentDate);
    }

    private Date getLastPayment() {
        for (Date paymentDate : paymentDates.keySet())
            if (paymentDate.equals(orderedDates[5]))
                return paymentDate;
        throw new ObjectNotFoundException();
    }

    @Override
    public void pay() {
        for (Date date : orderedDates)
            for (Date keyDate : paymentDates.keySet())
                if (paymentDates.get(keyDate).isNotPaid() && date.equals(keyDate)) {
                    setPayment(keyDate, new Date());
                    return;
                }
    }

    @Override
    public void pay(Date date) {
        for (Date orderedDate : orderedDates)
            for (Date paymentDate : paymentDates.keySet())
                if (paymentDates.get(paymentDate).isNotPaid() && orderedDate.equals(paymentDate)) {
                    setPayment(paymentDate, date);
                }
    }

    private void setPayment(Date expectedPaymentDate, Date actualPaymentDate) {
        paymentDates.get(expectedPaymentDate).pay(actualPaymentDate);
        setDelayed(expectedPaymentDate);
    }

    private void setDelayed(Date expectedPaymentDate) {
        if (isDelayed(expectedPaymentDate))
            paymentDates.get(expectedPaymentDate).setDelayed(true);
    }

    private boolean isDelayed(Date expectedPaymentDate) {
        PaymentStatus status = paymentDates.get(expectedPaymentDate);
        return expectedPaymentDate.before(status.getPaymentDate());
    }

    public boolean isDelayed() {
        return paymentDates.values().stream().anyMatch(PaymentStatus::isDelayed);
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
