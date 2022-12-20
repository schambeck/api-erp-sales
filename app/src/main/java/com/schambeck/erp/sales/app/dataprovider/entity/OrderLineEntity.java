package com.schambeck.erp.sales.app.dataprovider.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@ToString
@Entity
@Table(name = "order_line")
public class OrderLineEntity {
    @Id
    @GeneratedValue
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "order_id")
    @NotNull(message = "Order is required")
    private OrderEntity order;
    @NotNull(message = "Product is required")
    private UUID productId;
    @NotNull(message = "Quantity is required")
    private BigDecimal quantity;
    @NotNull(message = "Price is required")
    private BigDecimal price;
    private BigDecimal cost;
}
