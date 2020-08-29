package exception;

public class LengthException extends ApplicationException {
    public LengthException(String message) {
        super(message);
    }

    public LengthException(String message, Throwable cause) {
        super(message, cause);
    }
}
