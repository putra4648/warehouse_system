package id.putra.wms.module.inbound.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import id.putra.wms.module.inventory.mapper.InventoryItemMapperImpl;
import id.putra.wms.shared.mapper.ProductMapperImpl;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import id.putra.wms.module.inbound.dto.ReceivingDto;
import id.putra.wms.module.inbound.dto.ReceivingLineDto;
import id.putra.wms.module.inbound.model.entity.Receiving;
import id.putra.wms.module.inbound.model.entity.ReceivingLine;

@ExtendWith(SpringExtension.class)
@Import({ReceivingMapperImpl.class, ReceivingLineMapperImpl.class, InventoryItemMapperImpl.class, ProductMapperImpl.class})
public class ReceivingMapperNestedTest {

    @Autowired
    private ReceivingMapper mapper;

    

    @Test
    void whenMapReceivingDtoWithLines_thenEntityHasLines() {
        ReceivingLineDto lineDto = new ReceivingLineDto();
        lineDto.setId(11L);
        lineDto.setQtyReceived(new BigDecimal("7"));
        lineDto.setLotBatch("LB-NEST");
        lineDto.setExpiryDate(new Date(System.currentTimeMillis()));

        ReceivingDto dto = new ReceivingDto();
        dto.setId(100L);
        dto.setReceivingNumber("RCV-NEST-1");
        dto.setReceivedDate(new Date(System.currentTimeMillis()));
        dto.setStatus("RECEIVED");
        dto.setReceivingLines(List.of(lineDto));

        Receiving entity = mapper.toEntity(dto);

        // MapStruct will map nested list using ReceivingLineMapper
        assertThat(entity).isNotNull();
        assertThat(entity.getReceivingNumber()).isEqualTo(dto.getReceivingNumber());

        // Also test reverse mapping
        ReceivingLine lineEntity = new ReceivingLine();
        lineEntity.setId(22L);
        lineEntity.setQtyReceived(new BigDecimal("3"));
        lineEntity.setLotBatch("LB-RE");

        Receiving r = new Receiving();
        r.setId(200L);
        r.setReceivingNumber("RCV-REV");
        r.setReceivingLines(List.of(lineEntity));

        ReceivingDto out = mapper.toDto(r);
        assertThat(out).isNotNull();
        assertThat(out.getReceivingLines()).isNotNull();
        assertThat(out.getReceivingLines().size()).isEqualTo(1);
    }
}
