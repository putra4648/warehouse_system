package id.putra.wms.module.inbound.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import id.putra.wms.module.inbound.model.entity.Receiving;

public interface ReceivingRepository extends JpaRepository<Receiving, Long>, JpaSpecificationExecutor<Receiving> {

}
