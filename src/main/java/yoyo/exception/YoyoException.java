package yoyo.exception;

/**
 * Customized Exception class for Yoyo.
 */
public class YoyoException extends Exception {
    /**
     * Initialise a YoyoException.
     * @param message Error message.
     */
    public YoyoException(String message) {
        super(message);
    }
}
