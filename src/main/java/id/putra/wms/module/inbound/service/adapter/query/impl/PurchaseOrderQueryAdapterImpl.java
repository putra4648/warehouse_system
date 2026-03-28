package id.putra.wms.module.inbound.service.adapter.query.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.putra.wms.module.inbound.dto.PurchaseOrderDto;
import id.putra.wms.module.inbound.mapper.PurchaseOrderMapper;
import id.putra.wms.module.inbound.model.entity.PurchaseOrder;
import id.putra.wms.module.inbound.model.repository.PurchaseOrderLineRepository;
import id.putra.wms.module.inbound.model.repository.PurchaseOrderRepository;
import id.putra.wms.module.inbound.service.adapter.query.PurchaseOrderQueryAdapter;
import id.putra.wms.shared.base.dto.response.attribute.MetaAttribute;
import id.putra.wms.shared.enums.ResponseEnum;
import id.putra.wms.shared.exceptions.ModuleException;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PurchaseOrderQueryAdapterImpl implements PurchaseOrderQueryAdapter {

    private final PurchaseOrderRepository purchaseOrderRepository;
    private final PurchaseOrderLineRepository purchaseOrderLineRepository;
    private final PurchaseOrderMapper purchaseOrderMapper;

    @Override
    public PurchaseOrderDto getById(@NonNull Long id, @NonNull Pageable pageable) {

        PurchaseOrder po = purchaseOrderRepository.findById(id)
                .orElseThrow(() -> new ModuleException(ResponseEnum.DATA_NOT_FOUND));

        PurchaseOrderDto dto = purchaseOrderMapper.toDto(po);
        Page<id.putra.wms.module.inbound.model.entity.PurchaseOrderLine> linesPage = purchaseOrderLineRepository
                .findByPurchaseOrderId(id, pageable);

        dto.setPurchaseOrderLines(linesPage.getContent().stream()
                .map(purchaseOrderMapper::toLineDto)
                .collect(java.util.stream.Collectors.toList()));

        dto.setMeta(MetaAttribute.builder()
                .page(linesPage.getNumber())
                .size(linesPage.getSize())
                .total(linesPage.getTotalElements())
                .lastPage(linesPage.getTotalPages())
                .build());

        return dto;
    }

    @Override
    public Page<PurchaseOrderDto> getAll(PurchaseOrderDto dto, @NonNull Pageable pageable) {
        Specification<PurchaseOrder> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (dto != null) {
                if (StringUtils.isNotBlank(dto.getPoNumber())) {
                    predicates.add(cb.like(root.get("poNumber"), "%" + dto.getPoNumber() + "%"));
                }

                if (dto.getStatus() != null) {
                    predicates.add(cb.equal(root.get("status"), dto.getStatus()));
                }
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
        return purchaseOrderRepository.findAll(spec, pageable).map(purchaseOrderMapper::toDto);
    }
}
