package id.putra.wms.module.customer.converter;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import id.putra.wms.module.customer.dto.CustomerDto;
import id.putra.wms.shared.base.entity.Customer;

public class CustomerConverterTest {

    @Test
    void whenToDto_thenReturnDto() {
        Customer entity = Customer.builder()
                .id(100L)
                .name("Test Customer")
                .build();

        CustomerDto dto = CustomerConverter.toDto(entity);

        assertThat(dto).isNotNull();
        assertThat(dto.getId()).isEqualTo(100L);
        assertThat(dto.getName()).isEqualTo("Test Customer");
    }

    @Test
    void whenToEntity_thenReturnEntity() {
        CustomerDto dto = CustomerDto.builder()
                .id(200L)
                .name("DTO Customer")
                .build();

        Customer entity = CustomerConverter.toEntity(dto);

        assertThat(entity).isNotNull();
        assertThat(entity.getId()).isEqualTo(200L);
        assertThat(entity.getName()).isEqualTo("DTO Customer");
    }

    @Test
    void whenNull_thenReturnNull() {
        assertThat(CustomerConverter.toDto(null)).isNull();
        assertThat(CustomerConverter.toEntity(null)).isNull();
    }
}
