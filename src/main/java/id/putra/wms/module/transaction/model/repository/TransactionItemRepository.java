package id.putra.wms.module.transaction.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import id.putra.wms.module.transaction.model.entity.TransactionItem;

public interface TransactionItemRepository extends JpaRepository<TransactionItem, String> {
}
