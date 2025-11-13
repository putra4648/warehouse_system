package id.putra.wms.module.outbound.service.core;

import java.util.List;

import id.putra.wms.module.outbound.dto.PackingDto;
import id.putra.wms.shared.base.dto.response.ResponseData;

public interface PackingService {
    ResponseData<PackingDto> createPacking(PackingDto dto);
    ResponseData<PackingDto> updatePacking(PackingDto dto);
    ResponseData<Boolean> deletePacking(Long id);
    ResponseData<PackingDto> getPackingById(Long id);
    ResponseData<PackingDto> shipPacking(Long id);
    ResponseData<List<PackingDto>> getAllPackings(PackingDto filter);

}
