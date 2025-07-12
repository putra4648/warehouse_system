package id.putra.wms.dto.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class PagingResponse<T> {
    @JsonProperty("last_page")
    private final Long lastPage;
    private final List<T> data;
}
