package id.putra.wms.views.help;

import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import id.putra.wms.views.MainLayout;
import jakarta.annotation.security.PermitAll;

@Route(value = "help", layout = MainLayout.class)
@PageTitle("Help & Support | WMS Pro")
@PermitAll
public class HelpView extends VerticalLayout {

    public HelpView() {
        setSizeFull();
        setPadding(true);
        setSpacing(true);

        addHeader();
        addHelpAccordion();
    }

    private void addHeader() {
        H2 title = new H2("Help & Documentation");
        title.addClassNames("m-0", "text-xl", "font-bold");
        Paragraph desc = new Paragraph("User guide and API reference documentation for Warehouse Management System.");
        desc.addClassNames("text-secondary", "m-0");
        add(title, desc);
    }

    private void addHelpAccordion() {
        Accordion accordion = new Accordion();
        accordion.setMaxWidth("700px");

        accordion.add("Master Data Management",
                new Paragraph("Manage master products, categories, suppliers, customers, and physical warehouse facilities (Zones, Racks, Locations, Bins)."));

        accordion.add("Inbound Operations",
                new Paragraph("Create Purchase Orders (POs) for vendors and log Goods Receiving notes to put away incoming inventory items."));

        accordion.add("Outbound & Shipping",
                new Paragraph("Process customer Sales Orders through picking, packing, and shipment dispatch workflows."));

        accordion.add("Swagger REST API Documentation",
                new Paragraph("External REST APIs remain accessible at /swagger-ui.html and /v3/api-docs for third-party integrations."));

        add(accordion);
    }
}
