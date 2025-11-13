package id.putra.wms.module.supplier.mapper;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import static org.assertj.core.api.Assertions.assertThat;

public class ContactPersonSupplierMapperTest {

    private ContactPersonSupplierMapper contactPersonSupplierMapper = Mappers.getMapper(ContactPersonSupplierMapper.class);

    @Test
    void mapperLoads() {
        assertThat(contactPersonSupplierMapper).isNotNull();
    }

}
