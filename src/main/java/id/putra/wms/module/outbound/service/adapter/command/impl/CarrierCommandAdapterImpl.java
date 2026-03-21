package id.putra.wms.module.outbound.service.adapter.command.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import id.putra.wms.module.outbound.dto.CarrierDto;
import id.putra.wms.module.outbound.mapper.CarrierMapper;
import id.putra.wms.module.outbound.model.repository.CarrierRepository;
import id.putra.wms.module.outbound.service.adapter.command.CarrierCommandAdapter;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CarrierCommandAdapterImpl implements CarrierCommandAdapter {
    private final CarrierRepository carrierRepository;
    private final CarrierMapper carrierMapper;

    @Override
    public void add(List<CarrierDto> dtos) {
        var entities = dtos.stream().map(carrierMapper::toEntity).toList();
        carrierRepository.saveAll(entities);
    }

    @Override
    public void update(List<CarrierDto> dtos) {
        var entities = dtos.stream().map(carrierMapper::toEntity).toList();
        carrierRepository.saveAll(entities);
    }

    @Override
    public void delete(List<CarrierDto> dtos) {
        var ids = dtos.stream().map(CarrierDto::getId).toList();
        carrierRepository.deleteAllById(ids);
    }
}
