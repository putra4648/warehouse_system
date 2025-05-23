package id.putra.wms.enums;

public enum ErrorType {
    PERMISSION_DENIED("Permission denied"),
    INVALID_TOKEN("Invalid or expired token"),
    INSUFFICIENT_ROLE("Insufficient role privileges"),
    TOKEN_EXPIRED("Authentication token has expired"),
    ROLE_NOT_FOUND("Required role not found"),
    PERMISSION_NOT_GRANTED("Required permission not granted"),
    TOKEN_MALFORMED("Malformed authentication token"),
    UNAUTHORIZED_ACCESS("Unauthorized access attempt"),
    USERNAME_ALREADY_EXISTS("Username already exists");

    private final String message;

    ErrorType(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
