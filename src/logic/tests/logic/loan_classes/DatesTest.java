package logic.loan_classes;

import logic.exceptions.DateOutOfLimitException;
import logic.loan_classes.CalendarFormatter;
import logic.loan_classes.Dates;
import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

class DatesTest {
    @Test
    void deliveryDateTest() {
        Dates date = new Dates("2020-06-20");
        Calendar deliveryDate = CalendarFormatter.format("2020-06-27");
        assertEquals(deliveryDate.getTime(), date.getDeliveryDate().getTime());
    }

    @Test
    void paymentDatesTest() {
        Dates date = new Dates("2020-03-13");
        Calendar[] dates = new Calendar[]{
                CalendarFormatter.format("2020-04-19"),
                CalendarFormatter.format("2020-05-19"),
                CalendarFormatter.format("2020-06-18"),
                CalendarFormatter.format("2020-07-18"),
                CalendarFormatter.format("2020-08-17"),
                CalendarFormatter.format("2020-09-16")
        };
        assertArrayEquals(dates, date.getPaymentDates());
    }

    @Test
    void authDateOutOfLimitTest() {
        assertThrows(DateOutOfLimitException.class, () -> new Dates("2020-01-21"));
    }
}