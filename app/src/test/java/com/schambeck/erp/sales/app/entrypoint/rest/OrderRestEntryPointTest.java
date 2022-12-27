package com.schambeck.erp.sales.app.entrypoint.rest;

import com.schambeck.erp.sales.app.entrypoint.controller.OrderController;
import com.schambeck.erp.sales.app.entrypoint.controller.model.OrderLineWeb;
import com.schambeck.erp.sales.app.entrypoint.controller.model.OrderWeb;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static com.schambeck.erp.sales.app.dataprovider.entity.StatusOrder.CREATED;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderRestEntryPointTest {
    @InjectMocks
    OrderRestEntryPoint restEntryPoint;
    @Mock
    OrderController controller;

    @Test
    void create() {
        UUID clientId = UUID.fromString("1750a631-3461-442e-8d99-8f0d6a127cb3");
        OrderWeb payload = OrderWeb.builder()
                .clientId(clientId)
                .item(OrderLineWeb.builder()
                        .productId(UUID.fromString("c90e4992-3e48-43ca-9da3-7ca012f44236"))
                        .quantity(new BigDecimal("3.00"))
                        .price(new BigDecimal("1.50"))
                        .build())
                .build();
        OrderWeb createdOrder = OrderWeb.builder()
                .id(UUID.fromString("6fbb52e1-3855-4ec9-bc3a-ad522ec19f16"))
                .clientId(clientId)
                .status(CREATED)
                .item(OrderLineWeb.builder()
                        .productId(UUID.fromString("c90e4992-3e48-43ca-9da3-7ca012f44236"))
                        .quantity(new BigDecimal("3.00"))
                        .price(new BigDecimal("1.50"))
                        .build())
                .build();
        when(controller.create(payload)).thenReturn(createdOrder);

        OrderWeb created = restEntryPoint.create(payload);

        assertNotNull(created);
        verify(controller).create(payload);
    }

    @Test
    void findAll() {
        UUID clientId = UUID.fromString("1750a631-3461-442e-8d99-8f0d6a127cb3");
        OrderWeb createdOrder = OrderWeb.builder()
                .id(UUID.fromString("6fbb52e1-3855-4ec9-bc3a-ad522ec19f16"))
                .clientId(clientId)
                .status(CREATED)
                .item(OrderLineWeb.builder()
                        .productId(UUID.fromString("c90e4992-3e48-43ca-9da3-7ca012f44236"))
                        .quantity(new BigDecimal("3.00"))
                        .price(new BigDecimal("1.50"))
                        .build())
                .build();
        when(controller.findAll()).thenReturn(List.of(createdOrder));

        List<OrderWeb> created = restEntryPoint.findAll();

        assertThat(created, hasItem(createdOrder));
        verify(controller).findAll();
    }

    @Test
    void findById() {
        UUID orderId = UUID.fromString("6fbb52e1-3855-4ec9-bc3a-ad522ec19f16");
        OrderWeb createdOrder = OrderWeb.builder()
                .id(orderId)
                .clientId(UUID.fromString("1750a631-3461-442e-8d99-8f0d6a127cb3"))
                .status(CREATED)
                .item(OrderLineWeb.builder()
                        .productId(UUID.fromString("c90e4992-3e48-43ca-9da3-7ca012f44236"))
                        .quantity(new BigDecimal("3.00"))
                        .price(new BigDecimal("1.50"))
                        .build())
                .build();
        when(controller.findById(orderId)).thenReturn(createdOrder);

        OrderWeb created = restEntryPoint.findById(orderId);

        assertNotNull(created);
        verify(controller).findById(orderId);
    }

    @Test
    void close() {
        UUID orderId = UUID.fromString("6fbb52e1-3855-4ec9-bc3a-ad522ec19f16");
        doNothing().when(controller).close(orderId);

        restEntryPoint.close(orderId);

        verify(controller).close(orderId);
    }
}
