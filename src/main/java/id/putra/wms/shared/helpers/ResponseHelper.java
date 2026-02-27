package id.putra.wms.shared.helpers;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import id.putra.wms.shared.base.dto.response.ResponseData;
import id.putra.wms.shared.base.dto.response.ResponseError;
import id.putra.wms.shared.base.dto.response.ResponseList;
import id.putra.wms.shared.base.dto.response.ResponseMeta;
import id.putra.wms.shared.base.dto.response.attribute.ErrorAttribute;
import id.putra.wms.shared.base.dto.response.attribute.MetaAttribute;
import id.putra.wms.shared.base.dto.response.attribute.ResponseSchemaAttribute;
import id.putra.wms.shared.enums.ResponseEnum;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ResponseHelper {

        /**
         * a utility function that is used to generate detailed responses
         * 
         * @param responseEnum Enum Response
         * @param <T>          Generic Type, for class body return
         * @return ResponseEntity contain Template Response
         */
        public <T> ResponseEntity<ResponseData<T>> createResponseData(
                        ResponseEnum responseEnum,
                        T data) {
                return ResponseEntity
                                .status(java.util.Objects.requireNonNull(responseEnum.getHttpStatus()))
                                .body(ResponseData.<T>builder()
                                                .data(data)
                                                .build());
        }

        public <T> ResponseEntity<ResponseList<T>> createResponseList(
                        ResponseEnum responseEnum,
                        List<T> list) {
                return ResponseEntity
                                .status(java.util.Objects.requireNonNull(responseEnum.getHttpStatus()))
                                .body(ResponseList.<T>builder()
                                                .responseSchemaAttribute(this.getResponseSchema(responseEnum))
                                                .data(list)
                                                .build());
        }

        public ResponseEntity<ResponseError> createResponseError(
                        ResponseEnum responseEnum,
                        List<ErrorAttribute> errorAttributes) {
                return ResponseEntity
                                .status(java.util.Objects.requireNonNull(responseEnum.getHttpStatus()))
                                .body(ResponseError.builder()
                                                .responseSchema(this.getResponseSchema(responseEnum))
                                                .errors(errorAttributes)
                                                .build());
        }

        public <T> ResponseEntity<ResponseMeta<T>> createResponseMeta(
                        ResponseEnum responseEnum,
                        Page<T> page) {

                return ResponseEntity
                                .status(java.util.Objects.requireNonNull(responseEnum.getHttpStatus()))
                                .body(ResponseMeta.<T>builder()
                                                .responseSchema(this.getResponseSchema(responseEnum))
                                                .meta(MetaAttribute.builder().page(page.getNumber())
                                                                .size(page.getSize()).total(page.getTotalElements())
                                                                .lastPage(page.getTotalPages())
                                                                .build())
                                                .data(page.getContent())
                                                .build());
        }

        private ResponseSchemaAttribute getResponseSchema(ResponseEnum responseEnum) {
                return ResponseSchemaAttribute.builder()
                                .responseCode(responseEnum.getResponseCode())
                                .responseMessage(responseEnum.getResponseMessage())
                                .build();
        }

}
