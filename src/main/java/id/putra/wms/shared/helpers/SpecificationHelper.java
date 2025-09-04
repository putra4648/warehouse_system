package id.putra.wms.shared.helpers;

public class SpecificationHelper {
    private SpecificationHelper() {
    }

    public static <T> String nullValueWrapper(T object) {
        return switch (object) {
            case null -> "";
            default -> "";
        };
    }
}
