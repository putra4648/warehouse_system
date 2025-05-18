package id.putra.wms.exception;

public class RolesException extends RuntimeException {
    public RolesException(String message) {
        super(message);
    }

    public RolesException(String message, Throwable cause) {
        super(message, cause);
    }
}
