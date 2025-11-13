package id.putra.wms.module.warehouse.mapper;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import static org.assertj.core.api.Assertions.assertThat;

public class ZoneMapperTest {

    private ZoneMapper zoneMapper = Mappers.getMapper(ZoneMapper.class);

    @Test
    void mapperLoads() {
        assertThat(zoneMapper).isNotNull();
    }

}
