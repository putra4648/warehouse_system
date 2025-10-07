package id.putra.wms.module.transaction.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import id.putra.wms.module.transaction.model.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, String> {
}
