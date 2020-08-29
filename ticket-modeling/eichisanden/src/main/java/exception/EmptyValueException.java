package exception;

public class EmptyValueException extends ApplicationException {
    public EmptyValueException(String message) {
        super(message);
    }

    public EmptyValueException(String message, Throwable cause) {
        super(message, cause);
    }
}
