package id.putra.wms.module.inventory.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import id.putra.wms.module.inventory.model.entity.ProductSupplierId;

public interface ProductSupplierIdRepository extends JpaRepository<ProductSupplierId, String> {
}
