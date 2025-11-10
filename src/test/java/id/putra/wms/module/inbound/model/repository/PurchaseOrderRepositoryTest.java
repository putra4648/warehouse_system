package id.putra.wms.module.inbound.model.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Date;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import id.putra.wms.PostgreSQLContainerInitializer;
import id.putra.wms.module.inbound.model.entity.PurchaseOrder;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@Transactional
public class PurchaseOrderRepositoryTest extends PostgreSQLContainerInitializer {

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    private PurchaseOrder po;

    @BeforeEach
    void setup() {
        po = new PurchaseOrder();
        po.setPoNumber("PO-100");
        po.setOrderDate(new Date(System.currentTimeMillis()));
        po.setStatus("OPEN");
        purchaseOrderRepository.saveAndFlush(java.util.Objects.requireNonNull(po));
    }

    @AfterEach
    void tearDown() {
        if (po != null) {
            purchaseOrderRepository.delete(po);
        }
    }

    @Test
    void whenSavePurchaseOrder_thenFindByIdReturnsIt() {
        Optional<PurchaseOrder> found = purchaseOrderRepository.findById(po.getId());

        assertThat(found).isNotEmpty();
        assertThat(found.get().getPoNumber()).isEqualTo("PO-100");
    }

}
