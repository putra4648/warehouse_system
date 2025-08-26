package id.putra.wms.module.setting.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SettingController {
    @GetMapping("/setting")
    public String setting(Model model) {
        return "pages/setting";
    }
}
