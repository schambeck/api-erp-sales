package com.schambeck.erp.sales.core.entity;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolationException;
import java.math.BigDecimal;
import java.util.UUID;

import static com.schambeck.erp.sales.core.entity.vo.StatusOrder.CREATED;
import static com.schambeck.erp.sales.core.util.MessageConstraintViolationMatcher.messageIs;
import static nl.jqno.equalsverifier.Warning.ALL_FIELDS_SHOULD_BE_USED;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OrderTest {
    @Test
    void equalsContract() {
        EqualsVerifier.simple()
                .suppress(ALL_FIELDS_SHOULD_BE_USED)
                .forClass(Order.class)
                .verify();
    }

    @Test
    void build_NoClientId_ShouldFail() {
        UUID orderId = UUID.fromString("dd9c24cc-b336-4f25-95de-bfd2ce7520fc");
        Order.OrderBuilder noClientId = Order.builder().id(orderId).status(CREATED);

        ConstraintViolationException exception = assertThrows(ConstraintViolationException.class, noClientId::build);

        assertThat(exception.getConstraintViolations(), hasItem(messageIs("Client is required")));
    }

    @Test
    void addItem_ValidTotalCost_ShouldPass() {
        Order.OrderBuilder createdOrder = Order.builder()
                .id(UUID.fromString("dd9c24cc-b336-4f25-95de-bfd2ce7520fc"))
                .clientId(UUID.fromString("89fc02d7-af79-473a-a792-ce4d6c188527"))
                .status(CREATED)
                .item(OrderLine.builder()
                        .productId(UUID.fromString("c90e4992-3e48-43ca-9da3-7ca012f44236"))
                        .quantity(new BigDecimal("3.00"))
                        .price(new BigDecimal("1.50"))
                        .build())
                .item(OrderLine.builder()
                        .productId(UUID.fromString("5da092c1-0abb-4b11-b1bd-a174f8a4a357"))
                        .quantity(new BigDecimal("2.00"))
                        .price(new BigDecimal("2.10"))
                        .build());

        Order created = createdOrder.build();

        assertEquals(new BigDecimal("8.7000"), created.getTotalCost());
    }

    @Test
    void clearItems_ZeroTotalCost_ShouldPass() {
        Order.OrderBuilder createdOrder = Order.builder()
                .id(UUID.fromString("dd9c24cc-b336-4f25-95de-bfd2ce7520fc"))
                .clientId(UUID.fromString("89fc02d7-af79-473a-a792-ce4d6c188527"))
                .status(CREATED)
                .item(OrderLine.builder()
                        .productId(UUID.fromString("c90e4992-3e48-43ca-9da3-7ca012f44236"))
                        .quantity(new BigDecimal("3.00"))
                        .price(new BigDecimal("1.50"))
                        .build());
        createdOrder.clearItems();

        Order created = createdOrder.build();

        assertEquals(new BigDecimal("0"), created.getTotalCost());
    }
}
