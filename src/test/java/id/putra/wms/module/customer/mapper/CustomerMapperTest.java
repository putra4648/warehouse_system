package id.putra.wms.module.customer.mapper;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import static org.assertj.core.api.Assertions.assertThat;

public class CustomerMapperTest {

    private CustomerMapper customerMapper = Mappers.getMapper(CustomerMapper.class);

    @Test
    void mapperLoads() {
        assertThat(customerMapper).isNotNull();
    }

}
