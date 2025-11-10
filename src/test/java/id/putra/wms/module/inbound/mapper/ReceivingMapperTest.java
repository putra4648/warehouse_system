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
public class ReceivingMapperTest {

    @Autowired
    private ReceivingMapper mapper;

    @Autowired
    private ReceivingLineMapper lineMapper;

    @Test
    void whenMapReceivingDtoToEntity_thenFieldsAreCopied() {
        ReceivingLineDto lineDto = new ReceivingLineDto();
        lineDto.setId(1L);
        lineDto.setQtyReceived(new BigDecimal("12.50"));
        lineDto.setLotBatch("LB-1");
        lineDto.setExpiryDate(new Date(System.currentTimeMillis()));

        ReceivingDto dto = new ReceivingDto();
        dto.setId(10L);
        dto.setReceivingNumber("RCV-001");
        dto.setReceivedDate(new Date(System.currentTimeMillis()));
        dto.setStatus("RECEIVED");
        dto.setReceivingLines(List.of(lineDto));

        Receiving entity = mapper.toEntity(dto);

        assertThat(entity).isNotNull();
        assertThat(entity.getReceivingNumber()).isEqualTo(dto.getReceivingNumber());
        assertThat(entity.getStatus()).isEqualTo(dto.getStatus());
        assertThat(entity.getReceivingLines()).isNotNull();
        assertThat(entity.getReceivingLines()).hasSize(1);
    }

    @Test
    void whenMapReceivingLineEntityToDto_thenFieldsAreCopied() {
        ReceivingLine line = new ReceivingLine();
        line.setId(2L);
        line.setQtyReceived(new BigDecimal("5"));
        line.setLotBatch("LOT-1");

        ReceivingLineDto dto = lineMapper.toDto(line);

        assertThat(dto).isNotNull();
        assertThat(dto.getId()).isEqualTo(line.getId());
        assertThat(dto.getQtyReceived()).isEqualTo(line.getQtyReceived());
    }

}
