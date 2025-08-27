package id.putra.wms.config.db;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class DatabaseVerifier {

    private final DataSource dataSource;

    @PostConstruct
    public void verifyConnection() {
        try (Connection conn = dataSource.getConnection()) {
            String currentDb = conn.getCatalog();
            String url = conn.getMetaData().getURL();

            log.info("=== DATABASE CONNECTION VERIFICATION ===");
            log.info("Expected database: wms_db");
            log.info("Actual database: {}", currentDb);
            log.info("Connection URL: {}", url);

        } catch (SQLException e) {
            log.error("❌ Database connection failed: {}", e.getMessage());
        }
    }
}
