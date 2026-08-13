package id.putra.wms.views.settings;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.dom.ThemeList;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.Lumo;
import id.putra.wms.views.MainLayout;
import jakarta.annotation.security.PermitAll;

@Route(value = "settings", layout = MainLayout.class)
@PageTitle("Settings | WMS Pro")
@PermitAll
public class SettingsView extends VerticalLayout {

    public SettingsView() {
        setSizeFull();
        setPadding(true);
        setSpacing(true);

        addHeader();
        addSettingsForm();
    }

    private void addHeader() {
        H2 title = new H2("System Settings");
        title.addClassNames("m-0", "text-xl", "font-bold");
        Paragraph desc = new Paragraph("Configure application parameters, dark mode, and system defaults.");
        desc.addClassNames("text-secondary", "m-0");
        add(title, desc);
    }

    private void addSettingsForm() {
        TextField sysName = new TextField("System Identifier", "WMS Pro Production", "");
        TextField timeZone = new TextField("Default Timezone", "UTC", "");

        ThemeList themeList = UI.getCurrent().getElement().getThemeList();
        Checkbox darkThemeCheck = new Checkbox("Enable Dark Mode Theme", themeList.contains(Lumo.DARK));
        darkThemeCheck.addValueChangeListener(e -> {
            if (Boolean.TRUE.equals(e.getValue())) {
                themeList.add(Lumo.DARK);
            } else {
                themeList.remove(Lumo.DARK);
            }
        });

        FormLayout form = new FormLayout(sysName, timeZone, darkThemeCheck);
        form.setMaxWidth("600px");

        Button saveBtn = new Button("Save Settings", e -> {
            Notification.show("Settings saved successfully", 3000, Notification.Position.TOP_END)
                    .addThemeVariants(NotificationVariant.LUMO_SUCCESS);
        });
        saveBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        VerticalLayout container = new VerticalLayout(form, saveBtn);
        container.addClassNames("border", "rounded-l", "p-l", "bg-base");
        container.setMaxWidth("650px");

        add(container);
    }
}
