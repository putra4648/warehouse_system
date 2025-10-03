package id.putra.wms.module.warehouse.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import id.putra.wms.module.warehouse.dto.ContactPersonWarehouseDto;
import id.putra.wms.module.warehouse.service.adapter.command.ContactPersonWarehouseCommandAdapter;
import id.putra.wms.module.warehouse.service.adapter.query.ContactPersonWarehouseQueryAdapter;

@ExtendWith(MockitoExtension.class)
public class ContactWarehouseServiceImplTest {

    @Mock
    private ContactPersonWarehouseQueryAdapter contactPersonWarehouseQueryAdapter;

    @Mock
    private ContactPersonWarehouseCommandAdapter contactPersonCommandAdapter;

    @InjectMocks
    private ContactWarehouseServiceImpl contactWarehouseService;

    private ContactPersonWarehouseDto contactPersonDto;
    private List<ContactPersonWarehouseDto> contactPersonDtos;
    private Pageable pageable;

    @BeforeEach
    public void setup() {
        contactPersonDto = ContactPersonWarehouseDto.builder()
                .id("cp-1")
                .name("Contact Person 1")
                .email("cp1@example.com")
                .build();

        contactPersonDtos = List.of(contactPersonDto);
        pageable = PageRequest.of(0, 10);
    }

    @Test
    void testGetContacts() {
        Page<ContactPersonWarehouseDto> expectedPage = new PageImpl<>(contactPersonDtos, pageable, 1);
        when(contactPersonWarehouseQueryAdapter.getContacts(any(ContactPersonWarehouseDto.class), eq(pageable)))
                .thenReturn(expectedPage);

        Page<ContactPersonWarehouseDto> result = contactWarehouseService.getContacts(contactPersonDto, pageable);

        assertThat(result).isNotNull();
        assertThat(result.getContent()).hasSize(1);
        assertThat(result.getContent().get(0).getId()).isEqualTo("cp-1");
        verify(contactPersonWarehouseQueryAdapter, times(1)).getContacts(any(ContactPersonWarehouseDto.class), eq(pageable));
    }

    @Test
    void testGetContact() {
        when(contactPersonWarehouseQueryAdapter.getContact(contactPersonDto)).thenReturn(contactPersonDto);

        ContactPersonWarehouseDto result = contactWarehouseService.getContact(contactPersonDto);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo("cp-1");
        verify(contactPersonWarehouseQueryAdapter, times(1)).getContact(contactPersonDto);
    }

    @Test
    void testAdd() {
        contactWarehouseService.add(contactPersonDtos);

        verify(contactPersonCommandAdapter, times(1)).add(contactPersonDtos);
    }

    @Test
    void testUpdate() {
        contactWarehouseService.update(contactPersonDtos);

        verify(contactPersonCommandAdapter, times(1)).update(contactPersonDtos);
    }

    @Test
    void testDelete() {
        contactWarehouseService.delete(contactPersonDtos);

        verify(contactPersonCommandAdapter, times(1)).delete(contactPersonDtos);
    }
}
