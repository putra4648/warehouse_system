package id.putra.wms.config.exception;

import id.putra.wms.shared.enums.ResponseEnum;
import lombok.Getter;

@Getter
public class ModuleException extends RuntimeException {

  private final ResponseEnum responseEnum;

  public ModuleException(ResponseEnum responseEnum) {
    super(responseEnum.getResponseCode());
    this.responseEnum = responseEnum;
  }
}
