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
@Table(name = "ORDER_LINE")
public class OrderLineEntity {
    @Id
    @Column(name = "ID")
    @GeneratedValue
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    @NotNull(message = "Order is required")
    private OrderEntity order;
    @Column(name = "PRODUCT_ID")
    @NotNull(message = "Product is required")
    private UUID productId;
    @Column(name = "QUANTITY")
    @NotNull(message = "Quantity is required")
    private BigDecimal quantity;
    @Column(name = "PRICE")
    @NotNull(message = "Price is required")
    private BigDecimal price;
    @Column(name = "COST")
    private BigDecimal cost;
}
