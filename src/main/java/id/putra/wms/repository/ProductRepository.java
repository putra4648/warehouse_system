package id.putra.wms.repository;

import org.springframework.data.repository.CrudRepository;

import id.putra.wms.entity.Product;

public interface ProductRepository extends CrudRepository<Product, String> {

}
