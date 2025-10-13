package id.putra.wms.module.supplier.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import id.putra.wms.module.supplier.model.entity.ProductSupplier;
import id.putra.wms.module.supplier.model.entity.ProductSupplierId;

public interface ProductSupplierRepository extends JpaRepository<ProductSupplier, ProductSupplierId> {
}
