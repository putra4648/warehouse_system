package id.putra.wms.views.master;

import java.util.List;

import org.springframework.data.domain.PageRequest;

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
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import id.putra.wms.module.customer.dto.ContactPersonCustomerDto;
import id.putra.wms.module.customer.dto.CustomerDto;
import id.putra.wms.module.customer.service.core.CustomerService;
import id.putra.wms.views.MainLayout;
import jakarta.annotation.security.PermitAll;

@Route(value = "master/customer", layout = MainLayout.class)
@PageTitle("Customers | WMS Pro")
@PermitAll
public class CustomerView extends VerticalLayout {

    private final CustomerService customerService;
    private final Grid<CustomerDto> grid = new Grid<>(CustomerDto.class, false);
    private final TextField searchField = new TextField();

    public CustomerView(CustomerService customerService) {
        this.customerService = customerService;
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
        H2 title = new H2("Customer Management");
        title.addClassNames("m-0", "text-xl", "font-bold");
        Paragraph desc = new Paragraph("Manage customer details and contact information.");
        desc.addClassNames("text-secondary", "m-0");
        add(title, desc);
    }

    private void addToolbar() {
        searchField.setPlaceholder("Search customers...");
        searchField.setClearButtonVisible(true);
        searchField.setPrefixComponent(VaadinIcon.SEARCH.create());
        searchField.addValueChangeListener(e -> refreshData());

        Button addButton = new Button("Add Customer", VaadinIcon.PLUS.create(), e -> openCustomerDialog(new CustomerDto()));
        addButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        Button refreshButton = new Button(VaadinIcon.REFRESH.create(), e -> refreshData());

        HorizontalLayout toolbar = new HorizontalLayout(searchField, addButton, refreshButton);
        toolbar.setWidthFull();
        toolbar.setFlexGrow(1, searchField);
        add(toolbar);
    }

    private void configureGrid() {
        grid.setSizeFull();
        grid.addColumn(c -> c.getCode() != null ? c.getCode() : "").setHeader("Customer Code").setAutoWidth(true).setSortable(true);
        grid.addColumn(c -> c.getName() != null ? c.getName() : "").setHeader("Customer Name").setFlexGrow(1).setSortable(true);
        grid.addColumn(c -> c.getContactPerson() != null ? c.getContactPerson().getName() : "-")
                .setHeader("Contact Person").setFlexGrow(1);
        grid.addColumn(c -> c.getContactPerson() != null ? c.getContactPerson().getEmail() : "-")
                .setHeader("Email").setFlexGrow(1);
        grid.addColumn(c -> c.getContactPerson() != null ? c.getContactPerson().getPhone() : "-")
                .setHeader("Phone").setAutoWidth(true);

        grid.addComponentColumn(customer -> {
            boolean active = Boolean.TRUE.equals(customer.getIsActive());
            Span badge = new Span(active ? "Active" : "Inactive");
            badge.getElement().getThemeList().add("badge " + (active ? "success" : "error"));
            return badge;
        }).setHeader("Status").setAutoWidth(true);

        grid.addComponentColumn(customer -> {
            Button editBtn = new Button(VaadinIcon.EDIT.create(), e -> openCustomerDialog(customer));
            editBtn.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

            Button deleteBtn = new Button(VaadinIcon.TRASH.create(), e -> confirmDelete(customer));
            deleteBtn.addThemeVariants(ButtonVariant.LUMO_TERTIARY, ButtonVariant.LUMO_ERROR);

            return new HorizontalLayout(editBtn, deleteBtn);
        }).setHeader("Actions").setAutoWidth(true);
    }

    private void refreshData() {
        var page = customerService.getAll(searchField.getValue(), PageRequest.of(0, 100));
        grid.setItems(page.getContent());
    }

    private void openCustomerDialog(CustomerDto customer) {
        boolean isEdit = customer.getId() != null;
        Dialog dialog = new Dialog(isEdit ? "Edit Customer" : "Add Customer");

        if (customer.getContactPerson() == null) {
            customer.setContactPerson(new ContactPersonCustomerDto());
        }

        Binder<CustomerDto> binder = new BeanValidationBinder<>(CustomerDto.class);

        TextField codeField = new TextField("Customer Code");
        TextField nameField = new TextField("Customer Name");
        Checkbox activeCheck = new Checkbox("Is Active");

        TextField cpNameField = new TextField("Contact Person Name");
        TextField cpEmailField = new TextField("Email");
        TextField cpPhoneField = new TextField("Phone");
        TextArea cpAddressField = new TextArea("Address");

        binder.forField(codeField).asRequired("Code is required").bind(CustomerDto::getCode, CustomerDto::setCode);
        binder.forField(nameField).asRequired("Name is required").bind(CustomerDto::getName, CustomerDto::setName);
        binder.forField(activeCheck).bind(CustomerDto::getIsActive, CustomerDto::setIsActive);

        codeField.setValue(customer.getCode() != null ? customer.getCode() : "");
        nameField.setValue(customer.getName() != null ? customer.getName() : "");
        activeCheck.setValue(customer.getIsActive() != null ? customer.getIsActive() : true);

        cpNameField.setValue(customer.getContactPerson().getName() != null ? customer.getContactPerson().getName() : "");
        cpEmailField.setValue(customer.getContactPerson().getEmail() != null ? customer.getContactPerson().getEmail() : "");
        cpPhoneField.setValue(customer.getContactPerson().getPhone() != null ? customer.getContactPerson().getPhone() : "");
        cpAddressField.setValue(customer.getContactPerson().getAddress() != null ? customer.getContactPerson().getAddress() : "");

        FormLayout formLayout = new FormLayout(codeField, nameField, activeCheck, cpNameField, cpEmailField, cpPhoneField, cpAddressField);
        dialog.add(formLayout);

        Button saveBtn = new Button("Save", e -> {
            if (binder.writeBeanIfValid(customer)) {
                customer.getContactPerson().setName(cpNameField.getValue());
                customer.getContactPerson().setEmail(cpEmailField.getValue());
                customer.getContactPerson().setPhone(cpPhoneField.getValue());
                customer.getContactPerson().setAddress(cpAddressField.getValue());

                try {
                    if (isEdit) {
                        customerService.update(customer.getId(), customer);
                        Notification.show("Customer updated successfully", 3000, Notification.Position.TOP_END)
                                .addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                    } else {
                        customerService.create(List.of(customer));
                        Notification.show("Customer created successfully", 3000, Notification.Position.TOP_END)
                                .addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                    }
                    dialog.close();
                    refreshData();
                } catch (Exception ex) {
                    Notification.show("Failed to save customer: " + ex.getMessage(), 4000, Notification.Position.TOP_END)
                            .addThemeVariants(NotificationVariant.LUMO_ERROR);
                }
            }
        });
        saveBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        Button cancelBtn = new Button("Cancel", e -> dialog.close());

        dialog.getFooter().add(cancelBtn, saveBtn);
        dialog.open();
    }

    private void confirmDelete(CustomerDto customer) {
        Dialog confirmDialog = new Dialog("Delete Customer");
        confirmDialog.add(new Paragraph("Are you sure you want to delete customer '" + customer.getName() + "'?"));

        Button deleteBtn = new Button("Delete", e -> {
            try {
                customerService.delete(customer.getId());
                Notification.show("Customer deleted successfully", 3000, Notification.Position.TOP_END)
                        .addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                confirmDialog.close();
                refreshData();
            } catch (Exception ex) {
                Notification.show("Failed to delete customer: " + ex.getMessage(), 4000, Notification.Position.TOP_END)
                        .addThemeVariants(NotificationVariant.LUMO_ERROR);
            }
        });
        deleteBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_ERROR);

        Button cancelBtn = new Button("Cancel", e -> confirmDialog.close());

        confirmDialog.getFooter().add(cancelBtn, deleteBtn);
        confirmDialog.open();
    }
}
