package id.putra.wms.module.warehouse.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

public class RackMapperTest {

    @Test
    void mapperLoads() {
        // For mappers with nested dependencies, we just verify the mapper interface exists
        // The actual mapping will be tested in integration tests with Spring context
        RackMapper mapper = Mappers.getMapper(RackMapper.class);
        assertThat(mapper).isNotNull();
    }

}
