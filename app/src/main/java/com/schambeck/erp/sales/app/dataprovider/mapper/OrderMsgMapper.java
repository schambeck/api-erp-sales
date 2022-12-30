package com.schambeck.erp.sales.app.dataprovider.mapper;

import com.schambeck.erp.sales.app.dataprovider.model.OrderLineMsg;
import com.schambeck.erp.sales.app.dataprovider.model.OrderMsg;
import com.schambeck.erp.sales.core.entity.Order;
import com.schambeck.erp.sales.core.entity.OrderLine;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OrderMsgMapper {
    OrderMsgMapper INSTANCE = Mappers.getMapper(OrderMsgMapper.class);

    @Mapping(target = "item", ignore = true)
    @Mapping(target = "items", ignore = true)
    Order toDomain(OrderMsg msg);

    @Mapping(target = "item", ignore = true)
    @Mapping(target = "items", ignore = true)
    OrderMsg toMsg(Order domain);

    List<OrderLine> mapFromMsg(List<OrderLineMsg> items);

    List<OrderLineMsg> mapFromDomain(List<OrderLine> items);

    @AfterMapping
    default void mapItems(@MappingTarget Order.OrderBuilder domain, OrderMsg msg) {
        domain.items(mapFromMsg(msg.getItems()));
    }

    @AfterMapping
    default void mapItems(@MappingTarget OrderMsg.OrderMsgBuilder msg, Order domain) {
        msg.items(mapFromDomain(domain.getItems()));
    }
}
