package id.putra.wms.controller;

import java.util.ArrayList;
import java.util.stream.IntStream;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import id.putra.wms.component.MessageBox;

@Controller
public class SupplierController {
    @GetMapping("/supplier")
    public String supplier(Model model) {
        var msgbox = new ArrayList<MessageBox>();
        msgbox.add(new MessageBox("fas fa-industry", "Total Supplier", 20L, "text-bg-primary"));
        msgbox.add(new MessageBox("fas fa-industry", "Active", 10L, "text-bg-success"));
        msgbox.add(new MessageBox("fas fa-boxes-stacked", "Total Orders", 20_000L, "text-bg-warning"));

        model.addAttribute("msgbox", msgbox);
        model.addAttribute("results", IntStream.range(0, 100).toArray());

        return "pages/supplier";
    }
}
