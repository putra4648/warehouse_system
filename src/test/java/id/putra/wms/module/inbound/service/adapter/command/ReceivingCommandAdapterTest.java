package id.putra.wms.module.inbound.service.adapter.command;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;

import java.sql.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import id.putra.wms.module.inbound.dto.ReceivingDto;
import id.putra.wms.module.inbound.mapper.ReceivingMapper;
import id.putra.wms.module.inbound.model.repository.ReceivingRepository;
import id.putra.wms.module.inbound.service.adapter.command.impl.ReceivingCommandAdapterImpl;

@ExtendWith(MockitoExtension.class)
@SuppressWarnings("null")
public class ReceivingCommandAdapterTest {

    @Mock
    private ReceivingRepository receivingRepository;

    @Mock
    private ReceivingMapper receivingMapper;

    private ReceivingCommandAdapter receivingCommandAdapter;

    @BeforeEach
    void setUp() {
        receivingCommandAdapter = new ReceivingCommandAdapterImpl(receivingRepository, receivingMapper);
    }

    @Test
    void whenAddReceivingDtos_thenSaved() {
        ReceivingDto dto = new ReceivingDto();
        dto.setReceivingNumber("RCV-TEST-ADD");
        dto.setReceivedDate(new Date(System.currentTimeMillis()));
        dto.setStatus("NEW");

        receivingCommandAdapter.add(dto);

        verify(receivingRepository).save(any());
    }

    @Test
    void whenUpdateReceivingDto_thenEntityUpdated() {
        ReceivingDto dto = new ReceivingDto();
        dto.setId(1L);
        dto.setReceivingNumber("RCV-CMD-2-UPDATED");
        dto.setStatus("UPDATED");

        receivingCommandAdapter.update(dto);

        verify(receivingRepository).save(any());
    }

    @Test
    void whenDeleteReceivingDto_thenEntityRemoved() {
        ReceivingDto dto = new ReceivingDto();
        dto.setId(1L);

        receivingCommandAdapter.delete(dto);

        verify(receivingRepository).deleteById(anyLong());
    }

}
