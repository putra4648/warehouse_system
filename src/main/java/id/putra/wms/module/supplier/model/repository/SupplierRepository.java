package id.putra.wms.module.supplier.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import id.putra.wms.shared.base.entity.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, String> {
}
