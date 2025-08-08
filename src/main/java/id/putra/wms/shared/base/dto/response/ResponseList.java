package id.putra.wms.shared.base.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import id.putra.wms.shared.base.dto.response.attribute.ResponseSchemaAttribute;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseList<T> {

  @JsonProperty("responseSchema")
  private ResponseSchemaAttribute responseSchemaAttribute;

  @JsonProperty("data")
  private List<T> data;

}