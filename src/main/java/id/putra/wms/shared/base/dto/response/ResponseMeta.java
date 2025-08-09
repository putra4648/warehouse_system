package id.putra.wms.shared.base.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import id.putra.wms.shared.base.dto.response.attribute.MetaAttribute;
import id.putra.wms.shared.base.dto.response.attribute.ResponseSchemaAttribute;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseMeta<T> {

	@JsonProperty("responseSchema")
	private ResponseSchemaAttribute responseSchema;

	@JsonProperty("meta")
	private MetaAttribute meta;

	@JsonProperty("data")
	private List<T> data;

}