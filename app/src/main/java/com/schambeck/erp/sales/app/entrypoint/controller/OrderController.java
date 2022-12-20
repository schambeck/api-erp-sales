package com.schambeck.erp.sales.app.entrypoint.controller;

import com.schambeck.erp.sales.app.dataprovider.mapper.OrderMapper;
import com.schambeck.erp.sales.app.entrypoint.controller.model.OrderWeb;
import com.schambeck.erp.sales.core.usecase.interactor.CloseOrder;
import com.schambeck.erp.sales.core.usecase.interactor.CreateOrder;
import com.schambeck.erp.sales.core.usecase.interactor.FindOrder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderController {
    private final CreateOrder create;
    private final CloseOrder close;
    private final FindOrder find;

    public OrderWeb create(OrderWeb web) {
        var order = OrderMapper.INSTANCE.toDomain(web);
        OrderWeb created = OrderMapper.INSTANCE.toWeb(create.execute(order));
        log.info("create: {}", created);
        return created;
    }

    public void close(UUID id) {
        close.execute(id);
        log.info("close: {}", id);
    }

    public OrderWeb findById(UUID id) {
        OrderWeb web = OrderMapper.INSTANCE.toWeb(find.findById(id));
        log.info("findById: {}", web);
        return web;
    }

    public List<OrderWeb> findAll() {
        log.info("before findAll...");
        List<OrderWeb> webs = OrderMapper.INSTANCE.fromDomain(find.findAll());
        log.info("after findAll: {}", webs);
        return webs;
    }
}
