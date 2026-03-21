package id.putra.wms.module.inbound.service.adapter.query.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.putra.wms.module.inbound.dto.PurchaseOrderDto;
import id.putra.wms.module.inbound.mapper.PurchaseOrderMapper;
import id.putra.wms.module.inbound.model.entity.PurchaseOrder;
import id.putra.wms.module.inbound.model.repository.PurchaseOrderRepository;
import id.putra.wms.module.inbound.service.adapter.query.PurchaseOrderQueryAdapter;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PurchaseOrderQueryAdapterImpl implements PurchaseOrderQueryAdapter {

    private final PurchaseOrderRepository purchaseOrderRepository;
    private final PurchaseOrderMapper purchaseOrderMapper;

    @Override
    public PurchaseOrderDto getById(@Nullable Long id) {
        if (id == null)
            return null;
        return purchaseOrderRepository.findById(id).map(purchaseOrderMapper::toDto).orElse(null);
    }

    @Override
    public Page<PurchaseOrderDto> getAll(String search, Pageable pageable) {
        Specification<PurchaseOrder> spec = (root, query, criteriaBuilder) -> {
            if (search == null || search.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(root.get("poNumber"), "%" + search + "%");
        };
        return purchaseOrderRepository.findAll(spec, pageable).map(purchaseOrderMapper::toDto);
    }
}
