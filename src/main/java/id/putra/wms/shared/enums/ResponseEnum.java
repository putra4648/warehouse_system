package id.putra.wms.shared.enums;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public enum ResponseEnum {
  SUCCESS("success", "Success", HttpStatus.OK),
  DATA_NOT_FOUND("not_found", "Data not found", HttpStatus.NOT_FOUND),

  INVALID_PARAM("invalid_param", "Invalid Parameter", HttpStatus.BAD_REQUEST),
  INTERNAL_SERVER_ERROR("internal_server_error", "Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR),
  DATA_ALREADY_EXIST("data_already_exist", "Data already exist", HttpStatus.OK);

  private final String responseCode;
  private final String responseMessage;
  private final HttpStatus httpStatus;

  ResponseEnum(String responseCode, String responseMessage, HttpStatus httpStatus) {
    this.responseCode = responseCode;
    this.responseMessage = responseMessage;
    this.httpStatus = httpStatus;
  }

}
