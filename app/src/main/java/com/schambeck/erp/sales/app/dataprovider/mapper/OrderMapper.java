package com.schambeck.erp.sales.app.dataprovider.mapper;

import com.schambeck.erp.sales.app.dataprovider.entity.OrderEntity;
import com.schambeck.erp.sales.app.dataprovider.entity.OrderLineEntity;
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
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Mapping(target = "item", ignore = true)
    @Mapping(target = "items", ignore = true)
    Order toDomain(OrderWeb web);

    @Mapping(target = "item", ignore = true)
    @Mapping(target = "items", ignore = true)
    Order toDomain(OrderEntity entity);

    @Mapping(target = "item", ignore = true)
    @Mapping(target = "items", ignore = true)
    List<Order> toDomain(List<OrderEntity> entities);

    @Mapping(target = "item", ignore = true)
    @Mapping(target = "items", ignore = true)
    OrderWeb toWeb(Order domain);

    List<OrderWeb> fromDomain(List<Order> domains);

    @Mapping(target = "items", ignore = true)
    OrderEntity toEntity(Order domain);

    List<OrderLine> mapFromWeb(List<OrderLineWeb> items);

    List<OrderLine> mapFromEntity(List<OrderLineEntity> items);

    List<OrderLineWeb> mapFromDomain(List<OrderLine> items);

    List<OrderLineEntity> mapToEntity(List<OrderLine> items);

    @AfterMapping
    default void mapItems(@MappingTarget Order.OrderBuilder domain, OrderWeb web) {
        domain.items(mapFromWeb(web.getItems()));
    }

    @AfterMapping
    default void mapItems(@MappingTarget Order.OrderBuilder domain, OrderEntity entity) {
        domain.items(mapFromEntity(entity.getItems()));
    }

    @AfterMapping
    default void mapItems(@MappingTarget OrderWeb.OrderWebBuilder web, Order domain) {
        web.items(mapFromDomain(domain.getItems()));
    }

    @AfterMapping
    default void mapItems(@MappingTarget OrderEntity entity, Order domain) {
        entity.addItems(mapToEntity(domain.getItems()));
    }
}
