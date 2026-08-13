package id.putra.wms.shared.converter;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Utility class for safe DTO conversions with non-null collection handling
 * All collection conversions return empty collections instead of null
 */
public class DtoConverter {

    private DtoConverter() {
        // Utility class
    }

    /**
     * Converts a list of entities to DTOs using the provided converter function
     * Returns empty list if input is null
     */
    public static <E, D> List<D> toList(Collection<E> entities, Function<E, D> converter) {
        if (entities == null || entities.isEmpty()) {
            return Collections.emptyList();
        }
        return entities.stream()
                .map(converter)
                .collect(Collectors.toList());
    }

    /**
     * Safe null check utility
     */
    public static <T> boolean isNull(T value) {
        return value == null;
    }

    /**
     * Safe non-null check utility
     */
    public static <T> boolean isNotNull(T value) {
        return value != null;
    }
}
