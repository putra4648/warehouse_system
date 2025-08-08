package id.putra.wms.shared.base.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import id.putra.wms.shared.base.dto.response.attribute.ResponseSchemaAttribute;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseData<T> {

  @JsonProperty("responseSchema")
  private ResponseSchemaAttribute responseSchema;

  @JsonProperty("data")
  private T data;

}