package id.putra.wms.module.outbound.model.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import id.putra.wms.PostgreSQLContainerInitializer;
import id.putra.wms.module.outbound.model.entity.SalesOrder;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@Transactional
public class SalesOrderRepositoryTest extends PostgreSQLContainerInitializer {

    @Autowired
    private SalesOrderRepository salesOrderRepository;

    private SalesOrder salesOrder;

    @BeforeEach
    void setup() {
        salesOrder = new SalesOrder();
        salesOrder.setSoNumber("SO-100");
        salesOrder.setStatus("NEW");
        salesOrderRepository.saveAndFlush(java.util.Objects.requireNonNull(salesOrder));
    }

    @AfterEach
    void tearDown() {
        if (salesOrder != null) {
            salesOrderRepository.delete(salesOrder);
        }
    }

    @Test
    void whenSaveSalesOrder_thenFindAllReturnsIt() {
        List<SalesOrder> all = salesOrderRepository.findAll();

        assertThat(all).isNotNull();
        assertThat(all.size()).isGreaterThan(0);
    }

}
