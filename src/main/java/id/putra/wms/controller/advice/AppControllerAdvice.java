package id.putra.wms.controller.advice;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import id.putra.wms.constant.MessageConstant;
import id.putra.wms.exceptions.MasterDataException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@ControllerAdvice(basePackages = "id.putra.wms.controller")
@Slf4j
public class AppControllerAdvice {

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

}
