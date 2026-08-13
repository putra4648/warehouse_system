package id.putra.wms.module.transaction.converter;

import id.putra.wms.module.transaction.dto.TransactionDto;
import id.putra.wms.module.transaction.model.entity.Transaction;

/**
 * Converter for Transaction entity and DTO.
 * Provides static methods for converting between Transaction entity and TransactionDto.
 */
public class TransactionConverter {

    private TransactionConverter() {
        // Private constructor to prevent instantiation
    }

    /**
     * Converts a Transaction entity to TransactionDto.
     *
     * @param entity the Transaction entity
     * @return the TransactionDto, or null if entity is null
     */
    public static TransactionDto toDto(Transaction entity) {
        if (entity == null) {
            return null;
        }
        
        return TransactionDto.builder()
                .id(entity.getId())
                .customerId(entity.getCustomer() != null ? entity.getCustomer().getId() : null)
                .supplierId(entity.getSupplier() != null ? entity.getSupplier().getId() : null)
                .totalAmount(entity.getTotalAmount())
                .notes(entity.getNotes())
                .build();
    }

    /**
     * Converts a TransactionDto to Transaction entity.
     *
     * @param dto the TransactionDto
     * @return the Transaction entity, or null if dto is null
     */
    public static Transaction toEntity(TransactionDto dto) {
        if (dto == null) {
            return null;
        }
        
        Transaction entity = new Transaction();
        entity.setTotalAmount(dto.getTotalAmount());
        entity.setNotes(dto.getNotes());
        entity.setCreatedAt(dto.getCreatedAt());
        entity.setUpdatedAt(dto.getUpdatedAt());
        
        return entity;
    }
}
