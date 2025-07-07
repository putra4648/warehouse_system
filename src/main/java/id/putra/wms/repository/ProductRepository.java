package id.putra.wms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import id.putra.wms.entity.Product;

public interface ProductRepository extends JpaRepository<Product, String> {

}
