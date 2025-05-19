package id.putra.wms.dto.response;

import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
public class AppResponse {

    ///  disable public constructor
    private AppResponse() {
    }

    /**
     * Construct AppResponse constructor with only message
     *
     * @param message
     */
    public record WithMessageOnly(String message) {
    }

    /**
     * Construct AppResponse constructor with paging information
     *
     * @param page
     * @param size
     * @param total
     * @param message
     * @param results
     * @param <T>
     */
    public record WithPaging<T>(int page, int size, long total, String message, List<T> results) {
    }

    /**
     * Construct AppResponse constructor default response
     *
     * @param message
     * @param results
     * @param <T>
     */
    public record WithDefault<T>(String message, List<T> results) {
    }

    /**
     * Construct static AppResponse.WithErrorOnly constructor
     */
    @Data
    public static class WithErrorOnly {
        private final String message;
        private final Instant occurredDate = Instant.now();
    }
}
