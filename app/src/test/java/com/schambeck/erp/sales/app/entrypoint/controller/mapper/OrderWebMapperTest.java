package com.schambeck.erp.sales.app.entrypoint.controller.mapper;

import com.schambeck.erp.sales.app.entrypoint.controller.model.OrderLineWeb;
import com.schambeck.erp.sales.app.entrypoint.controller.model.OrderWeb;
import com.schambeck.erp.sales.core.entity.Order;
import com.schambeck.erp.sales.core.entity.OrderLine;
import com.schambeck.erp.sales.core.entity.vo.StatusOrder;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static com.schambeck.erp.sales.app.dataprovider.entity.StatusOrder.CLOSED;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class OrderWebMapperTest {
    @Test
    void toDomain_ClosedStatus_ShouldPass() {
        OrderWeb web = OrderWeb.builder()
                .clientId(UUID.fromString("d10f7108-ed25-4c77-adf0-a172492a5922"))
                .status(CLOSED)
                .build();
        Order order = OrderWebMapper.INSTANCE.toDomain(web);
        assertEquals(StatusOrder.CLOSED, order.getStatus());
    }

    @Test
    void toDomain_NullWeb_ShouldPass() {
        Order order = OrderWebMapper.INSTANCE.toDomain(null);
        assertNull(order);
    }

    @Test
    void toWeb_ClosedStatus_ShouldPass() {
        Order domain = Order.builder()
                .clientId(UUID.fromString("d10f7108-ed25-4c77-adf0-a172492a5922"))
                .status(StatusOrder.CLOSED)
                .build();
        OrderWeb order = OrderWebMapper.INSTANCE.toWeb(domain);
        assertEquals(CLOSED, order.getStatus());
    }

    @Test
    void toWeb_NullDomain_ShouldPass() {
        OrderWeb order = OrderWebMapper.INSTANCE.toWeb((Order) null);
        assertNull(order);
    }

    @Test
    void toWeb_NullStatus_ShouldPass() {
        Order domain = Order.builder()
                .clientId(UUID.fromString("d10f7108-ed25-4c77-adf0-a172492a5922"))
                .build();
        OrderWeb order = OrderWebMapper.INSTANCE.toWeb(domain);
        assertNull(order.getStatus());
    }

    @Test
    void toWeb_NullDomains_ShouldPass() {
        List<OrderWeb> order = OrderWebMapper.INSTANCE.toWeb((List<Order>) null);
        assertNull(order);
    }

    @Test
    void mapFromWeb_NullWebs_ShouldPass() {
        List<OrderLine> orders = OrderWebMapper.INSTANCE.mapFromWeb(null);
        assertNull(orders);
    }

    @Test
    void mapFromWeb_NullItem_ShouldPass() {
        List<OrderLine> orders = OrderWebMapper.INSTANCE.mapFromWeb(Lists.newArrayList((OrderLineWeb) null));
        assertNull(orders.get(0));
    }

    @Test
    void mapFromDomain_NullDomains_ShouldPass() {
        List<OrderLineWeb> orders = OrderWebMapper.INSTANCE.mapFromDomain(null);
        assertNull(orders);
    }

    @Test
    void mapFromDomain_NullItem_ShouldPass() {
        List<OrderLineWeb> orders = OrderWebMapper.INSTANCE.mapFromDomain(Lists.newArrayList((OrderLine) null));
        assertNull(orders.get(0));
    }
}
