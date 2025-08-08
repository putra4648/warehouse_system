package id.putra.wms.shared.base.dto.response.attribute;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorAttribute {

  @JsonProperty("field")
  private String field;

  @JsonProperty("message")
  private String message;

}
