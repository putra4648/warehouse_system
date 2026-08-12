package id.putra.wms.views.inbound;

import org.springframework.data.domain.PageRequest;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import id.putra.wms.module.inbound.dto.PurchaseOrderDto;
import id.putra.wms.module.inbound.service.core.PurchaseOrderService;
import id.putra.wms.views.MainLayout;
import jakarta.annotation.security.PermitAll;

@Route(value = "inbound/purchase-order", layout = MainLayout.class)
@PageTitle("Purchase Orders | WMS Pro")
@PermitAll
public class PurchaseOrderView extends VerticalLayout {

    private final PurchaseOrderService purchaseOrderService;
    private final Grid<PurchaseOrderDto> grid = new Grid<>(PurchaseOrderDto.class, false);
    private final TextField searchField = new TextField();

    public PurchaseOrderView(PurchaseOrderService purchaseOrderService) {
        this.purchaseOrderService = purchaseOrderService;
        setSizeFull();
        setPadding(true);
        setSpacing(true);

        addHeader();
        addToolbar();
        configureGrid();
        add(grid);

        refreshData();
    }

    private void addHeader() {
        H2 title = new H2("Purchase Orders");
        title.addClassNames("m-0", "text-xl", "font-bold");
        Paragraph desc = new Paragraph("Track inbound vendor purchase orders and item lines.");
        desc.addClassNames("text-secondary", "m-0");
        add(title, desc);
    }

    private void addToolbar() {
        searchField.setPlaceholder("Search PO number...");
        searchField.setClearButtonVisible(true);
        searchField.setPrefixComponent(VaadinIcon.SEARCH.create());
        searchField.addValueChangeListener(e -> refreshData());

        Button addButton = new Button("New Purchase Order", VaadinIcon.PLUS.create(), e -> openPoDialog(new PurchaseOrderDto()));
        addButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        Button refreshButton = new Button(VaadinIcon.REFRESH.create(), e -> refreshData());

        HorizontalLayout toolbar = new HorizontalLayout(searchField, addButton, refreshButton);
        toolbar.setWidthFull();
        toolbar.setFlexGrow(1, searchField);
        add(toolbar);
    }

    private void configureGrid() {
        grid.setSizeFull();
        grid.addColumn(p -> p.getPoNumber() != null ? p.getPoNumber() : "").setHeader("PO Number").setAutoWidth(true).setSortable(true);
        grid.addColumn(p -> p.getOrderDate() != null ? p.getOrderDate().toString() : "-").setHeader("Order Date").setAutoWidth(true);
        grid.addColumn(p -> p.getSupplierId() != null ? "Supplier #" + p.getSupplierId() : "-").setHeader("Supplier").setFlexGrow(1);

        grid.addComponentColumn(po -> {
            String status = po.getStatus() != null ? po.getStatus().name() : "DRAFT";
            Span badge = new Span(status);
            badge.getElement().getThemeList().add("badge " + ("APPROVED".equals(status) ? "success" : "neutral"));
            return badge;
        }).setHeader("Status").setAutoWidth(true);

        grid.addComponentColumn(po -> {
            Button viewBtn = new Button(VaadinIcon.EYE.create(), e -> openDetailsDialog(po));
            viewBtn.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

            Button deleteBtn = new Button(VaadinIcon.TRASH.create(), e -> confirmDelete(po));
            deleteBtn.addThemeVariants(ButtonVariant.LUMO_TERTIARY, ButtonVariant.LUMO_ERROR);

            return new HorizontalLayout(viewBtn, deleteBtn);
        }).setHeader("Actions").setAutoWidth(true);
    }

    private void refreshData() {
        PurchaseOrderDto searchParam = new PurchaseOrderDto();
        searchParam.setPoNumber(searchField.getValue());
        var page = purchaseOrderService.getAll(searchParam, PageRequest.of(0, 100));
        grid.setItems(page.getContent());
    }

    private void openPoDialog(PurchaseOrderDto po) {
        Dialog dialog = new Dialog("New Purchase Order");
        TextField poNumField = new TextField("PO Number");
        poNumField.setRequired(true);

        FormLayout form = new FormLayout(poNumField);
        dialog.add(form);

        Button saveBtn = new Button("Create PO", e -> {
            po.setPoNumber(poNumField.getValue());
            try {
                purchaseOrderService.create(po);
                Notification.show("PO created successfully", 3000, Notification.Position.TOP_END)
                        .addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                dialog.close();
                refreshData();
            } catch (Exception ex) {
                Notification.show("Failed to create PO: " + ex.getMessage(), 4000, Notification.Position.TOP_END)
                        .addThemeVariants(NotificationVariant.LUMO_ERROR);
            }
        });
        saveBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        dialog.getFooter().add(new Button("Cancel", e -> dialog.close()), saveBtn);
        dialog.open();
    }

    private void openDetailsDialog(PurchaseOrderDto po) {
        Dialog dialog = new Dialog("Purchase Order: " + po.getPoNumber());
        dialog.setWidth("600px");
        
        VerticalLayout layout = new VerticalLayout();
        layout.add(new Paragraph("PO Number: " + po.getPoNumber()));
        layout.add(new Paragraph("Status: " + (po.getStatus() != null ? po.getStatus().name() : "DRAFT")));

        dialog.add(layout);
        dialog.getFooter().add(new Button("Close", e -> dialog.close()));
        dialog.open();
    }

    private void confirmDelete(PurchaseOrderDto po) {
        Dialog confirmDialog = new Dialog("Delete Purchase Order");
        confirmDialog.add(new Paragraph("Are you sure you want to delete PO '" + po.getPoNumber() + "'?"));

        Button deleteBtn = new Button("Delete", e -> {
            try {
                purchaseOrderService.delete(po.getId());
                Notification.show("PO deleted successfully", 3000, Notification.Position.TOP_END)
                        .addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                confirmDialog.close();
                refreshData();
            } catch (Exception ex) {
                Notification.show("Failed to delete PO: " + ex.getMessage(), 4000, Notification.Position.TOP_END)
                        .addThemeVariants(NotificationVariant.LUMO_ERROR);
            }
        });
        deleteBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_ERROR);

        confirmDialog.getFooter().add(new Button("Cancel", e -> confirmDialog.close()), deleteBtn);
        confirmDialog.open();
    }
}
