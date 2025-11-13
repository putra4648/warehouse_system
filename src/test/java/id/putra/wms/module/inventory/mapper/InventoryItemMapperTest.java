package id.putra.wms.module.inventory.mapper;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import static org.assertj.core.api.Assertions.assertThat;

public class InventoryItemMapperTest {

    private InventoryItemMapper inventoryItemMapper = Mappers.getMapper(InventoryItemMapper.class);

    @Test
    void mapperLoads() {
        assertThat(inventoryItemMapper).isNotNull();
    }

}
