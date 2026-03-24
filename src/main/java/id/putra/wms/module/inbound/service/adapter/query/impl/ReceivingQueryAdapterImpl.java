package id.putra.wms.module.inbound.service.adapter.query.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.putra.wms.module.inbound.dto.ReceivingDto;
import id.putra.wms.module.inbound.mapper.ReceivingMapper;
import id.putra.wms.module.inbound.model.entity.Receiving;
import id.putra.wms.module.inbound.model.entity.ReceivingLine;
import id.putra.wms.module.inbound.model.repository.ReceivingLineRepository;
import id.putra.wms.module.inbound.model.repository.ReceivingRepository;
import id.putra.wms.module.inbound.service.adapter.query.ReceivingQueryAdapter;
import id.putra.wms.shared.base.dto.response.attribute.MetaAttribute;
import id.putra.wms.shared.enums.ResponseEnum;
import id.putra.wms.shared.exceptions.ModuleException;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReceivingQueryAdapterImpl implements ReceivingQueryAdapter {

    private final ReceivingRepository receivingRepository;
    private final ReceivingLineRepository receivingLineRepository;
    private final ReceivingMapper receivingMapper;

    @Override
    public ReceivingDto getById(@NonNull Long id, @NonNull Pageable pageable) {
        Receiving receiving = receivingRepository.findById(id)
                .orElseThrow(() -> new ModuleException(ResponseEnum.DATA_NOT_FOUND));

        ReceivingDto dto = receivingMapper.toDto(receiving);
        Page<ReceivingLine> linesPage = receivingLineRepository.findByReceivingId(id, pageable);

        dto.setReceivingLines(linesPage.getContent().stream()
                .map(receivingMapper::toLineDto)
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
    public Page<ReceivingDto> getAll(String search, @NonNull Pageable pageable) {
        Specification<Receiving> spec = (root, query, criteriaBuilder) -> {
            if (search == null || search.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            Predicate code = criteriaBuilder.like(root.get("code"), "%" + search + "%");
            Predicate poCode = criteriaBuilder.like(root.get("purchaseOrder").get("code"), "%" + search + "%");
            return criteriaBuilder.or(code, poCode);
        };
        return receivingRepository.findAll(spec, pageable).map(receivingMapper::toDto);
    }
}
