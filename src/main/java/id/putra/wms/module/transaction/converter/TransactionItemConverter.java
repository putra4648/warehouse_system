package id.putra.wms.module.transaction.converter;

import id.putra.wms.module.transaction.dto.TransactionItemDto;
import id.putra.wms.module.transaction.model.entity.TransactionItem;

/**
 * Converter for TransactionItem entity and DTO.
 * Provides static methods for converting between TransactionItem entity and TransactionItemDto.
 */
public class TransactionItemConverter {

    private TransactionItemConverter() {
        // Private constructor to prevent instantiation
    }

    /**
     * Converts a TransactionItem entity to TransactionItemDto.
     *
     * @param entity the TransactionItem entity
     * @return the TransactionItemDto, or null if entity is null
     */
    public static TransactionItemDto toDto(TransactionItem entity) {
        if (entity == null) {
            return null;
        }
        
        return TransactionItemDto.builder()
                .id(entity.getId())
                .transactionId(entity.getTransaction() != null ? entity.getTransaction().getId() : null)
                .productId(entity.getProduct() != null ? entity.getProduct().getId() : null)
                .quantity(entity.getQuantity())
                .unitPrice(entity.getUnitPrice())
                .totalPrice(entity.getTotalPrice())
                .build();
    }

    /**
     * Converts a TransactionItemDto to TransactionItem entity.
     *
     * @param dto the TransactionItemDto
     * @return the TransactionItem entity, or null if dto is null
     */
    public static TransactionItem toEntity(TransactionItemDto dto) {
        if (dto == null) {
            return null;
        }
        
        TransactionItem entity = new TransactionItem();
        entity.setId(dto.getId());
        entity.setQuantity(dto.getQuantity());
        entity.setUnitPrice(dto.getUnitPrice());
        entity.setTotalPrice(dto.getTotalPrice());
        entity.setCreatedAt(dto.getCreatedAt());
        entity.setUpdatedAt(dto.getUpdatedAt());
        return entity;
    }
}
