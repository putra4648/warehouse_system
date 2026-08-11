package id.putra.wms.views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.contextmenu.SubMenu;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.menubar.MenuBarVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.dom.ThemeList;
import com.vaadin.flow.theme.lumo.Lumo;
import id.putra.wms.security.SecurityService;
import id.putra.wms.views.dashboard.DashboardView;
import id.putra.wms.views.help.HelpView;
import id.putra.wms.views.inbound.PurchaseOrderView;
import id.putra.wms.views.inbound.ReceivingView;
import id.putra.wms.views.inventory.InventoryView;
import id.putra.wms.views.master.CategoryView;
import id.putra.wms.views.master.CustomerView;
import id.putra.wms.views.master.ProductView;
import id.putra.wms.views.master.SupplierView;
import id.putra.wms.views.master.WarehouseView;
import id.putra.wms.views.outbound.OutboundView;
import id.putra.wms.views.reports.ReportsView;
import id.putra.wms.views.settings.SettingsView;

public class MainLayout extends AppLayout {

    private final SecurityService securityService;

    public MainLayout(SecurityService securityService) {
        this.securityService = securityService;
        createHeader();
        createDrawer();
    }

    private void createHeader() {
        DrawerToggle toggle = new DrawerToggle();
        toggle.setAriaLabel("Menu toggle");

        H1 title = new H1("WMS Pro");
        title.addClassNames("text-l", "m-0", "font-bold");

        Button themeToggle = new Button(VaadinIcon.MOON.create(), click -> {
            ThemeList themeList = UI.getCurrent().getElement().getThemeList();
            if (themeList.contains(Lumo.DARK)) {
                themeList.remove(Lumo.DARK);
            } else {
                themeList.add(Lumo.DARK);
            }
        });
        themeToggle.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        themeToggle.setTooltipText("Toggle Dark/Light Theme");

        HorizontalLayout userLayout = new HorizontalLayout();
        userLayout.setAlignItems(FlexComponent.Alignment.CENTER);

        MenuBar userMenu = new MenuBar();
        userMenu.addThemeVariants(MenuBarVariant.LUMO_TERTIARY_INLINE);

        securityService.getAuthenticatedUser().ifPresentOrElse(user -> {
            Avatar avatar = new Avatar(user.name(), user.pictureUrl());
            MenuItem item = userMenu.addItem(avatar);
            SubMenu subMenu = item.getSubMenu();
            
            Span nameSpan = new Span(user.name());
            nameSpan.addClassNames("font-semibold", "px-m");
            subMenu.addItem(nameSpan).setEnabled(false);
            
            if (!user.email().isBlank()) {
                Span emailSpan = new Span(user.email());
                emailSpan.addClassNames("text-xs", "text-secondary", "px-m");
                subMenu.addItem(emailSpan).setEnabled(false);
            }
            
            subMenu.add(new Hr());
            subMenu.addItem("Sign Out", e -> securityService.logout());
        }, () -> {
            Avatar avatar = new Avatar("User");
            MenuItem item = userMenu.addItem(avatar);
            SubMenu subMenu = item.getSubMenu();
            subMenu.addItem("Sign Out", e -> securityService.logout());
        });

        userLayout.add(themeToggle, userMenu);

        Header header = new Header(toggle, title, userLayout);
        header.addClassNames("flex", "items-center", "justify-between", "w-full", "px-m");

        addToNavbar(header);
    }

    private void createDrawer() {
        SideNav nav = new SideNav();

        SideNavItem dashboard = new SideNavItem("Dashboard", DashboardView.class, VaadinIcon.DASHBOARD.create());
        SideNavItem inventory = new SideNavItem("Inventory", InventoryView.class, VaadinIcon.STORAGE.create());

        SideNavItem inboundGroup = new SideNavItem("Inbound");
        inboundGroup.setPrefixComponent(VaadinIcon.DOWNLOAD.create());
        inboundGroup.addItem(new SideNavItem("Purchase Order", PurchaseOrderView.class, VaadinIcon.CART.create()));
        inboundGroup.addItem(new SideNavItem("Receiving", ReceivingView.class, VaadinIcon.TRUCK.create()));

        SideNavItem outbound = new SideNavItem("Outbound", OutboundView.class, VaadinIcon.UPLOAD.create());

        SideNavItem masterGroup = new SideNavItem("Master Data");
        masterGroup.setPrefixComponent(VaadinIcon.DATABASE.create());
        masterGroup.addItem(new SideNavItem("Customer", CustomerView.class, VaadinIcon.USER.create()));
        masterGroup.addItem(new SideNavItem("Product", ProductView.class, VaadinIcon.PACKAGE.create()));
        masterGroup.addItem(new SideNavItem("Category", CategoryView.class, VaadinIcon.TAGS.create()));
        masterGroup.addItem(new SideNavItem("Supplier", SupplierView.class, VaadinIcon.GROUP.create()));
        masterGroup.addItem(new SideNavItem("Warehouse", WarehouseView.class, VaadinIcon.BUILDING.create()));

        SideNavItem reports = new SideNavItem("Reports", ReportsView.class, VaadinIcon.PIE_CHART.create());
        SideNavItem settings = new SideNavItem("Settings", SettingsView.class, VaadinIcon.COG.create());
        SideNavItem help = new SideNavItem("Help", HelpView.class, VaadinIcon.QUESTION_CIRCLE.create());

        nav.addItem(dashboard, inventory, inboundGroup, outbound, masterGroup, reports, settings, help);

        VerticalLayout drawerLayout = new VerticalLayout(nav);
        drawerLayout.setPadding(true);
        drawerLayout.setSpacing(true);
        drawerLayout.setSizeFull();

        addToDrawer(drawerLayout);
    }
}
