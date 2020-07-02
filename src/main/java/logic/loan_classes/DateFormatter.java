package logic.loan_classes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public interface DateFormatter {

    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    static Date format(String date) {
        checkDateCorrect(date);
        try {
            return formatter.parse(date);
        } catch (ParseException | NullPointerException e) {
            throw new NullPointerException("Incorrect Date.");
        }
    }

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
