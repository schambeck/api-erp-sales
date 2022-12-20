package com.schambeck.erp.sales.core.entity;

import lombok.Builder;
import lombok.Data;

import javax.validation.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

@Data
@Builder(toBuilder = true)
public class OrderLine implements Serializable {
    @NotNull(message = "Product is required")
    private UUID productId;
    @NotNull(message = "Quantity is required")
    private BigDecimal quantity;
    @NotNull(message = "Price is required")
    private BigDecimal price;
    private BigDecimal cost;

    public static OrderLine.OrderLineBuilder builder() {
        return new CustomBuilder();
    }

    private static class CustomBuilder extends OrderLine.OrderLineBuilder {
        @Override
        public OrderLine build() {
            OrderLine built = super.build();
            validate(built);
            built.setCost(calcCost(built));
            return built;
        }

        private static void validate(OrderLine item) {
            try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
                Validator validator = factory.getValidator();
                Set<ConstraintViolation<OrderLine>> violations = validator.validate(item);
                if (!violations.isEmpty()) {
                    throw new ConstraintViolationException("Constraint validation", violations);
                }
            }
        }

        private static BigDecimal calcCost(OrderLine built) {
            return built.getPrice().multiply(built.getQuantity());
        }
    }
}
