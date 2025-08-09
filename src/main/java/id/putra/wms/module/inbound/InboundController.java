package id.putra.wms.module.inbound;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import id.putra.wms.component.MessageBox;

@Controller
public class InboundController {
    @GetMapping("/inbound")
    public String order(Model model) {
        var msgbox = new ArrayList<MessageBox>();
        msgbox.add(new MessageBox("fas fa-cubes", "Pending Shipments", 2L, "text-bg-primary"));
        msgbox.add(new MessageBox("fas fa-exclamation-triangle", "Completed Today", 10_000L, "text-bg-success"));
        msgbox.add(new MessageBox("fas fa-cubes", "Expected Today", 20_000L, "text-bg-warning"));

        model.addAttribute("msgbox", msgbox);
        return "pages/inbound";
    }
}
