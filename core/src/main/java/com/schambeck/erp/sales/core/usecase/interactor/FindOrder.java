package com.schambeck.erp.sales.core.usecase.interactor;

import com.schambeck.erp.sales.core.entity.Order;

import java.util.List;
import java.util.UUID;

public interface FindOrder {
    Order findById(UUID id);
    List<Order> findAll();
}
