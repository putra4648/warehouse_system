package id.putra.wms.shared.base.dto.response.thymeleaf;

import java.util.List;

public record PagingResponse<T>(
                Long lastPage,
                List<T> data) {

}
