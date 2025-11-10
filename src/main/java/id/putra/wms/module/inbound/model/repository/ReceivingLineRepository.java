package id.putra.wms.module.inbound.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import id.putra.wms.module.inbound.model.entity.ReceivingLine;

public interface ReceivingLineRepository extends JpaRepository<ReceivingLine, Long>, JpaSpecificationExecutor<ReceivingLine> {

}
