package com.schambeck.erp.sales.core.usecase.interactor;

import com.schambeck.erp.sales.core.entity.Order;

public interface CreateOrder {
    Order execute(Order order);
}
