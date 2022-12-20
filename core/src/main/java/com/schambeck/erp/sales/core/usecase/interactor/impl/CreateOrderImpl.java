package com.schambeck.erp.sales.core.usecase.interactor.impl;

import com.schambeck.erp.sales.core.dataprovider.OrderRepository;
import com.schambeck.erp.sales.core.entity.Order;
import com.schambeck.erp.sales.core.usecase.generator.IdGenerator;
import com.schambeck.erp.sales.core.usecase.interactor.CreateOrder;
import lombok.RequiredArgsConstructor;

import javax.inject.Named;

import static com.schambeck.erp.sales.core.entity.vo.StatusOrder.CREATED;

@Named
@RequiredArgsConstructor
class CreateOrderImpl implements CreateOrder {
	private final OrderRepository repository;
	private final IdGenerator idGenerator;
	@Override
	public Order execute(Order order) {
		Order toCreate = order.toBuilder()
			.id(idGenerator.generate())
			.status(CREATED)
			.build();
		return repository.create(toCreate);
    }
}
