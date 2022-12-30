package com.schambeck.erp.sales.core.dataprovider;

import com.schambeck.erp.sales.core.entity.Order;

public interface OrderNotifier {
    void sendMessage(Order order);
}
