package logic.exceptions;

public class ObjectNotFoundException extends RuntimeException {
    /**
     * A signal that states that a object wasn't found on a search.
     * @param message A string that will be displayed when the exception is thrown.
     */
    public ObjectNotFoundException(String message) {
        super(message);
    }
}
