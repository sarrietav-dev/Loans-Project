package logic.exceptions;

public class LoginIncorrectException extends RuntimeException {
    public LoginIncorrectException() {
    }

    public LoginIncorrectException(String message) {
        super(message);
    }
}
