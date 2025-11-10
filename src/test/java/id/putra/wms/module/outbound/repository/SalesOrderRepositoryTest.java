package id.putra.wms.module.outbound.repository;

import id.putra.wms.test.TestContainersConfig;
import id.putra.wms.module.outbound.model.repository.SalesOrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(TestContainersConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SalesOrderRepositoryTest {

    @Autowired
    private SalesOrderRepository salesOrderRepository;

    @Test
    void repositoryLoads() {
        assertThat(salesOrderRepository).isNotNull();
        assertThat(salesOrderRepository.count()).isGreaterThanOrEqualTo(0L);
    }

}
