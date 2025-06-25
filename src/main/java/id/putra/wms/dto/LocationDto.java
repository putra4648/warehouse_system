package id.putra.wms.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LocationDto {
    private String id;

    @NotBlank(message = "Location NAME cannot be empty")
    private String name;

    @NotBlank(message = "Location TYPE cannot be empty")
    private String type;

    private Boolean is_active;

    private String bin_number;
}
