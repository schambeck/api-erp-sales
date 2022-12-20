package com.schambeck.erp.sales.core.entity;

import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolationException;
import java.math.BigDecimal;
import java.util.UUID;

import static com.schambeck.erp.sales.core.util.MessageConstraintViolationMatcher.messageIs;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OrderLineTest {
    @Test
    void cost_ValidItem_ShouldPass() {
        OrderLine.OrderLineBuilder item = OrderLine.builder()
                .productId(UUID.fromString("7a4142b6-c672-4e92-a42c-d4f93735d480"))
                .price(new BigDecimal("1.50"))
                .quantity(new BigDecimal("3.00"));

        OrderLine built = item.build();

        assertEquals(new BigDecimal("4.5000"), built.getCost());
    }

    @Test
    void build_NoProductId_ShouldFail() {
        OrderLine.OrderLineBuilder noProductItem = OrderLine.builder()
                .price(new BigDecimal("1.50"))
                .quantity(new BigDecimal("3.00"));

        ConstraintViolationException exception = assertThrows(ConstraintViolationException.class, noProductItem::build);

        assertThat(exception.getConstraintViolations(), hasItem(messageIs("Product is required")));
    }

    @Test
    void build_NoPrice_ShouldFail() {
        OrderLine.OrderLineBuilder noPriceItem = OrderLine.builder()
                .productId(UUID.fromString("7a4142b6-c672-4e92-a42c-d4f93735d480"))
                .quantity(new BigDecimal("3.00"));

        ConstraintViolationException exception = assertThrows(ConstraintViolationException.class, noPriceItem::build);

        assertThat(exception.getConstraintViolations(), hasItem(messageIs("Price is required")));
    }

    @Test
    void build_NoQuantity_ShouldFail() {
        OrderLine.OrderLineBuilder noQuantityItem = OrderLine.builder()
                .productId(UUID.fromString("7a4142b6-c672-4e92-a42c-d4f93735d480"))
                .price(new BigDecimal("1.50"));

        ConstraintViolationException exception = assertThrows(ConstraintViolationException.class, noQuantityItem::build);

        assertThat(exception.getConstraintViolations(), hasItem(messageIs("Quantity is required")));
    }
}