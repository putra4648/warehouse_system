package id.putra.wms.shared.base.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import id.putra.wms.shared.base.dto.response.attribute.ErrorAttribute;
import id.putra.wms.shared.base.dto.response.attribute.ResponseSchemaAttribute;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseError {

  @JsonProperty("responseSchema")
  private ResponseSchemaAttribute responseSchema;

  @JsonProperty("errors")
  private List<ErrorAttribute> errors;

}
