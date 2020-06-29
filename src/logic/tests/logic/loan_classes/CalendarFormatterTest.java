package logic.loan_classes;

import logic.loan_classes.CalendarFormatter;
import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

class CalendarFormatterTest implements CalendarFormatter {
    String date = "25-25-25";
    @Test
    void formatTest() {
        assertThrows(NullPointerException.class, () -> CalendarFormatter.format(date));
    }

    @Test
    void equalDates() {
        Calendar calendar1 = CalendarFormatter.format("2020-01-12");
        Calendar calendar2 = CalendarFormatter.format("2020-01-12");
        assertTrue(calendar1.getTime().equals(calendar2.getTime()));
    }

    @Test
    void formatTest2() {
        CalendarFormatter.format("2020-02-11");
    }
}