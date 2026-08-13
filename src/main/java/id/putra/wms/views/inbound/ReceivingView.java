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

import id.putra.wms.module.inbound.dto.ReceivingDto;
import id.putra.wms.module.inbound.service.core.ReceivingOrderService;
import id.putra.wms.views.MainLayout;
import jakarta.annotation.security.PermitAll;

@Route(value = "inbound/receiving", layout = MainLayout.class)
@PageTitle("Receiving | WMS Pro")
@PermitAll
public class ReceivingView extends VerticalLayout {

    private final ReceivingOrderService receivingOrderService;
    private final Grid<ReceivingDto> grid = new Grid<>(ReceivingDto.class, false);
    private final TextField searchField = new TextField();

    public ReceivingView(ReceivingOrderService receivingOrderService) {
        this.receivingOrderService = receivingOrderService;
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
        H2 title = new H2("Goods Receiving");
        title.addClassNames("m-0", "text-xl", "font-bold");
        Paragraph desc = new Paragraph("Process inbound goods receipts and assign storage locations.");
        desc.addClassNames("text-secondary", "m-0");
        add(title, desc);
    }

    private void addToolbar() {
        searchField.setPlaceholder("Search receiving number...");
        searchField.setClearButtonVisible(true);
        searchField.setPrefixComponent(VaadinIcon.SEARCH.create());
        searchField.addValueChangeListener(e -> refreshData());

        Button addButton = new Button("New Goods Receipt", VaadinIcon.PLUS.create(), e -> openReceivingDialog(new ReceivingDto()));
        addButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        Button refreshButton = new Button(VaadinIcon.REFRESH.create(), e -> refreshData());

        HorizontalLayout toolbar = new HorizontalLayout(searchField, addButton, refreshButton);
        toolbar.setWidthFull();
        toolbar.setFlexGrow(1, searchField);
        add(toolbar);
    }

    private void configureGrid() {
        grid.setSizeFull();
        grid.addColumn(r -> r.getReceivingNumber() != null ? r.getReceivingNumber() : "").setHeader("Receiving #").setAutoWidth(true).setSortable(true);
        grid.addColumn(r -> r.getReceivedDate() != null ? r.getReceivedDate().toString() : "-").setHeader("Received Date").setAutoWidth(true);
        grid.addColumn(r -> r.getPurchaseOrder() != null ? r.getPurchaseOrder().getPoNumber() : "-").setHeader("PO Number").setFlexGrow(1);

        grid.addComponentColumn(receiving -> {
            String status = receiving.getStatus() != null ? receiving.getStatus() : "COMPLETED";
            Span badge = new Span(status);
            badge.getElement().getThemeList().add("badge success");
            return badge;
        }).setHeader("Status").setAutoWidth(true);

        grid.addComponentColumn(receiving -> {
            Button editBtn = new Button(VaadinIcon.EDIT.create(), e -> openReceivingDialog(receiving));
            editBtn.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

            Button deleteBtn = new Button(VaadinIcon.TRASH.create(), e -> confirmDelete(receiving));
            deleteBtn.addThemeVariants(ButtonVariant.LUMO_TERTIARY, ButtonVariant.LUMO_ERROR);

            return new HorizontalLayout(editBtn, deleteBtn);
        }).setHeader("Actions").setAutoWidth(true);
    }

    private void refreshData() {
        var page = receivingOrderService.getAll(searchField.getValue(), PageRequest.of(0, 100));
        grid.setItems(page.getContent());
    }

    private void openReceivingDialog(ReceivingDto receiving) {
        boolean isEdit = receiving.getId() != null;
        Dialog dialog = new Dialog(isEdit ? "Edit Goods Receipt" : "New Goods Receipt");

        TextField recNumField = new TextField("Receiving Number");
        recNumField.setValue(receiving.getReceivingNumber() != null ? receiving.getReceivingNumber() : "");

        FormLayout form = new FormLayout(recNumField);
        dialog.add(form);

        Button saveBtn = new Button("Save", e -> {
            receiving.setReceivingNumber(recNumField.getValue());
            try {
                if (isEdit) {
                    receivingOrderService.update(receiving);
                } else {
                    receivingOrderService.create(receiving);
                }
                Notification.show("Receiving record saved successfully", 3000, Notification.Position.TOP_END)
                        .addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                dialog.close();
                refreshData();
            } catch (Exception ex) {
                Notification.show("Failed to save receiving: " + ex.getMessage(), 4000, Notification.Position.TOP_END)
                        .addThemeVariants(NotificationVariant.LUMO_ERROR);
            }
        });
        saveBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        dialog.getFooter().add(new Button("Cancel", e -> dialog.close()), saveBtn);
        dialog.open();
    }

    private void confirmDelete(ReceivingDto receiving) {
        Dialog confirmDialog = new Dialog("Delete Receiving");
        confirmDialog.add(new Paragraph("Are you sure you want to delete receiving record '" + receiving.getReceivingNumber() + "'?"));

        Button deleteBtn = new Button("Delete", e -> {
            try {
                receivingOrderService.delete(receiving.getId());
                Notification.show("Receiving deleted successfully", 3000, Notification.Position.TOP_END)
                        .addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                confirmDialog.close();
                refreshData();
            } catch (Exception ex) {
                Notification.show("Failed to delete receiving: " + ex.getMessage(), 4000, Notification.Position.TOP_END)
                        .addThemeVariants(NotificationVariant.LUMO_ERROR);
            }
        });
        deleteBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_ERROR);

        confirmDialog.getFooter().add(new Button("Cancel", e -> confirmDialog.close()), deleteBtn);
        confirmDialog.open();
    }
}
