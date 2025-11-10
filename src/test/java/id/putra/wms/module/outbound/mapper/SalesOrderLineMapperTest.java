package id.putra.wms.module.outbound.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import id.putra.wms.module.outbound.dto.SalesOrderLineDto;
import id.putra.wms.module.outbound.model.entity.SalesOrderLine;

@ExtendWith(SpringExtension.class)
@Import({SalesOrderLineMapperImpl.class, id.putra.wms.module.inventory.mapper.InventoryItemMapperImpl.class, id.putra.wms.shared.mapper.ProductMapperImpl.class})
public class SalesOrderLineMapperTest {

    @Autowired
    private SalesOrderLineMapper mapper;

    @Test
    void whenMapSalesOrderLineEntityToDto_thenFieldsCopied() {
        SalesOrderLine entity = new SalesOrderLine();
        entity.setId(5L);

        SalesOrderLineDto dto = mapper.toDto(entity);

        assertThat(dto).isNotNull();
        assertThat(dto.getId()).isEqualTo(5L);
    }
}
