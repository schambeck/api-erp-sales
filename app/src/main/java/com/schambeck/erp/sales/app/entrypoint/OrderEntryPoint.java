package com.schambeck.erp.sales.app.entrypoint;

import com.schambeck.erp.sales.app.entrypoint.controller.model.OrderWeb;

import java.util.List;
import java.util.UUID;

public interface OrderEntryPoint {
    OrderWeb create(OrderWeb orderWeb);
    void close(UUID id);
    OrderWeb findById(UUID id);
    List<OrderWeb> findAll();
}
