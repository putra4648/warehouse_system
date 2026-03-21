package id.putra.wms.module.outbound.service.adapter.query.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.putra.wms.module.outbound.dto.SalesOrderDto;
import id.putra.wms.module.outbound.mapper.SalesOrderMapper;
import id.putra.wms.module.outbound.model.entity.SalesOrder;
import id.putra.wms.module.outbound.model.repository.SalesOrderRepository;
import id.putra.wms.module.outbound.service.adapter.query.SalesOrderQueryAdapter;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SalesOrderQueryAdapterImpl implements SalesOrderQueryAdapter {
    private final SalesOrderRepository salesOrderRepository;
    private final SalesOrderMapper salesOrderMapper;

    @Override
    public SalesOrderDto getById(Long id) {
        return salesOrderRepository.findById(id).map(salesOrderMapper::toDto).orElse(null);
    }

    @Override
    public Page<SalesOrderDto> getAll(String search, Pageable pageable) {
        Specification<SalesOrder> spec = (root, query, criteriaBuilder) -> {
            if (search == null || search.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(root.get("soNumber"), "%" + search + "%");
        };
        return salesOrderRepository.findAll(spec, pageable).map(salesOrderMapper::toDto);
    }
}
