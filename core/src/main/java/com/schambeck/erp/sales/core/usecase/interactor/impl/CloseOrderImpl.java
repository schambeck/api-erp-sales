package com.schambeck.erp.sales.core.usecase.interactor.impl;

import com.schambeck.erp.sales.core.dataprovider.OrderNotifier;
import com.schambeck.erp.sales.core.dataprovider.OrderRepository;
import com.schambeck.erp.sales.core.entity.Order;
import com.schambeck.erp.sales.core.usecase.exception.BusinessException;
import com.schambeck.erp.sales.core.usecase.exception.NotFoundException;
import com.schambeck.erp.sales.core.usecase.interactor.CloseOrder;
import lombok.RequiredArgsConstructor;

import javax.inject.Named;
import java.util.UUID;

import static com.schambeck.erp.sales.core.entity.vo.StatusOrder.CLOSED;

@Named
@RequiredArgsConstructor
class CloseOrderImpl implements CloseOrder {
    private final OrderRepository repository;
    private final OrderNotifier notifier;

    @Override
    public void execute(UUID id) {
        Order order = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Entity %s not found".formatted(id)));
        validateStatus(order);
        validateItems(order);
        repository.updateStatus(id, CLOSED);
        notifier.sendMessage(order);
    }

    private static void validateStatus(Order order) {
        if (order.getStatus() == CLOSED) {
            throw new BusinessException("Order already closed: %s".formatted(order.getId()));
        }
    }

    private void validateItems(Order order) {
        if (order.getItems().isEmpty()) {
            throw new BusinessException("Order has no items: %s".formatted(order.getId()));
        }
    }
}
