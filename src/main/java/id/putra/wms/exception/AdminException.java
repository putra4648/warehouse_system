package id.putra.wms.exception;

import id.putra.wms.enums.ErrorType;
import lombok.Getter;

@Getter
public class AdminException extends RuntimeException {
    private final ErrorType errorType;
    private final String resourceId;

    /**
     * Constructs admin exception with specified ErrorType
     *
     * @param errorType - ErrorType enum
     */
    public AdminException(ErrorType errorType) {
        super(errorType.getMessage());
        this.errorType = errorType;
        this.resourceId = null;
    }

    /**
     * Constructs admin exception with specified ErrorType, custom message and Resource ID
     *
     * @param errorType - ErrorType enum
     * @param message - Custom error message
     * @param resourceId - Resource ID
     */
    public AdminException(ErrorType errorType, String message, String resourceId) {
        super(message);
        this.errorType = errorType;
        this.resourceId = resourceId;
    }

    /**
     * Constructs admin exception with specified ErrorType and custom message
     *
     * @param errorType - ErrorType enum
     * @param message - Custom error message
     */
    public AdminException(ErrorType errorType, String message) {
        super(message);
        this.errorType = errorType;
        this.resourceId = null;
    }


}
