package loan_classes;

import logic.loan_classes.DateFormatter;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class DateFormatterTest implements DateFormatter {
    final String date = "25-25-25";
    @Test
    void formatTest() {
        assertThrows(NullPointerException.class, () -> DateFormatter.format(date));
    }

    @Test
    void equalDates() {
        Date date1 = DateFormatter.format("2020-01-12");
        Date date2 = DateFormatter.format("2020-01-12");
        assertEquals(date1, date2);
    }

    @Test
    void formatTest2() {
        DateFormatter.format("2020-02-11");
    }
}