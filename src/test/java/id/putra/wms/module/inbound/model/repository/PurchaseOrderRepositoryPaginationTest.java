package id.putra.wms.module.inbound.model.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import id.putra.wms.PostgreSQLContainerInitializer;
import id.putra.wms.module.inbound.dto.PurchaseOrderDto;
import id.putra.wms.module.inbound.model.entity.PurchaseOrder;
import jakarta.persistence.criteria.Predicate;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@Transactional
public class PurchaseOrderRepositoryPaginationTest extends PostgreSQLContainerInitializer {

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @BeforeEach
    void setup() {
        var po = new PurchaseOrder();
        po.setPoNumber("PO-PAG-1");
        po.setOrderDate(new Date(System.currentTimeMillis()));
        po.setStatus("OPEN");
        purchaseOrderRepository.saveAndFlush(java.util.Objects.requireNonNull(po));
    }

    @Test
    void givenPurchaseOrder_whenSearchByDto_shouldReturnPaginationResult() {
        Pageable pageable = PageRequest.of(0, 10);

        PurchaseOrderDto dto = new PurchaseOrderDto();
        dto.setPoNumber("PO-PAG-1");

        Specification<PurchaseOrder> byPoNumber = (root, criteria, builder) -> {
            Predicate p = builder.like(root.get("poNumber"), "%" + (dto.getPoNumber() != null ? dto.getPoNumber() : "") + "%");
            return builder.and(p);
        };

        Page<PurchaseOrder> page = purchaseOrderRepository.findAll(byPoNumber, pageable);

        assertThat(page).isNotNull();
        assertThat(page.getContent().size()).isGreaterThan(0);
    }
}
