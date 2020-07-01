package logic.loan_classes;

import logic.exceptions.DateOutOfLimitException;
import logic.exceptions.ObjectNotFoundException;
import logic.loan_classes.setpayment_implementation.SetPayment;
import logic.loan_classes.setpayment_implementation.SetPaymentOptions;

import java.io.Serializable;
import java.util.*;

public class Dates implements Serializable, PaymentMethods {
    private Date authorizationDate;
    private Date deliveryDate;
    protected final HashMap<Date, PaymentStatus> paymentDates = new HashMap<>();
    private List<Date> orderedDates;

    protected Dates() {
    }

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
        orderedDates = Arrays.asList(new Date[6]);
        orderedDates.addAll(paymentDates.keySet());
        orderedDates.sort(Date::compareTo);
    }

    @Override
    public boolean arePaymentsPaid() {
        return paymentDates.values().stream()
                .noneMatch(PaymentStatus::isNotPaid);
    }

    @Override
    public void payAll() {
        searchAndPay(SetPaymentOptions.PAY_ALL, new Date());
        setAllDelayedStatus(new Date());
    }

    @Override
    public void payAll(Date date) {
        searchAndPay(SetPaymentOptions.PAY_ALL, date);
        setAllDelayedStatus(date);
    }

    private void setAllDelayedStatus(Date actualPaymentDate) {
        if (isLastPaymentDelayed(actualPaymentDate))
            paymentDates.values().forEach(status -> status.setDelayed(true));
        else
            paymentDates.values().forEach(status -> status.setDelayed(false));
    }

    private boolean isLastPaymentDelayed(Date actualPaymentDate) {
        return getLastPayment().before(actualPaymentDate);
    }

    private Date getLastPayment() {
        for (Date paymentDate : paymentDates.keySet())
            if (paymentDate.equals(orderedDates.get(5)))
                return paymentDate;
        throw new ObjectNotFoundException();
    }

    @Override
    public void pay() {
        searchAndPay(SetPaymentOptions.PAY, new Date());
    }

    @Override
    public void pay(Date date) {
        searchAndPay(SetPaymentOptions.PAY, date);
    }

    private void searchAndPay(SetPaymentOptions option, Date date) {
        for (Date orderedDate : orderedDates)
            for (Date paymentDate : paymentDates.keySet())
                if (paymentDates.get(paymentDate).isNotPaid() && orderedDate.equals(paymentDate)) {
                    new SetPayment(option).getSetter().pay(paymentDate, date);
                    if (option == SetPaymentOptions.PAY)
                        return;
                }

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
