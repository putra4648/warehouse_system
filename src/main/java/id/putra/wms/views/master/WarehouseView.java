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
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import id.putra.wms.module.warehouse.dto.LocationDto;
import id.putra.wms.module.warehouse.dto.RackDto;
import id.putra.wms.module.warehouse.dto.WarehouseDto;
import id.putra.wms.module.warehouse.dto.ZoneDto;
import id.putra.wms.module.warehouse.service.core.LocationCoreService;
import id.putra.wms.module.warehouse.service.core.RackCoreService;
import id.putra.wms.module.warehouse.service.core.WarehouseCoreService;
import id.putra.wms.module.warehouse.service.core.ZoneCoreService;
import id.putra.wms.views.MainLayout;
import jakarta.annotation.security.PermitAll;

@Route(value = "master/warehouses", layout = MainLayout.class)
@PageTitle("Warehouses | WMS Pro")
@PermitAll
public class WarehouseView extends VerticalLayout {

    private final WarehouseCoreService warehouseService;
    private final ZoneCoreService zoneService;
    private final RackCoreService rackService;
    private final LocationCoreService locationService;

    private final VerticalLayout contentArea = new VerticalLayout();
    private final Tabs tabs = new Tabs();
    private final Tab tabWarehouses = new Tab("Warehouses");
    private final Tab tabZones = new Tab("Zones");
    private final Tab tabRacks = new Tab("Racks");
    private final Tab tabLocations = new Tab("Locations");

    public WarehouseView(WarehouseCoreService warehouseService, ZoneCoreService zoneService,
                         RackCoreService rackService, LocationCoreService locationService) {
        this.warehouseService = warehouseService;
        this.zoneService = zoneService;
        this.rackService = rackService;
        this.locationService = locationService;

        setSizeFull();
        setPadding(true);
        setSpacing(true);

        addHeader();
        configureTabs();
        
        contentArea.setSizeFull();
        contentArea.setPadding(false);
        add(tabs, contentArea);

        showWarehousesPanel();
    }

    private void addHeader() {
        H2 title = new H2("Warehouse Management");
        title.addClassNames("m-0", "text-xl", "font-bold");
        Paragraph desc = new Paragraph("Manage physical facilities, zones, storage racks, and location bin numbers.");
        desc.addClassNames("text-secondary", "m-0");
        add(title, desc);
    }

    private void configureTabs() {
        tabs.add(tabWarehouses, tabZones, tabRacks, tabLocations);
        tabs.addSelectedChangeListener(event -> {
            contentArea.removeAll();
            Tab selected = tabs.getSelectedTab();
            if (selected.equals(tabWarehouses)) {
                showWarehousesPanel();
            } else if (selected.equals(tabZones)) {
                showZonesPanel();
            } else if (selected.equals(tabRacks)) {
                showRacksPanel();
            } else if (selected.equals(tabLocations)) {
                showLocationsPanel();
            }
        });
    }

    // --- Warehouses Panel ---
    private void showWarehousesPanel() {
        Grid<WarehouseDto> grid = new Grid<>(WarehouseDto.class, false);
        TextField search = new TextField();
        search.setPlaceholder("Search warehouses...");
        search.setPrefixComponent(VaadinIcon.SEARCH.create());
        search.setClearButtonVisible(true);

        Runnable refresh = () -> {
            WarehouseDto param = WarehouseDto.builder().name(search.getValue()).build();
            var page = warehouseService.getWarehouses(param, PageRequest.of(0, 100));
            grid.setItems(page.getContent());
        };
        search.addValueChangeListener(e -> refresh.run());

        Button addBtn = new Button("Add Warehouse", VaadinIcon.PLUS.create(), e -> openWarehouseDialog(WarehouseDto.builder().build(), refresh));
        addBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        HorizontalLayout toolbar = new HorizontalLayout(search, addBtn);
        toolbar.setWidthFull();
        toolbar.setFlexGrow(1, search);

        grid.setSizeFull();
        grid.addColumn(WarehouseDto::getId).setHeader("ID").setAutoWidth(true);
        grid.addColumn(WarehouseDto::getName).setHeader("Warehouse Name").setFlexGrow(1);
        grid.addColumn(WarehouseDto::getLocation).setHeader("Location/Address").setFlexGrow(1);
        grid.addColumn(w -> w.getArea() != null ? w.getArea() + " m²" : "-").setHeader("Area").setAutoWidth(true);

        grid.addComponentColumn(wh -> {
            boolean active = Boolean.TRUE.equals(wh.getIsActive());
            Span badge = new Span(active ? "Active" : "Inactive");
            badge.getElement().getThemeList().add("badge " + (active ? "success" : "error"));
            return badge;
        }).setHeader("Status").setAutoWidth(true);

        grid.addComponentColumn(wh -> {
            Button edit = new Button(VaadinIcon.EDIT.create(), e -> openWarehouseDialog(wh, refresh));
            edit.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
            Button delete = new Button(VaadinIcon.TRASH.create(), e -> {
                try {
                    warehouseService.delete(List.of(wh));
                    Notification.show("Warehouse deleted", 3000, Notification.Position.TOP_END).addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                    refresh.run();
                } catch (Exception ex) {
                    Notification.show("Delete failed: " + ex.getMessage(), 4000, Notification.Position.TOP_END).addThemeVariants(NotificationVariant.LUMO_ERROR);
                }
            });
            delete.addThemeVariants(ButtonVariant.LUMO_TERTIARY, ButtonVariant.LUMO_ERROR);
            return new HorizontalLayout(edit, delete);
        }).setHeader("Actions").setAutoWidth(true);

        contentArea.add(toolbar, grid);
        refresh.run();
    }

    private void openWarehouseDialog(WarehouseDto warehouse, Runnable onSave) {
        boolean isEdit = warehouse.getId() != null;
        Dialog dialog = new Dialog(isEdit ? "Edit Warehouse" : "Add Warehouse");

        TextField nameField = new TextField("Name");
        TextField locationField = new TextField("Location");
        NumberField areaField = new NumberField("Area (m²)");
        Checkbox activeCheck = new Checkbox("Is Active");

        nameField.setValue(warehouse.getName() != null ? warehouse.getName() : "");
        locationField.setValue(warehouse.getLocation() != null ? warehouse.getLocation() : "");
        areaField.setValue(warehouse.getArea() != null ? warehouse.getArea() : 0.0);
        activeCheck.setValue(warehouse.getIsActive() != null ? warehouse.getIsActive() : true);

        FormLayout form = new FormLayout(nameField, locationField, areaField, activeCheck);
        dialog.add(form);

        Button save = new Button("Save", e -> {
            WarehouseDto dto = warehouse.toBuilder()
                    .name(nameField.getValue())
                    .location(locationField.getValue())
                    .area(areaField.getValue())
                    .isActive(activeCheck.getValue())
                    .build();

            try {
                if (isEdit) {
                    warehouseService.update(List.of(dto));
                } else {
                    warehouseService.save(List.of(dto));
                }
                Notification.show("Warehouse saved", 3000, Notification.Position.TOP_END).addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                dialog.close();
                onSave.run();
            } catch (Exception ex) {
                Notification.show("Error: " + ex.getMessage(), 4000, Notification.Position.TOP_END).addThemeVariants(NotificationVariant.LUMO_ERROR);
            }
        });
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        dialog.getFooter().add(new Button("Cancel", e -> dialog.close()), save);
        dialog.open();
    }

    // --- Zones Panel ---
    private void showZonesPanel() {
        Grid<ZoneDto> grid = new Grid<>(ZoneDto.class, false);
        TextField search = new TextField();
        search.setPlaceholder("Search zones...");
        search.setPrefixComponent(VaadinIcon.SEARCH.create());

        Runnable refresh = () -> {
            ZoneDto param = ZoneDto.builder().name(search.getValue()).build();
            var page = zoneService.getZones(param, PageRequest.of(0, 100));
            grid.setItems(page.getContent());
        };
        search.addValueChangeListener(e -> refresh.run());

        Button addBtn = new Button("Add Zone", VaadinIcon.PLUS.create(), e -> openZoneDialog(ZoneDto.builder().build(), refresh));
        addBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        HorizontalLayout toolbar = new HorizontalLayout(search, addBtn);
        toolbar.setWidthFull();
        toolbar.setFlexGrow(1, search);

        grid.setSizeFull();
        grid.addColumn(ZoneDto::getCode).setHeader("Zone Code").setAutoWidth(true);
        grid.addColumn(ZoneDto::getName).setHeader("Zone Name").setFlexGrow(1);
        grid.addColumn(ZoneDto::getType).setHeader("Type").setAutoWidth(true);

        grid.addComponentColumn(z -> {
            Button delete = new Button(VaadinIcon.TRASH.create(), e -> {
                zoneService.delete(List.of(z));
                refresh.run();
            });
            delete.addThemeVariants(ButtonVariant.LUMO_TERTIARY, ButtonVariant.LUMO_ERROR);
            return delete;
        }).setHeader("Actions").setAutoWidth(true);

        contentArea.add(toolbar, grid);
        refresh.run();
    }

    private void openZoneDialog(ZoneDto zone, Runnable onSave) {
        Dialog dialog = new Dialog("Add Zone");
        TextField code = new TextField("Zone Code");
        TextField name = new TextField("Zone Name");
        TextField type = new TextField("Zone Type");
        dialog.add(new FormLayout(code, name, type));

        Button save = new Button("Save", e -> {
            ZoneDto dto = zone.toBuilder().code(code.getValue()).name(name.getValue()).type(type.getValue()).isActive(true).build();
            zoneService.add(List.of(dto));
            dialog.close();
            onSave.run();
        });
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        dialog.getFooter().add(new Button("Cancel", e -> dialog.close()), save);
        dialog.open();
    }

    // --- Racks Panel ---
    private void showRacksPanel() {
        Grid<RackDto> grid = new Grid<>(RackDto.class, false);
        TextField search = new TextField();
        search.setPlaceholder("Search racks...");
        search.setPrefixComponent(VaadinIcon.SEARCH.create());

        Runnable refresh = () -> {
            RackDto param = RackDto.builder().name(search.getValue()).build();
            var page = rackService.getRacks(param, PageRequest.of(0, 100));
            grid.setItems(page.getContent());
        };
        search.addValueChangeListener(e -> refresh.run());

        Button addBtn = new Button("Add Rack", VaadinIcon.PLUS.create(), e -> openRackDialog(RackDto.builder().build(), refresh));
        addBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        HorizontalLayout toolbar = new HorizontalLayout(search, addBtn);
        toolbar.setWidthFull();
        toolbar.setFlexGrow(1, search);

        grid.setSizeFull();
        grid.addColumn(RackDto::getName).setHeader("Rack Name").setFlexGrow(1);
        grid.addColumn(RackDto::getRows).setHeader("Rows").setAutoWidth(true);
        grid.addColumn(RackDto::getCols).setHeader("Columns").setAutoWidth(true);

        contentArea.add(toolbar, grid);
        refresh.run();
    }

    private void openRackDialog(RackDto rack, Runnable onSave) {
        Dialog dialog = new Dialog("Add Rack");
        TextField name = new TextField("Rack Name");
        NumberField rows = new NumberField("Rows");
        NumberField cols = new NumberField("Columns");
        dialog.add(new FormLayout(name, rows, cols));

        Button save = new Button("Save", e -> {
            RackDto dto = rack.toBuilder().name(name.getValue())
                    .rows(rows.getValue() != null ? rows.getValue().longValue() : 1L)
                    .cols(cols.getValue() != null ? cols.getValue().longValue() : 1L)
                    .isActive(true).build();
            rackService.add(List.of(dto));
            dialog.close();
            onSave.run();
        });
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        dialog.getFooter().add(new Button("Cancel", e -> dialog.close()), save);
        dialog.open();
    }

    // --- Locations Panel ---
    private void showLocationsPanel() {
        Grid<LocationDto> grid = new Grid<>(LocationDto.class, false);
        TextField search = new TextField();
        search.setPlaceholder("Search locations / bin numbers...");
        search.setPrefixComponent(VaadinIcon.SEARCH.create());

        Runnable refresh = () -> {
            LocationDto param = LocationDto.builder().name(search.getValue()).build();
            var page = locationService.getLocations(param, PageRequest.of(0, 100));
            grid.setItems(page.getContent());
        };
        search.addValueChangeListener(e -> refresh.run());

        Button addBtn = new Button("Add Location", VaadinIcon.PLUS.create(), e -> openLocationDialog(LocationDto.builder().build(), refresh));
        addBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        HorizontalLayout toolbar = new HorizontalLayout(search, addBtn);
        toolbar.setWidthFull();
        toolbar.setFlexGrow(1, search);

        grid.setSizeFull();
        grid.addColumn(LocationDto::getName).setHeader("Location Name").setFlexGrow(1);
        grid.addColumn(LocationDto::getBinNumber).setHeader("Bin Number").setAutoWidth(true);
        grid.addColumn(LocationDto::getType).setHeader("Type").setAutoWidth(true);

        contentArea.add(toolbar, grid);
        refresh.run();
    }

    private void openLocationDialog(LocationDto location, Runnable onSave) {
        Dialog dialog = new Dialog("Add Location");
        TextField name = new TextField("Location Name");
        TextField bin = new TextField("Bin Number");
        TextField type = new TextField("Location Type");
        dialog.add(new FormLayout(name, bin, type));

        Button save = new Button("Save", e -> {
            LocationDto dto = LocationDto.builder().name(name.getValue()).binNumber(bin.getValue()).type(type.getValue()).isActive(true).build();
            locationService.add(List.of(dto));
            dialog.close();
            onSave.run();
        });
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        dialog.getFooter().add(new Button("Cancel", e -> dialog.close()), save);
        dialog.open();
    }
}
