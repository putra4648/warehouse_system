package id.putra.wms.controller;

import java.util.stream.IntStream;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    @GetMapping("/")
    public String index(@AuthenticationPrincipal OidcUser user, Model model) {
        user.getFullName();

        return "pages/index";
    }

    @GetMapping("/outbound")
    public String logistic(Model model) {

        return "pages/outbound";
    }

    @GetMapping("/inbound")
    public String order(Model model) {

        return "pages/inbound";
    }

    @GetMapping("/supplier")
    public String supplier(Model model) {
        model.addAttribute("results", IntStream.range(0, 100).toArray());

        return "pages/supplier";
    }

    @GetMapping("/report_and_analysis")
    public String reportAndAnalysis(Model model) {

        return "pages/report_and_analysis";
    }

    @GetMapping("/user")
    public String user(Model model) {

        return "pages/user";
    }

    @GetMapping("/setting")
    public String setting(Model model) {

        return "pages/setting";
    }

}
