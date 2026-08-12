package id.putra.wms.views.reports;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.streams.DownloadHandler;
import com.vaadin.flow.server.streams.DownloadResponse;

import id.putra.wms.views.MainLayout;
import jakarta.annotation.security.PermitAll;

@Route(value = "reports", layout = MainLayout.class)
@PageTitle("Reports | WMS Pro")
@PermitAll
public class ReportsView extends VerticalLayout {

    public ReportsView() {
        setSizeFull();
        setPadding(true);
        setSpacing(true);

        addHeader();
        addReportGeneratorSection();
    }

    private void addHeader() {
        H2 title = new H2("Reports & Analytics");
        title.addClassNames("m-0", "text-xl", "font-bold");
        Paragraph desc = new Paragraph("Generate and download warehouse inventory and order fulfillment reports.");
        desc.addClassNames("text-secondary", "m-0");
        add(title, desc);
    }

    private void addReportGeneratorSection() {
        ComboBox<String> reportType = new ComboBox<>("Report Type");
        reportType.setItems("Inventory Stock Valuation", "Inbound Receipts Summary", "Outbound Fulfillment Rate",
                "Stock Audit History");
        reportType.setValue("Inventory Stock Valuation");
        reportType.setRequired(true);

        DatePicker startDate = new DatePicker("Start Date", LocalDate.now().minusDays(30));
        DatePicker endDate = new DatePicker("End Date", LocalDate.now());

        FormLayout form = new FormLayout(reportType, startDate, endDate);
        form.setMaxWidth("600px");

        Button generateBtn = new Button("Generate Preview", VaadinIcon.FILE_TEXT.create(), e -> {
            Notification.show("Generated preview for " + reportType.getValue(), 3000, Notification.Position.TOP_END)
                    .addThemeVariants(NotificationVariant.LUMO_SUCCESS);
        });
        generateBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        
        DownloadHandler downloadHandler = DownloadHandler.fromInputStream((event) -> {
            String csvContent = "Report Type,Start Date,End Date,Generated At\n" +
                    "\"" + reportType.getValue() + "\",\"" + startDate.getValue() + "\",\"" + endDate.getValue()
                    + "\",\"" + LocalDate.now() + "\"\n";
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
                    csvContent.getBytes(StandardCharsets.UTF_8));
            return new DownloadResponse(byteArrayInputStream, "wms_report.csv", "text/csv", -1);
        });

        Anchor downloadLink = new Anchor(downloadHandler, "Export CSV");
        downloadLink.getElement().setAttribute("download", true);
        Button downloadBtn = new Button("Export CSV", VaadinIcon.DOWNLOAD.create());
        downloadLink.removeAll();
        downloadLink.add(downloadBtn);

        HorizontalLayout actions = new HorizontalLayout(generateBtn, downloadLink);
        actions.setAlignItems(Alignment.CENTER);

        VerticalLayout container = new VerticalLayout(form, actions);
        container.addClassNames("border", "rounded-l", "p-l", "bg-base");
        container.setMaxWidth("650px");

        add(container);
    }
}
