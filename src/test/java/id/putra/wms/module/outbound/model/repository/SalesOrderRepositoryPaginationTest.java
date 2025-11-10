package id.putra.wms.module.outbound.model.repository;

import static org.assertj.core.api.Assertions.assertThat;

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
import id.putra.wms.module.outbound.dto.SalesOrderDto;
import id.putra.wms.module.outbound.model.entity.SalesOrder;
import jakarta.persistence.criteria.Predicate;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@Transactional
public class SalesOrderRepositoryPaginationTest extends PostgreSQLContainerInitializer {

    @Autowired
    private SalesOrderRepository salesOrderRepository;

    @BeforeEach
    void setup() {
        var so = new SalesOrder();
        so.setSoNumber("SO-PAG-1");
        so.setStatus("NEW");
        salesOrderRepository.saveAndFlush(java.util.Objects.requireNonNull(so));
    }

    @Test
    void givenSalesOrder_whenSearchByDto_shouldReturnPaginationResult() {
        Pageable pageable = PageRequest.of(0, 10);

        SalesOrderDto dto = new SalesOrderDto();
        dto.setSoNumber("SO-PAG-1");

        Specification<SalesOrder> bySoNumber = (root, criteria, builder) -> {
            Predicate p = builder.like(root.get("soNumber"), "%" + (dto.getSoNumber() != null ? dto.getSoNumber() : "") + "%");
            return builder.and(p);
        };

        Page<SalesOrder> page = salesOrderRepository.findAll(bySoNumber, pageable);

        assertThat(page).isNotNull();
        assertThat(page.getContent().size()).isGreaterThan(0);
    }
}
