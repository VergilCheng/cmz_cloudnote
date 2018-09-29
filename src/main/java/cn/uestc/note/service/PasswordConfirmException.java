package cn.uestc.note.service;

public class PasswordConfirmException extends RuntimeException {

    public PasswordConfirmException() {
    }

    public PasswordConfirmException(String message) {
        super(message);
    }

    public PasswordConfirmException(String message, Throwable cause) {
        super(message, cause);
    }

    public PasswordConfirmException(Throwable cause) {
        super(cause);
    }

    public PasswordConfirmException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
