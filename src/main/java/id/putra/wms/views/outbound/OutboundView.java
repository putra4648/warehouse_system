package id.putra.wms.views.outbound;

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

import id.putra.wms.module.outbound.dto.SalesOrderDto;
import id.putra.wms.module.outbound.service.core.SalesOrderService;
import id.putra.wms.views.MainLayout;
import jakarta.annotation.security.PermitAll;

@Route(value = "outbound", layout = MainLayout.class)
@PageTitle("Outbound | WMS Pro")
@PermitAll
public class OutboundView extends VerticalLayout {

    private final SalesOrderService salesOrderService;
    private final Grid<SalesOrderDto> grid = new Grid<>(SalesOrderDto.class, false);
    private final TextField searchField = new TextField();

    public OutboundView(SalesOrderService salesOrderService) {
        this.salesOrderService = salesOrderService;
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
        H2 title = new H2("Outbound Management");
        title.addClassNames("m-0", "text-xl", "font-bold");
        Paragraph desc = new Paragraph("Manage customer sales orders, order picking, packing, and shipping.");
        desc.addClassNames("text-secondary", "m-0");
        add(title, desc);
    }

    private void addToolbar() {
        searchField.setPlaceholder("Search Sales Order number...");
        searchField.setClearButtonVisible(true);
        searchField.setPrefixComponent(VaadinIcon.SEARCH.create());
        searchField.addValueChangeListener(e -> refreshData());

        Button addButton = new Button("New Outbound Order", VaadinIcon.PLUS.create(), e -> openOrderDialog(new SalesOrderDto()));
        addButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        Button refreshButton = new Button(VaadinIcon.REFRESH.create(), e -> refreshData());

        HorizontalLayout toolbar = new HorizontalLayout(searchField, addButton, refreshButton);
        toolbar.setWidthFull();
        toolbar.setFlexGrow(1, searchField);
        add(toolbar);
    }

    private void configureGrid() {
        grid.setSizeFull();
        grid.addColumn(so -> so.getSoNumber() != null ? so.getSoNumber() : "").setHeader("SO Number").setAutoWidth(true).setSortable(true);
        grid.addColumn(so -> so.getOrderDate() != null ? so.getOrderDate().toString() : "-").setHeader("Order Date").setAutoWidth(true);
        grid.addColumn(so -> so.getCustomerId() != null ? "Customer #" + so.getCustomerId() : "-").setHeader("Customer").setFlexGrow(1);

        grid.addComponentColumn(so -> {
            String status = so.getStatus() != null ? so.getStatus() : "PENDING";
            Span badge = new Span(status);
            badge.getElement().getThemeList().add("badge " + ("SHIPPED".equals(status) ? "success" : "contrast"));
            return badge;
        }).setHeader("Status").setAutoWidth(true);

        grid.addComponentColumn(so -> {
            Button editBtn = new Button(VaadinIcon.EDIT.create(), e -> openOrderDialog(so));
            editBtn.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

            Button deleteBtn = new Button(VaadinIcon.TRASH.create(), e -> confirmDelete(so));
            deleteBtn.addThemeVariants(ButtonVariant.LUMO_TERTIARY, ButtonVariant.LUMO_ERROR);

            return new HorizontalLayout(editBtn, deleteBtn);
        }).setHeader("Actions").setAutoWidth(true);
    }

    private void refreshData() {
        var page = salesOrderService.getAll(searchField.getValue(), PageRequest.of(0, 100));
        grid.setItems(page.getContent());
    }

    private void openOrderDialog(SalesOrderDto order) {
        boolean isEdit = order.getId() != null;
        Dialog dialog = new Dialog(isEdit ? "Edit Outbound Order" : "New Outbound Order");

        TextField soNumField = new TextField("Sales Order Number");
        soNumField.setValue(order.getSoNumber() != null ? order.getSoNumber() : "");

        FormLayout form = new FormLayout(soNumField);
        dialog.add(form);

        Button saveBtn = new Button("Save Order", e -> {
            order.setSoNumber(soNumField.getValue());
            try {
                if (isEdit) {
                    salesOrderService.update(order);
                } else {
                    salesOrderService.create(order);
                }
                Notification.show("Sales order saved successfully", 3000, Notification.Position.TOP_END)
                        .addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                dialog.close();
                refreshData();
            } catch (Exception ex) {
                Notification.show("Failed to save order: " + ex.getMessage(), 4000, Notification.Position.TOP_END)
                        .addThemeVariants(NotificationVariant.LUMO_ERROR);
            }
        });
        saveBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        dialog.getFooter().add(new Button("Cancel", e -> dialog.close()), saveBtn);
        dialog.open();
    }

    private void confirmDelete(SalesOrderDto order) {
        Dialog confirmDialog = new Dialog("Delete Outbound Order");
        confirmDialog.add(new Paragraph("Are you sure you want to delete sales order '" + order.getSoNumber() + "'?"));

        Button deleteBtn = new Button("Delete", e -> {
            try {
                salesOrderService.delete(order.getId());
                Notification.show("Sales order deleted successfully", 3000, Notification.Position.TOP_END)
                        .addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                confirmDialog.close();
                refreshData();
            } catch (Exception ex) {
                Notification.show("Failed to delete order: " + ex.getMessage(), 4000, Notification.Position.TOP_END)
                        .addThemeVariants(NotificationVariant.LUMO_ERROR);
            }
        });
        deleteBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_ERROR);

        confirmDialog.getFooter().add(new Button("Cancel", e -> confirmDialog.close()), deleteBtn);
        confirmDialog.open();
    }
}
