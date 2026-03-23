package id.putra.wms.module.inbound.model.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import id.putra.wms.module.inbound.model.entity.PurchaseOrderLine;

public interface PurchaseOrderLineRepository extends JpaRepository<PurchaseOrderLine, Long>, JpaSpecificationExecutor<PurchaseOrderLine> {
    Page<PurchaseOrderLine> findByPurchaseOrderId(Long purchaseOrderId, Pageable pageable);
}
