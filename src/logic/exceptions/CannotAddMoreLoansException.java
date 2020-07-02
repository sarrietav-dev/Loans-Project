package logic.exceptions;

public class CannotAddMoreLoansException extends RuntimeException {
    public CannotAddMoreLoansException() {

    }

    public CannotAddMoreLoansException(String message) {
        super(message);
    }
}
