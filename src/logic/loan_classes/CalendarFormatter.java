package logic.loan_classes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public interface CalendarFormatter {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    static Calendar format(String date) {
        Calendar tempCalendar = Calendar.getInstance();
        try {
            checkDateCorrect(date);
            Date dateParsed = formatter.parse(date);
            tempCalendar.setTime(dateParsed);
        } catch (NullPointerException | ParseException e) {
            throw new NullPointerException("Incorrect Date.");
        }
        return tempCalendar;
    }
    
    private static void checkDateCorrect(String date) {
        String[] dateSplit = date.split("-");

        final String YEAR = dateSplit[0];
        final String MONTH = dateSplit[1];
        final String DAY = dateSplit[2];

        boolean isYearCorrect = YEAR.length() == 4;
        boolean isMonthCorrect = MONTH.length() == 2 && (Integer.parseInt(MONTH) > 0 && Integer.parseInt(MONTH) < 13);
        boolean isDayCorrect = DAY.length() == 2 && (Integer.parseInt(DAY) > 0 && Integer.parseInt(DAY) < 32);

        if (!(isYearCorrect && isMonthCorrect && isDayCorrect))
            throw new NullPointerException();
    }
}
