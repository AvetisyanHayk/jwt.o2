package be.howest.jwt.o2.ex;

/**
 *
 * @author Hayk
 */
public class CoreException extends RuntimeException { // TODO must be implemented in web.xml
    
    private static final long serialVersionUID = 1L;

    public CoreException(Throwable cause) {
        super("At this moment we cannot handle your request. We are working on it.", cause);
    }
}
