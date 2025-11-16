package id.putra.wms.config.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import id.putra.wms.shared.base.dto.response.attribute.ErrorAttribute;
import id.putra.wms.shared.enums.ResponseEnum;
import id.putra.wms.shared.helpers.ResponseHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class AppControllerAdvice extends ResponseEntityExceptionHandler {

        private final ResponseHelper responseHelper;

        /**
         * For handling spring validation, will automatically picked up
         */
        @Override
        protected ResponseEntity<Object> handleMethodArgumentNotValid(
                        @NonNull MethodArgumentNotValidException ex,
                        @NonNull HttpHeaders headers,
                        @NonNull HttpStatusCode status,
                        @NonNull WebRequest request) {
                List<ErrorAttribute> errors = new ArrayList<>();
                ex.getFieldErrors().forEach(fieldError -> errors.add(
                                ErrorAttribute.builder().field(fieldError.getField())
                                                .message(fieldError.getDefaultMessage()).build()));
                return ResponseEntity.badRequest()
                                .body(responseHelper.createResponseError(ResponseEnum.INVALID_PARAM, errors));
        }

}
