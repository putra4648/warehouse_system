package id.putra.wms.config;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DatabaseVerifier {

    @Autowired
    private DataSource dataSource;

    @PostConstruct
    public void verifyConnection() {
        try (Connection conn = dataSource.getConnection()) {
            String currentDb = conn.getCatalog();
            String url = conn.getMetaData().getURL();

            log.info("=== DATABASE CONNECTION VERIFICATION ===");
            log.info("Expected database: wms_db");
            log.info("Actual database: {}", currentDb);
            log.info("Connection URL: {}", url);

            if (!"wms_db".equals(currentDb)) {
                log.error("❌ WRONG DATABASE! Expected 'wms_db' but connected to '{}'", currentDb);
                log.error("This means 'wms_db' probably doesn't exist!");
            } else {
                log.info("✅ Connected to correct database: {}", currentDb);
            }

            // Check if inventory table exists
            DatabaseMetaData metaData = conn.getMetaData();
            try (ResultSet tables = metaData.getTables(null, "public", "inventory", new String[] { "TABLE" })) {
                if (tables.next()) {
                    log.info("✅ 'inventory' table exists");
                } else {
                    log.warn("⚠️ 'inventory' table does not exist in {}", currentDb);
                }
            }

        } catch (SQLException e) {
            log.error("❌ Database connection failed: {}", e.getMessage());
        }
    }
}
