package exception;

public class LogicalException extends ApplicationException {
    public LogicalException(String message) {
        super(message);
    }

    public LogicalException(String message, Throwable cause) {
        super(message, cause);
    }
}
