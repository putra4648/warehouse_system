package id.putra.wms.module.customer.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import id.putra.wms.module.customer.model.entity.ContactPersonCustomer;

public interface ContactPersonCustomerRepository extends JpaRepository<ContactPersonCustomer, String> {
}
