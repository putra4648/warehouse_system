package id.putra.wms.module.outbound.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

public class SalesOrderMapperTest {

    @Test
    void mapperLoads() {
        // For mappers with nested dependencies, we just verify the mapper interface exists
        // The actual mapping will be tested in integration tests with Spring context
        SalesOrderMapper mapper = Mappers.getMapper(SalesOrderMapper.class);
        assertThat(mapper).isNotNull();
    }

}
