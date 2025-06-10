package id.putra.wms.controller.advice;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice(basePackages = "id.putra.wms.controller")
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
}
