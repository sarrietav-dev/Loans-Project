package logic.exceptions;

public class BorrowerAlreadyExistsException extends RuntimeException {
    public BorrowerAlreadyExistsException(String message) {
        super(message);
    }
}
