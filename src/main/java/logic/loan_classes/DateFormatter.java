package logic.loan_classes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public interface DateFormatter {

    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * Converts a string date into a Date object.
     * @param date A date in a string with the yyyy-MM-dd format.
     * @return A date object.
     * @throws NullPointerException if the date doesn't match the yyyy-MM-dd format.
     */
    static Date format(String date) {
        checkDateCorrect(date);
        try {
            return formatter.parse(date);
        } catch (ParseException | NullPointerException e) {
            throw new NullPointerException("Incorrect Date.");
        }
    }

    /**
     * Takes a date object and converts it into string.
     * @param date A date object.
     * @return The string of the date in the yyyy-MM-dd format.
     */
    static String format(Date date) {
        return formatter.format(date);
    }
    
    private static void checkDateCorrect(String date) {
        String[] dateSplit = date.split("-");

        if (!isDateCorrect(dateSplit))
            throw new NullPointerException("Incorrect Date");
    }

    private static boolean isDateCorrect(String[] dateSplit) {
        final String YEAR = dateSplit[0];
        final String MONTH = dateSplit[1];
        final String DAY = dateSplit[2];

        return isAYear(YEAR) && isAMonth(MONTH) && isADay(DAY);
    }

    private static boolean isAYear(final String YEAR) {
        return YEAR.length() == 4;
    }

    private static boolean isAMonth(final String MONTH) {
        return MONTH.length() == 2 && (Integer.parseInt(MONTH) > 0 && Integer.parseInt(MONTH) < 13);
    }

    private static boolean isADay(final String DAY) {
        return DAY.length() == 2 && (Integer.parseInt(DAY) > 0 && Integer.parseInt(DAY) < 32);
    }
}
