package id.putra.wms.test;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.postgresql.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

public class TestContainersConfig {

    public static final PostgreSQLContainer POSTGRES_CONTAINER;

    static {
        @SuppressWarnings("resource")
        PostgreSQLContainer container = new PostgreSQLContainer(DockerImageName.parse("postgres:17"))
                .withDatabaseName("wms-test-db")
                .withUsername("test")
                .withPassword("test");
        container.start();
        POSTGRES_CONTAINER = container;
    }

    @DynamicPropertySource
    static void postgresProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", POSTGRES_CONTAINER::getJdbcUrl);
        registry.add("spring.datasource.username", POSTGRES_CONTAINER::getUsername);
        registry.add("spring.datasource.password", POSTGRES_CONTAINER::getPassword);
        registry.add("spring.datasource.driver-class-name", () -> "org.postgresql.Driver");
    }

}
