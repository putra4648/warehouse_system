package id.putra.wms.module.outbound.service.adapter.query.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.putra.wms.module.outbound.converter.CarrierConverter;
import id.putra.wms.module.outbound.dto.CarrierDto;
import id.putra.wms.module.outbound.model.repository.CarrierRepository;
import id.putra.wms.module.outbound.service.adapter.query.CarrierQueryAdapter;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CarrierQueryAdapterImpl implements CarrierQueryAdapter {
    private final CarrierRepository carrierRepository;

    @Override
    public CarrierDto getById(Long id) {
        return carrierRepository.findById(id).map(CarrierConverter::toDto).orElse(null);
    }

    @Override
    public List<CarrierDto> getAll(CarrierDto filter) {
        return carrierRepository.findAll().stream().map(CarrierConverter::toDto).toList();
    }
}
