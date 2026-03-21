package id.putra.wms.module.inbound.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

import id.putra.wms.module.inbound.dto.PurchaseOrderDto;
import id.putra.wms.module.inbound.model.entity.PurchaseOrder;
import id.putra.wms.module.supplier.mapper.SupplierMapper;
import id.putra.wms.shared.base.entity.Supplier;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = { SupplierMapper.class })
public interface PurchaseOrderMapper {

    @Mapping(target = "supplier", source = "supplierId", qualifiedByName = "mapIdToSupplier")
    @Mapping(target = "receivings", ignore = true)
    PurchaseOrder toEntity(PurchaseOrderDto dto);

    @Mapping(target = "supplierId", source = "supplier", qualifiedByName = "mapSupplierToId")
    PurchaseOrderDto toDto(PurchaseOrder entity);

    List<PurchaseOrderDto> toDtos(List<PurchaseOrder> entities);

    @Named("mapSupplierToId")
    default Long mapSupplierToId(Supplier supplier) {
        if (supplier == null) {
            return null;
        }
        return supplier.getId();
    }

    @Named("mapIdToSupplier")
    default Supplier mapIdToSupplier(Long id) {
        if (id == null) {
            return null;
        }
        Supplier entity = new Supplier();
        entity.setId(id);
        return entity;
    }

}
