package id.putra.wms.controller;

import id.putra.wms.dto.response.AppErrorResponse;
import id.putra.wms.exception.TokenException;
import id.putra.wms.exception.UserException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalControllerAdvice {

    @ExceptionHandler({UserException.class, TokenException.class})
    public ResponseEntity<AppErrorResponse> handleUserException(RuntimeException ex) {
        AppErrorResponse response = new AppErrorResponse();
        response.setMessage(ex.getMessage());

        log.error(ex.getMessage(), ex);

        return ResponseEntity.internalServerError().body(response);
    }
}
