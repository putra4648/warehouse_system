package id.putra.wms.module.customer.mapper;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import static org.assertj.core.api.Assertions.assertThat;

public class ContactPersonCustomerMapperTest {

    private ContactPersonCustomerMapper contactPersonCustomerMapper = Mappers.getMapper(ContactPersonCustomerMapper.class);

    @Test
    void mapperLoads() {
        assertThat(contactPersonCustomerMapper).isNotNull();
    }

}
