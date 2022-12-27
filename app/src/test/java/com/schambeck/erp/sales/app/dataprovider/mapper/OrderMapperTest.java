package com.schambeck.erp.sales.app.dataprovider.mapper;

import com.schambeck.erp.sales.app.dataprovider.entity.OrderEntity;
import com.schambeck.erp.sales.app.dataprovider.entity.OrderLineEntity;
import com.schambeck.erp.sales.core.entity.Order;
import com.schambeck.erp.sales.core.entity.OrderLine;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNull;

class OrderMapperTest {
    @Test
    void toDomain_NullEntity_ShouldPass() {
        Order order = OrderMapper.INSTANCE.toDomain((OrderEntity) null);
        assertNull(order);
    }

    @Test
    void toDomain_NullEntities_ShouldPass() {
        List<Order> orders = OrderMapper.INSTANCE.toDomain((List<OrderEntity>) null);
        assertNull(orders);
    }

    @Test
    void toEntity_NullDomain_ShouldPass() {
        OrderEntity order = OrderMapper.INSTANCE.toEntity(null);
        assertNull(order);
    }

    @Test
    void mapFromEntity_NullEntities_ShouldPass() {
        List<OrderLine> orders = OrderMapper.INSTANCE.mapFromEntity(null);
        assertNull(orders);
    }

    @Test
    void mapFromEntity_NullItem_ShouldPass() {
        List<OrderLine> orders = OrderMapper.INSTANCE.mapFromEntity(Lists.newArrayList((OrderLineEntity) null));
        assertNull(orders.get(0));
    }

    @Test
    void mapFromDomain_NullDomains_ShouldPass() {
        List<OrderLineEntity> orders = OrderMapper.INSTANCE.mapFromDomain(null);
        assertNull(orders);
    }

    @Test
    void mapFromDomain_NullItem_ShouldPass() {
        List<OrderLineEntity> orders = OrderMapper.INSTANCE.mapFromDomain(Lists.newArrayList((OrderLine) null));
        assertNull(orders.get(0));
    }
}
