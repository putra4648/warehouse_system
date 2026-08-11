package id.putra.wms.views.master;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
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
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import id.putra.wms.module.inventory.service.core.ProductCoreService;
import id.putra.wms.shared.base.dto.ProductDto;
import id.putra.wms.views.MainLayout;
import jakarta.annotation.security.PermitAll;
import org.springframework.data.domain.PageRequest;

@Route(value = "master/products", layout = MainLayout.class)
@PageTitle("Products | WMS Pro")
@PermitAll
public class ProductView extends VerticalLayout {

    private final ProductCoreService productService;
    private final Grid<ProductDto> grid = new Grid<>(ProductDto.class, false);
    private final TextField searchField = new TextField();

    public ProductView(ProductCoreService productService) {
        this.productService = productService;
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
        H2 title = new H2("Product Catalog");
        title.addClassNames("m-0", "text-xl", "font-bold");
        Paragraph desc = new Paragraph("Manage product items, SKU details, and stock thresholds.");
        desc.addClassNames("text-secondary", "m-0");
        add(title, desc);
    }

    private void addToolbar() {
        searchField.setPlaceholder("Search products by name or SKU...");
        searchField.setClearButtonVisible(true);
        searchField.setPrefixComponent(VaadinIcon.SEARCH.create());
        searchField.addValueChangeListener(e -> refreshData());

        Button addButton = new Button("Add Product", VaadinIcon.PLUS.create(), e -> openProductDialog(ProductDto.builder().build()));
        addButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        Button refreshButton = new Button(VaadinIcon.REFRESH.create(), e -> refreshData());

        HorizontalLayout toolbar = new HorizontalLayout(searchField, addButton, refreshButton);
        toolbar.setWidthFull();
        toolbar.setFlexGrow(1, searchField);
        add(toolbar);
    }

    private void configureGrid() {
        grid.setSizeFull();
        grid.addColumn(ProductDto::getSku).setHeader("SKU").setAutoWidth(true).setSortable(true);
        grid.addColumn(ProductDto::getName).setHeader("Product Name").setFlexGrow(1).setSortable(true);
        grid.addColumn(p -> p.getQuantity() != null ? p.getQuantity() : 0).setHeader("Total Stock").setAutoWidth(true);
        grid.addColumn(p -> p.getMinStock() != null ? p.getMinStock() : 0).setHeader("Min Stock").setAutoWidth(true);
        grid.addColumn(p -> p.getMaxStock() != null ? p.getMaxStock() : 0).setHeader("Max Stock").setAutoWidth(true);

        grid.addComponentColumn(product -> {
            boolean active = Boolean.TRUE.equals(product.getIsActive());
            Span badge = new Span(active ? "Active" : "Inactive");
            badge.getElement().getThemeList().add("badge " + (active ? "success" : "error"));
            return badge;
        }).setHeader("Status").setAutoWidth(true);

        grid.addComponentColumn(product -> {
            Button editBtn = new Button(VaadinIcon.EDIT.create(), e -> openProductDialog(product));
            editBtn.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

            Button deleteBtn = new Button(VaadinIcon.TRASH.create(), e -> confirmDelete(product));
            deleteBtn.addThemeVariants(ButtonVariant.LUMO_TERTIARY, ButtonVariant.LUMO_ERROR);

            return new HorizontalLayout(editBtn, deleteBtn);
        }).setHeader("Actions").setAutoWidth(true);
    }

    private void refreshData() {
        ProductDto searchParam = ProductDto.builder().name(searchField.getValue()).build();
        var page = productService.getProducts(searchParam, PageRequest.of(0, 100));
        grid.setItems(page.getContent());
    }

    private void openProductDialog(ProductDto product) {
        boolean isEdit = product.getId() != null;
        Dialog dialog = new Dialog(isEdit ? "Edit Product" : "Add Product");

        Binder<ProductDto> binder = new BeanValidationBinder<>(ProductDto.class);

        TextField skuField = new TextField("SKU");
        TextField nameField = new TextField("Product Name");
        TextArea descField = new TextArea("Description");

        IntegerField qtyField = new IntegerField("Quantity");
        IntegerField minStockField = new IntegerField("Min Stock");
        IntegerField maxStockField = new IntegerField("Max Stock");
        Checkbox activeCheck = new Checkbox("Is Active");

        binder.forField(skuField).asRequired("SKU is required").bind(ProductDto::getSku, ProductDto::setSku);
        binder.forField(nameField).asRequired("Name is required").bind(ProductDto::getName, ProductDto::setName);
        binder.forField(descField).bind(ProductDto::getDescription, ProductDto::setDescription);
        binder.forField(activeCheck).bind(ProductDto::getIsActive, ProductDto::setIsActive);

        skuField.setValue(product.getSku() != null ? product.getSku() : "");
        nameField.setValue(product.getName() != null ? product.getName() : "");
        descField.setValue(product.getDescription() != null ? product.getDescription() : "");
        qtyField.setValue(product.getQuantity() != null ? product.getQuantity().intValue() : 0);
        minStockField.setValue(product.getMinStock() != null ? product.getMinStock().intValue() : 0);
        maxStockField.setValue(product.getMaxStock() != null ? product.getMaxStock().intValue() : 0);
        activeCheck.setValue(product.getIsActive() != null ? product.getIsActive() : true);

        FormLayout formLayout = new FormLayout(skuField, nameField, qtyField, minStockField, maxStockField, activeCheck, descField);
        dialog.add(formLayout);

        Button saveBtn = new Button("Save", e -> {
            if (binder.writeBeanIfValid(product)) {
                product.setQuantity(qtyField.getValue() != null ? qtyField.getValue().longValue() : 0L);
                product.setMinStock(minStockField.getValue() != null ? minStockField.getValue().longValue() : 0L);
                product.setMaxStock(maxStockField.getValue() != null ? maxStockField.getValue().longValue() : 0L);

                try {
                    if (isEdit) {
                        productService.update(product);
                        Notification.show("Product updated successfully", 3000, Notification.Position.TOP_END)
                                .addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                    } else {
                        productService.save(product);
                        Notification.show("Product created successfully", 3000, Notification.Position.TOP_END)
                                .addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                    }
                    dialog.close();
                    refreshData();
                } catch (Exception ex) {
                    Notification.show("Failed to save product: " + ex.getMessage(), 4000, Notification.Position.TOP_END)
                            .addThemeVariants(NotificationVariant.LUMO_ERROR);
                }
            }
        });
        saveBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        Button cancelBtn = new Button("Cancel", e -> dialog.close());

        dialog.getFooter().add(cancelBtn, saveBtn);
        dialog.open();
    }

    private void confirmDelete(ProductDto product) {
        Dialog confirmDialog = new Dialog("Delete Product");
        confirmDialog.add(new Paragraph("Are you sure you want to delete product '" + product.getName() + "'?"));

        Button deleteBtn = new Button("Delete", e -> {
            try {
                productService.delete(product.getId());
                Notification.show("Product deleted successfully", 3000, Notification.Position.TOP_END)
                        .addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                confirmDialog.close();
                refreshData();
            } catch (Exception ex) {
                Notification.show("Failed to delete product: " + ex.getMessage(), 4000, Notification.Position.TOP_END)
                        .addThemeVariants(NotificationVariant.LUMO_ERROR);
            }
        });
        deleteBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_ERROR);

        Button cancelBtn = new Button("Cancel", e -> confirmDialog.close());

        confirmDialog.getFooter().add(cancelBtn, deleteBtn);
        confirmDialog.open();
    }
}
