package com.schambeck.erp.sales.core.usecase.interactor.impl;

import com.schambeck.erp.sales.core.entity.Order;
import com.schambeck.erp.sales.core.usecase.interactor.ConsumeOrder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Named;

@Slf4j
@Named
@RequiredArgsConstructor
class ConsumeOrderImpl implements ConsumeOrder {
    @Override
    public void execute(Order order) {
        log.info("received: {}", order);
    }
}
