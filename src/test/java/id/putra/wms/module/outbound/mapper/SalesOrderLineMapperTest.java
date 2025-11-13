package id.putra.wms.module.outbound.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import id.putra.wms.module.outbound.dto.SalesOrderLineDto;
import id.putra.wms.module.outbound.model.entity.SalesOrderLine;

public class SalesOrderLineMapperTest {

    private SalesOrderLineMapper mapper = Mappers.getMapper(SalesOrderLineMapper.class);

    @Test
    void whenMapSalesOrderLineEntityToDto_thenFieldsCopied() {
        SalesOrderLine entity = new SalesOrderLine();
        entity.setId(5L);

        SalesOrderLineDto dto = mapper.toDto(entity);

        assertThat(dto).isNotNull();
        assertThat(dto.getId()).isEqualTo(5L);
    }
}
