package id.putra.wms.controller;

import java.util.ArrayList;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import id.putra.wms.component.MessageBoxV2;
import id.putra.wms.enums.MessageBoxDataType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class ReportAndAnalytics {
        @GetMapping("/report_and_analysis")
        public String reportAndAnalysis(Model model, HttpServletRequest request, HttpServletResponse response) {

                response.setHeader(HttpHeaders.ACCEPT_LANGUAGE, "en-ID");

                var msgbox = new ArrayList<MessageBoxV2>();
                msgbox.add(new MessageBoxV2("fas fa-dollar-sign", "Total Revenue", 2_500_000L, MessageBoxDataType.MONEY,
                                "text-bg-primary",
                                "12% vs last month"));
                msgbox.add(new MessageBoxV2("fas fa-truck-ramp-box", "Order Processed", 10_000L,
                                MessageBoxDataType.VALUES, "text-bg-success",
                                "12% vs last month"));
                msgbox.add(
                                new MessageBoxV2("fas fa-hourglass", "Avg. Proccess Time", 2.567D,
                                                MessageBoxDataType.HOURS, "text-bg-warning",
                                                "12% vs last month"));
                msgbox.add(new MessageBoxV2("fas fa-bullseye", "Accuracy Rate", 50L, MessageBoxDataType.PERCENTAGE,
                                "text-bg-warning", "12% vs last month"));

                model.addAttribute("msgbox", msgbox);
                return "pages/report_and_analysis";
        }

}
