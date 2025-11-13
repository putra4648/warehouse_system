package id.putra.wms.module.inbound.mapper;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import static org.assertj.core.api.Assertions.assertThat;

public class ReceivingLineMapperTest {

    private ReceivingLineMapper receivingLineMapper = Mappers.getMapper(ReceivingLineMapper.class);

    @Test
    void mapperLoads() {
        assertThat(receivingLineMapper).isNotNull();
    }

}
