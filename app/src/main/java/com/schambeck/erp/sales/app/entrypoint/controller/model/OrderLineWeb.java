package com.schambeck.erp.sales.app.entrypoint.controller.model;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Builder(toBuilder = true)
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrderLineWeb {
    @NotNull(message = "Product is required")
    private UUID productId;
    @NotNull(message = "Quantity is required")
    private BigDecimal quantity;
    @NotNull(message = "Price is required")
    private BigDecimal price;
    private BigDecimal cost;
}
