package com.schambeck.erp.sales.core.dataprovider;

import com.schambeck.erp.sales.core.entity.Order;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderRepository {
    Order create(Order order);
    Optional<Order> findById(UUID id);
    List<Order> findAll();
    void close(UUID id);
}
