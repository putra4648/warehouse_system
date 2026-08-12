package id.putra.wms.views.master;

import java.util.List;

import org.springframework.data.domain.PageRequest;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import id.putra.wms.module.inventory.dto.CategoryDto;
import id.putra.wms.module.inventory.service.core.CategoryCoreService;
import id.putra.wms.views.MainLayout;
import jakarta.annotation.security.PermitAll;

@Route(value = "master/categories", layout = MainLayout.class)
@PageTitle("Categories | WMS Pro")
@PermitAll
public class CategoryView extends VerticalLayout {

    private final CategoryCoreService categoryCoreService;
    private final Grid<CategoryDto> grid = new Grid<>(CategoryDto.class, false);
    private final TextField searchField = new TextField();

    public CategoryView(CategoryCoreService categoryCoreService) {
        this.categoryCoreService = categoryCoreService;
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
        H2 title = new H2("Category Management");
        title.addClassNames("m-0", "text-xl", "font-bold");
        Paragraph desc = new Paragraph("Manage product classification categories.");
        desc.addClassNames("text-secondary", "m-0");
        add(title, desc);
    }

    private void addToolbar() {
        searchField.setPlaceholder("Search categories...");
        searchField.setClearButtonVisible(true);
        searchField.setPrefixComponent(VaadinIcon.SEARCH.create());
        searchField.addValueChangeListener(e -> refreshData());

        Button addButton = new Button("Add Category", VaadinIcon.PLUS.create(), e -> openCategoryDialog(new CategoryDto()));
        addButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        Button refreshButton = new Button(VaadinIcon.REFRESH.create(), e -> refreshData());
        refreshButton.setTooltipText("Refresh");

        HorizontalLayout toolbar = new HorizontalLayout(searchField, addButton, refreshButton);
        toolbar.setWidthFull();
        toolbar.setFlexGrow(1, searchField);
        add(toolbar);
    }

    private void configureGrid() {
        grid.setSizeFull();
        grid.addColumn(c -> c.getId() != null ? c.getId().toString() : "").setHeader("ID").setAutoWidth(true);
        grid.addColumn(c -> c.getName() != null ? c.getName() : "").setHeader("Category Name").setSortable(true).setFlexGrow(1);
        grid.addColumn(c -> c.getDescription() != null ? c.getDescription() : "").setHeader("Description").setFlexGrow(2);

        grid.addComponentColumn(category -> {
            Button editBtn = new Button(VaadinIcon.EDIT.create(), e -> openCategoryDialog(category));
            editBtn.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

            Button deleteBtn = new Button(VaadinIcon.TRASH.create(), e -> confirmDelete(category));
            deleteBtn.addThemeVariants(ButtonVariant.LUMO_TERTIARY, ButtonVariant.LUMO_ERROR);

            return new HorizontalLayout(editBtn, deleteBtn);
        }).setHeader("Actions").setAutoWidth(true);
    }

    private void refreshData() {
        CategoryDto searchFilter = CategoryDto.builder().name(searchField.getValue()).build();
        var page = categoryCoreService.getCategories(searchFilter, PageRequest.of(0, 100));
        grid.setItems(page.getContent());
    }

    private void openCategoryDialog(CategoryDto category) {
        boolean isEdit = category.getId() != null;
        Dialog dialog = new Dialog(isEdit ? "Edit Category" : "Add Category");

        Binder<CategoryDto> binder = new BeanValidationBinder<>(CategoryDto.class);

        TextField nameField = new TextField("Category Name");
        nameField.setRequired(true);
        TextArea descField = new TextArea("Description");

        binder.forField(nameField).asRequired("Name is required").bind(CategoryDto::getName, CategoryDto::setName);
        binder.forField(descField).bind(CategoryDto::getDescription, CategoryDto::setDescription);
        binder.readBean(category);

        FormLayout formLayout = new FormLayout(nameField, descField);
        dialog.add(formLayout);

        Button saveBtn = new Button("Save", e -> {
            if (binder.writeBeanIfValid(category)) {
                try {
                    if (isEdit) {
                        categoryCoreService.update(List.of(category));
                        Notification.show("Category updated successfully", 3000, Notification.Position.TOP_END)
                                .addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                    } else {
                        categoryCoreService.save(List.of(category));
                        Notification.show("Category created successfully", 3000, Notification.Position.TOP_END)
                                .addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                    }
                    dialog.close();
                    refreshData();
                } catch (Exception ex) {
                    Notification.show("Failed to save category: " + ex.getMessage(), 4000, Notification.Position.TOP_END)
                            .addThemeVariants(NotificationVariant.LUMO_ERROR);
                }
            }
        });
        saveBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        Button cancelBtn = new Button("Cancel", e -> dialog.close());

        dialog.getFooter().add(cancelBtn, saveBtn);
        dialog.open();
    }

    private void confirmDelete(CategoryDto category) {
        Dialog confirmDialog = new Dialog("Delete Category");
        confirmDialog.add(new Paragraph("Are you sure you want to delete category '" + category.getName() + "'?"));

        Button deleteBtn = new Button("Delete", e -> {
            try {
                categoryCoreService.delete(List.of(category));
                Notification.show("Category deleted successfully", 3000, Notification.Position.TOP_END)
                        .addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                confirmDialog.close();
                refreshData();
            } catch (Exception ex) {
                Notification.show("Failed to delete category: " + ex.getMessage(), 4000, Notification.Position.TOP_END)
                        .addThemeVariants(NotificationVariant.LUMO_ERROR);
            }
        });
        deleteBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_ERROR);

        Button cancelBtn = new Button("Cancel", e -> confirmDialog.close());

        confirmDialog.getFooter().add(cancelBtn, deleteBtn);
        confirmDialog.open();
    }
}
