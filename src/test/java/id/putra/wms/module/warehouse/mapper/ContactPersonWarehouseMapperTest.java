package id.putra.wms.module.warehouse.mapper;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import static org.assertj.core.api.Assertions.assertThat;

public class ContactPersonWarehouseMapperTest {

    private ContactPersonWarehouseMapper contactPersonWarehouseMapper = Mappers.getMapper(ContactPersonWarehouseMapper.class);

    @Test
    void mapperLoads() {
        assertThat(contactPersonWarehouseMapper).isNotNull();
    }

}
