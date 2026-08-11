package id.putra.wms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.theme.lumo.Lumo;;

@StyleSheet(Lumo.STYLESHEET)
@SpringBootApplication
public class WmsApplication implements AppShellConfigurator {

    public static void main(String[] args) {
        SpringApplication.run(WmsApplication.class, args);
    }
}
