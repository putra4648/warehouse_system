package id.putra.wms.module.warehouse.mapper;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import static org.assertj.core.api.Assertions.assertThat;

public class WarehouseMapperTest {

    private WarehouseMapper warehouseMapper = Mappers.getMapper(WarehouseMapper.class);

    @Test
    void mapperLoads() {
        assertThat(warehouseMapper).isNotNull();
    }

}
