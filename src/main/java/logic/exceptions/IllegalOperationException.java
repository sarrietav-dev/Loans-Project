package logic.exceptions;

public class IllegalOperationException extends RuntimeException {
    public IllegalOperationException() {
    }

    public IllegalOperationException(String message) {
        super(message);
    }
}
