package id.putra.wms.module.inventory.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import id.putra.wms.module.inventory.model.entity.ProductSupplier;
import id.putra.wms.module.inventory.model.entity.ProductSupplierId;

public interface ProductSupplierRepository extends JpaRepository<ProductSupplier, ProductSupplierId> {
}
