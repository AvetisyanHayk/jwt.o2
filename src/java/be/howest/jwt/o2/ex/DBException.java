package be.howest.jwt.o2.ex;

/**
 *
 * @author Hayk
 */
public class DBException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DBException(Throwable cause) {
        super("Database error", cause);
    }

    public DBException(String message, Throwable cause) {
        super(message, cause);
    }
}
