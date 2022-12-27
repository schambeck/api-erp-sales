package com.schambeck.erp.sales.app.dataprovider.mapper;

import com.schambeck.erp.sales.app.dataprovider.entity.OrderEntity;
import com.schambeck.erp.sales.app.dataprovider.entity.OrderLineEntity;
import com.schambeck.erp.sales.core.entity.Order;
import com.schambeck.erp.sales.core.entity.OrderLine;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(unmappedTargetPolicy = IGNORE)
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Mapping(target = "items", ignore = true)
    Order toDomain(OrderEntity entity);

    List<Order> toDomain(List<OrderEntity> entities);

    @Mapping(target = "items", ignore = true)
    OrderEntity toEntity(Order domain);

    List<OrderLine> mapFromEntity(List<OrderLineEntity> items);

    List<OrderLineEntity> mapFromDomain(List<OrderLine> items);

    @AfterMapping
    default void mapItems(@MappingTarget Order.OrderBuilder domain, OrderEntity entity) {
        domain.items(mapFromEntity(entity.getItems()));
    }

    @AfterMapping
    default void mapItems(@MappingTarget OrderEntity entity, Order domain) {
        entity.addItems(mapFromDomain(domain.getItems()));
    }
}
