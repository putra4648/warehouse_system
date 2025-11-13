package id.putra.wms.module.outbound.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import id.putra.wms.module.outbound.model.entity.PickingTask;

public interface PickingTaskRepository extends JpaRepository<PickingTask, Long>, JpaSpecificationExecutor<PickingTask> {

}
