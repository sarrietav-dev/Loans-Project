package logic.loan_classes;

import logic.company_members.Employee;
import logic.databases.ClientDatabase;
import logic.exceptions.DateOutOfLimitException;

import java.io.Serializable;
import java.util.*;

/**
 * Calculates and stores a loan's payment dates of each installment,<br>
 * the authorization date and the delivery date.
 */
public class Dates implements Serializable {
    private Date authorizationDate;
    private Date deliveryDate;
    protected final HashMap<Date, PaymentStatus> paymentDates = new HashMap<>();

    /**
     * @param date The authorization date of the loan.
     * @throws DateOutOfLimitException if the authorization date is off the limits given by the company.
     */
    public Dates(String date) {
        setAuthorizationDate(date);
        setDeliveryDate();
        setPaymentDates();
    }

    private void setAuthorizationDate(String authorizationDate) {
        final ClientDatabase CLIENT_DATABASE = ClientDatabase.getInstance();
        Date authDate = DateFormatter.format(authorizationDate);
        if (isAuthDateWithinLimits(authDate)) {
            if (isAuthDateAfterToday(authDate)) {
                this.authorizationDate = authDate;
            } else {
                throw new DateOutOfLimitException("Only dates after today! ("+ DateFormatter.format(new Date()) + ")");
            }
        } else {
            throw new DateOutOfLimitException("Date out of limits! Only until " + CLIENT_DATABASE.getLimitOfAuthDate() + " days of the month!");
        }
    }

    private boolean isAuthDateAfterToday(Date date) {
	    return date.after(new Date());
    }

    private boolean isAuthDateWithinLimits(Date date) {
        final ClientDatabase CLIENT_DATABASE = ClientDatabase.getInstance();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.get(Calendar.DAY_OF_MONTH);
        return calendar.get(Calendar.DAY_OF_MONTH) <= CLIENT_DATABASE.getLimitOfAuthDate();
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

    /**
     * Shows the payment dates of each installment.
     *
     * @return A list of dates, in ascending order.
     */
    public List<Date> getDatesSorted() {
        List<Date> datesSorted = new ArrayList<>(paymentDates.keySet());
        datesSorted.sort(Date::compareTo);
        return datesSorted;
    }

    HashMap<Date, PaymentStatus> getPaymentDates() {
        return paymentDates;
    }

    @Override
    public String toString() {
        return "Dates{" +
                "authorizationDate=" + DateFormatter.format(authorizationDate) +
                ", deliveryDate=" + DateFormatter.format(deliveryDate) +
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
