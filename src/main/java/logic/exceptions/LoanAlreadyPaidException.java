package logic.exceptions;

public class LoanAlreadyPaidException extends RuntimeException {
    public LoanAlreadyPaidException() {
    }

    public LoanAlreadyPaidException(String message) {
        super(message);
    }
}
