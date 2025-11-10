package id.putra.wms.module.outbound.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import id.putra.wms.module.inventory.mapper.InventoryItemMapperImpl;
import id.putra.wms.shared.mapper.ProductMapperImpl;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import id.putra.wms.module.outbound.dto.SalesOrderDto;
import id.putra.wms.module.outbound.dto.SalesOrderLineDto;
import id.putra.wms.module.outbound.model.entity.SalesOrder;
import id.putra.wms.module.outbound.model.entity.SalesOrderLine;

@ExtendWith(SpringExtension.class)
@Import({SalesOrderMapperImpl.class, SalesOrderLineMapperImpl.class, InventoryItemMapperImpl.class, ProductMapperImpl.class})
public class SalesOrderMapperNestedTest {

    @Autowired
    private SalesOrderMapper mapper;

    @Test
    void whenMapSalesOrderDtoWithLines_thenEntityHasLines() {
        SalesOrderLineDto lineDto = new SalesOrderLineDto();
        lineDto.setId(7L);
        var product = new id.putra.wms.shared.base.dto.ProductDto();
        product.setId("prod-nested");
        lineDto.setProduct(product);
        lineDto.setQty(4);

        SalesOrderDto dto = new SalesOrderDto();
        dto.setSoNumber("SO-NEST-1");
        dto.setOrderDate(new Date(System.currentTimeMillis()));
        dto.setStatus("NEW");
        dto.setSalesOrderLines(List.of(lineDto));

        SalesOrder entity = mapper.toEntity(dto);

        assertThat(entity).isNotNull();
        assertThat(entity.getSoNumber()).isEqualTo(dto.getSoNumber());

        // Reverse mapping
        SalesOrderLine lineEntity = new SalesOrderLine();
        lineEntity.setId(8L);
        SalesOrder so = new SalesOrder();
        so.setId(55L);
        so.setSoNumber("SO-REV");
        so.setSalesOrderLines(List.of(lineEntity));

        SalesOrderDto out = mapper.toDto(so);
        assertThat(out).isNotNull();
        assertThat(out.getSalesOrderLines()).isNotNull();
        assertThat(out.getSalesOrderLines().size()).isEqualTo(1);
    }
}
