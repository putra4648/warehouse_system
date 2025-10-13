package id.putra.wms.module.supplier.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import id.putra.wms.module.supplier.model.entity.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, String> {
}
