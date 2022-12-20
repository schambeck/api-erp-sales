package com.schambeck.erp.sales.core.usecase.interactor.impl;

import com.schambeck.erp.sales.core.dataprovider.OrderRepository;
import com.schambeck.erp.sales.core.entity.Order;
import com.schambeck.erp.sales.core.usecase.exception.NotFoundException;
import com.schambeck.erp.sales.core.usecase.interactor.FindOrder;
import lombok.RequiredArgsConstructor;

import javax.inject.Named;
import java.util.List;
import java.util.UUID;

@Named
@RequiredArgsConstructor
class FindOrderImpl implements FindOrder {
    private final OrderRepository repository;
    @Override
    public Order findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Entity %s not found".formatted(id)));
    }
    @Override
    public List<Order> findAll() {
        return repository.findAll();
    }
}
