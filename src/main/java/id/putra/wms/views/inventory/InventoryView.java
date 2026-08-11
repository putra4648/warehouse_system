package id.putra.wms.views.inventory;

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
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import id.putra.wms.module.inventory.dto.InventoryItemDto;
import id.putra.wms.module.inventory.service.core.InventoryItemCoreService;
import id.putra.wms.views.MainLayout;
import jakarta.annotation.security.PermitAll;
import org.springframework.data.domain.PageRequest;

import java.util.List;

@Route(value = "inventory", layout = MainLayout.class)
@PageTitle("Inventory | WMS Pro")
@PermitAll
public class InventoryView extends VerticalLayout {

    private final InventoryItemCoreService inventoryService;
    private final Grid<InventoryItemDto> grid = new Grid<>(InventoryItemDto.class, false);
    private final TextField searchField = new TextField();

    public InventoryView(InventoryItemCoreService inventoryService) {
        this.inventoryService = inventoryService;
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
        H2 title = new H2("Inventory Management");
        title.addClassNames("m-0", "text-xl", "font-bold");
        Paragraph desc = new Paragraph("Track stock quantities, lot numbers, serials, and reserved stock.");
        desc.addClassNames("text-secondary", "m-0");
        add(title, desc);
    }

    private void addToolbar() {
        searchField.setPlaceholder("Search item name or lot number...");
        searchField.setClearButtonVisible(true);
        searchField.setPrefixComponent(VaadinIcon.SEARCH.create());
        searchField.addValueChangeListener(e -> refreshData());

        Button addButton = new Button("Add Stock Item", VaadinIcon.PLUS.create(), e -> openItemDialog(InventoryItemDto.builder().build()));
        addButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        Button refreshButton = new Button(VaadinIcon.REFRESH.create(), e -> refreshData());

        HorizontalLayout toolbar = new HorizontalLayout(searchField, addButton, refreshButton);
        toolbar.setWidthFull();
        toolbar.setFlexGrow(1, searchField);
        add(toolbar);
    }

    private void configureGrid() {
        grid.setSizeFull();
        grid.addColumn(InventoryItemDto::getName).setHeader("Item Name").setFlexGrow(1).setSortable(true);
        grid.addColumn(item -> item.getProduct() != null ? item.getProduct().getSku() : "-").setHeader("Product SKU").setAutoWidth(true);
        grid.addColumn(InventoryItemDto::getLotNumber).setHeader("Lot Number").setAutoWidth(true);
        grid.addColumn(InventoryItemDto::getSerialNumber).setHeader("Serial Number").setAutoWidth(true);
        grid.addColumn(item -> item.getQuantity() != null ? item.getQuantity() : 0).setHeader("Quantity").setAutoWidth(true);
        grid.addColumn(item -> item.getReservedQty() != null ? item.getReservedQty() : 0).setHeader("Reserved Qty").setAutoWidth(true);

        grid.addComponentColumn(item -> {
            String status = item.getStatus() != null ? item.getStatus() : "AVAILABLE";
            Span badge = new Span(status);
            badge.getElement().getThemeList().add("badge " + ("AVAILABLE".equalsIgnoreCase(status) ? "success" : "contrast"));
            return badge;
        }).setHeader("Status").setAutoWidth(true);

        grid.addComponentColumn(item -> {
            Button editBtn = new Button(VaadinIcon.EDIT.create(), e -> openItemDialog(item));
            editBtn.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

            Button deleteBtn = new Button(VaadinIcon.TRASH.create(), e -> confirmDelete(item));
            deleteBtn.addThemeVariants(ButtonVariant.LUMO_TERTIARY, ButtonVariant.LUMO_ERROR);

            return new HorizontalLayout(editBtn, deleteBtn);
        }).setHeader("Actions").setAutoWidth(true);
    }

    private void refreshData() {
        InventoryItemDto filter = InventoryItemDto.builder().name(searchField.getValue()).build();
        var page = inventoryService.getInventoryItems(filter, PageRequest.of(0, 100));
        grid.setItems(page.getContent());
    }

    private void openItemDialog(InventoryItemDto item) {
        boolean isEdit = item.getId() != null;
        Dialog dialog = new Dialog(isEdit ? "Edit Stock Item" : "Add Stock Item");

        TextField nameField = new TextField("Item Name");
        TextField lotField = new TextField("Lot Number");
        TextField serialField = new TextField("Serial Number");
        IntegerField qtyField = new IntegerField("Quantity");
        IntegerField reservedField = new IntegerField("Reserved Qty");

        nameField.setValue(item.getName() != null ? item.getName() : "");
        lotField.setValue(item.getLotNumber() != null ? item.getLotNumber() : "");
        serialField.setValue(item.getSerialNumber() != null ? item.getSerialNumber() : "");
        qtyField.setValue(item.getQuantity() != null ? item.getQuantity().intValue() : 0);
        reservedField.setValue(item.getReservedQty() != null ? item.getReservedQty().intValue() : 0);

        FormLayout form = new FormLayout(nameField, lotField, serialField, qtyField, reservedField);
        dialog.add(form);

        Button saveBtn = new Button("Save", e -> {
            InventoryItemDto dto = item.getId() != null ? item : InventoryItemDto.builder().build();
            dto.setName(nameField.getValue());
            dto.setLotNumber(lotField.getValue());
            dto.setSerialNumber(serialField.getValue());
            dto.setQuantity(qtyField.getValue() != null ? qtyField.getValue().longValue() : 0L);
            dto.setReservedQty(reservedField.getValue() != null ? reservedField.getValue().longValue() : 0L);
            dto.setStatus("AVAILABLE");

            try {
                if (isEdit) {
                    inventoryService.update(List.of(dto));
                } else {
                    inventoryService.save(List.of(dto));
                }
                Notification.show("Stock item saved successfully", 3000, Notification.Position.TOP_END)
                        .addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                dialog.close();
                refreshData();
            } catch (Exception ex) {
                Notification.show("Failed to save inventory: " + ex.getMessage(), 4000, Notification.Position.TOP_END)
                        .addThemeVariants(NotificationVariant.LUMO_ERROR);
            }
        });
        saveBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        dialog.getFooter().add(new Button("Cancel", e -> dialog.close()), saveBtn);
        dialog.open();
    }

    private void confirmDelete(InventoryItemDto item) {
        Dialog confirmDialog = new Dialog("Delete Stock Item");
        confirmDialog.add(new Paragraph("Are you sure you want to delete inventory item '" + item.getName() + "'?"));

        Button deleteBtn = new Button("Delete", e -> {
            try {
                inventoryService.delete(List.of(item));
                Notification.show("Stock item deleted successfully", 3000, Notification.Position.TOP_END)
                        .addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                confirmDialog.close();
                refreshData();
            } catch (Exception ex) {
                Notification.show("Failed to delete stock item: " + ex.getMessage(), 4000, Notification.Position.TOP_END)
                        .addThemeVariants(NotificationVariant.LUMO_ERROR);
            }
        });
        deleteBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_ERROR);

        confirmDialog.getFooter().add(new Button("Cancel", e -> confirmDialog.close()), deleteBtn);
        confirmDialog.open();
    }
}
