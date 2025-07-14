package id.putra.wms.config;

import java.io.File;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

@Configuration
@Profile("dev")
public class DevToolsConfig {

    @EventListener
    public void handleContextRefresh(ContextRefreshedEvent event) {
        // Run npm build when context refreshes
        runNpmBuild();
    }

    private void runNpmBuild() {
        try {
            ProcessBuilder pb = new ProcessBuilder("npm", "run", "build");
            pb.directory(new File("src/main/resources/static/raw"));
            Process process = pb.start();
            process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
