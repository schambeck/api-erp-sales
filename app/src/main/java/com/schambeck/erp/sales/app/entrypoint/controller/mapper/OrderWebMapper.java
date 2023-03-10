package com.schambeck.erp.sales.app.entrypoint.controller.mapper;

import com.schambeck.erp.sales.app.entrypoint.controller.model.OrderLineWeb;
import com.schambeck.erp.sales.app.entrypoint.controller.model.OrderWeb;
import com.schambeck.erp.sales.core.entity.Order;
import com.schambeck.erp.sales.core.entity.OrderLine;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OrderWebMapper {
    OrderWebMapper INSTANCE = Mappers.getMapper(OrderWebMapper.class);

    @Mapping(target = "item", ignore = true)
    @Mapping(target = "items", ignore = true)
    Order toDomain(OrderWeb web);

    @Mapping(target = "item", ignore = true)
    @Mapping(target = "items", ignore = true)
    OrderWeb toWeb(Order domain);

    List<OrderWeb> toWeb(List<Order> domains);

    List<OrderLine> mapFromWeb(List<OrderLineWeb> items);

    List<OrderLineWeb> mapFromDomain(List<OrderLine> items);

    @AfterMapping
    default void mapItems(@MappingTarget Order.OrderBuilder domain, OrderWeb web) {
        domain.items(mapFromWeb(web.getItems()));
    }

    @AfterMapping
    default void mapItems(@MappingTarget OrderWeb.OrderWebBuilder web, Order domain) {
        web.items(mapFromDomain(domain.getItems()));
    }
}
