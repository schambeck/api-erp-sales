package com.schambeck.erp.sales.core.entity;

import com.schambeck.erp.sales.core.entity.vo.StatusOrder;
import lombok.*;

import javax.validation.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import static java.math.BigDecimal.ZERO;

@Getter
@Setter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Order implements Serializable {
    private UUID id;
    @NotNull(message = "Client is required")
    private UUID clientId;
    @NotNull(message = "Issued date is required")
    private LocalDate issuedDate;
    private StatusOrder status;
    private BigDecimal totalCost;
    @Singular
    private List<OrderLine> items;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public static Order.OrderBuilder builder() {
        return new Order.CustomBuilder();
    }

    public static class CustomBuilder extends Order.OrderBuilder {
        @Override
        public Order build() {
            Order built = super.build();
            validate(built);
            if (built.getTotalCost() == null) {
                built.setTotalCost(ZERO);
            }
            return built;
        }

        @Override
        public OrderBuilder item(OrderLine item) {
            OrderBuilder order = super.item(item);
            if (order.totalCost == null) {
                return order.totalCost(item.getCost());
            } else {
                BigDecimal totalCost = order.totalCost.add(item.getCost());
                return order.totalCost(totalCost);
            }
        }

        @Override
        public OrderBuilder clearItems() {
            OrderBuilder order = super.clearItems();
            return order.totalCost(ZERO);
        }

        private void validate(Order order) {
            try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
                Validator validator = factory.getValidator();
                Set<ConstraintViolation<Order>> violations = validator.validate(order);
                if (!violations.isEmpty()) {
                    throw new ConstraintViolationException("Constraint validation", violations);
                }
            }
        }
    }
}
