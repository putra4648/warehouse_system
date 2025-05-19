package id.putra.wms.controller;

import id.putra.wms.dto.response.AppResponse;
import id.putra.wms.exception.AdminException;
import id.putra.wms.exception.AuthException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class GlobalControllerAdvice {

    @ExceptionHandler({AdminException.class, AuthException.class})
    public ResponseEntity<AppResponse.WithErrorOnly> handleAdminException(RuntimeException ex) {
        var response = new AppResponse.WithErrorOnly(ex.getMessage());

        log.error(ex.getMessage(), ex);

        return ResponseEntity.internalServerError().body(response);
    }

    /// Validator exception handler
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, List<String>>> handleValidationException(MethodArgumentNotValidException ex) {
        var errors = new HashMap<String, List<String>>();
        var messages = new ArrayList<String>();

        log.error(ex.getMessage(), ex);

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            messages.add(String.format("'%S' parameter %s", error.getField(), error.getDefaultMessage()));
        });
        errors.put("error", messages);
        
        return ResponseEntity.badRequest().body(errors);
    }
}
