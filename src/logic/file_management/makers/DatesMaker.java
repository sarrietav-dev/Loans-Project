package logic.file_management.makers;

import logic.loan_classes.Borrower;
import logic.loan_classes.Dates;

/**
 * The class makes a {@link Dates} from a string of data.
 */
public class DatesMaker {
    private final String date;

    /**
     *
     * @param date An Authorization date.
     */
    public DatesMaker(String date) {
        this.date = date;
    }

    /**
     * Makes the data to a {@link Dates} object.
     * @return A {@link Dates} object.
     */
    public Dates make() {
        return new Dates(date);
    }
}
