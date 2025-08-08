package id.putra.wms.shared.base.dto.response.thymeleaf;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PagingResponse<T>(
        @JsonProperty("last_page") Long lastPage,
        List<T> data
) {

}
