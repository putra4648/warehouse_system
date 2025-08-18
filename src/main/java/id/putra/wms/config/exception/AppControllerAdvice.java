package id.putra.wms.config.exception;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.ui.Model;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import id.putra.wms.shared.base.dto.response.attribute.ErrorAttribute;
import id.putra.wms.shared.constants.MessageConstant;
import id.putra.wms.shared.enums.ResponseEnum;
import id.putra.wms.shared.exceptions.MasterDataException;
import id.putra.wms.shared.helpers.ResponseHelper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class AppControllerAdvice extends ResponseEntityExceptionHandler {

    private final ResponseHelper responseHelper;

    @ModelAttribute
    public void globalAttribute(Model model, HttpServletRequest request) {
        model.addAttribute("path", request.getServletPath());
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);

        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @ExceptionHandler(MasterDataException.class)
    public String exceptionHandler(MasterDataException exception, HttpServletRequest request,
            RedirectAttributesModelMap redirect) {
        redirect.addFlashAttribute("messageHTML", MessageConstant.MESSAGE.formatted("alert-danger",
                "Error", exception.getMessage()));
        return "redirect:" + request.getServletPath();
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView errorModelAndView(Exception exception) {
        log.error(exception.getMessage());
        return new ModelAndView("error");
    }

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
                ErrorAttribute.builder().field(fieldError.getField()).message(fieldError.getDefaultMessage()).build()));

        return ResponseEntity.badRequest()
                .body(responseHelper.createResponseError(ResponseEnum.INVALID_PARAM, errors));
    }

}
