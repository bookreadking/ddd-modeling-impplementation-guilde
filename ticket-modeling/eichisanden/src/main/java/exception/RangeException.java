package exception;

public class RangeException extends ApplicationException {
    public RangeException(String message) {
        super(message);
    }

    public RangeException(String message, Throwable cause) {
        super(message, cause);
    }
}
