package logic.exceptions;


public class DateOutOfLimitException extends RuntimeException {
    /**
     * Sends a signal when a date is out of the provided limits.
     * @param message A string that will be displayed when the exception is thrown.
     */
    public DateOutOfLimitException(String message) {
        super(message);
    }
}
