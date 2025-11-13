package id.putra.wms.module.warehouse.mapper;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import static org.assertj.core.api.Assertions.assertThat;

public class LocationMapperTest {

    private LocationMapper locationMapper = Mappers.getMapper(LocationMapper.class);

    @Test
    void mapperLoads() {
        assertThat(locationMapper).isNotNull();
    }

}
