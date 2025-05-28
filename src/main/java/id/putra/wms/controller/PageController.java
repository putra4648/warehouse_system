package id.putra.wms.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    @GetMapping("/")
    public String index(@AuthenticationPrincipal OidcUser user, Model model) {
        model.addAttribute("name", user.getUserInfo().getGivenName());
        model.addAttribute("title", "Dashboard");
        return "pages/index";
    }

    @GetMapping("/outbound")
    public String logistic(Model model) {
        model.addAttribute("title", "Outbound");
        return "pages/outbound";
    }

    @GetMapping("/inbound")
    public String order(Model model) {
        model.addAttribute("title", "Inbound");
        return "pages/inbound";
    }

    @GetMapping("/inventory")
    public String inventory(Model model) {
        model.addAttribute("title", "Inventory");
        return "pages/inventory";
    }

    @GetMapping("/supplier")
    public String supplier(Model model) {
        model.addAttribute("title", "Supplier");
        return "pages/supplier";
    }

    @GetMapping("/report_and_analysis")
    public String reportAndAnalysis(Model model) {
        model.addAttribute("title", "Report & Analysis");
        return "pages/report_and_analysis";
    }

    @GetMapping("/user")
    public String user(Model model) {
        model.addAttribute("title", "User");
        return "pages/user";
    }

    @GetMapping("/setting")
    public String setting(Model model) {
        model.addAttribute("title", "Setting");
        return "pages/setting";
    }
}
