package com.schambeck.erp.sales.app.dataprovider;

import com.schambeck.erp.sales.app.dataprovider.entity.OrderEntity;
import com.schambeck.erp.sales.app.dataprovider.entity.StatusOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
public interface JpaOrderRepository extends JpaRepository<OrderEntity, UUID> {
    @Transactional
    @Modifying
    @Query("""
                UPDATE OrderEntity o
                   SET o.status = :status
                 WHERE o.id = :id
            """)
    void updateStatus(@Param(value = "id") UUID id,
                      @Param(value = "status") StatusOrder status);
}
