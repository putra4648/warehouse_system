package id.putra.wms.module.inventory.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import id.putra.wms.shared.base.entity.Product;

public interface ProductRepository extends JpaRepository<Product, String> {

}
