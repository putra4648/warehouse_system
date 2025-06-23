package id.putra.wms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SettingController {
    @GetMapping("/setting")
    public String setting(Model model) {
        return "pages/setting";
    }
}
