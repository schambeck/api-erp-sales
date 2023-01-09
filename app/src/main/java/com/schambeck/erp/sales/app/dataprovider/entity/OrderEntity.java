package com.schambeck.erp.sales.app.dataprovider.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import static javax.persistence.CascadeType.ALL;

@Getter
@Setter
@Entity
@Table(name = "ORDER")
@ToString(exclude = "items")
public class OrderEntity {
    public OrderEntity() {
    }

    public OrderEntity(UUID id) {
        this.id = id;
    }

    @Id
    @Column(name = "ID")
    @NotNull(message = "Id is required")
    private UUID id;
    @Column(name = "CLIENT_ID")
    @NotNull(message = "Client is required")
    private UUID clientId;
    @Column(name = "ISSUED_DATE")
    @NotNull(message = "Issued date is required")
    private LocalDate issuedDate;
    @Column(name = "STATUS")
    @NotNull(message = "Status is required")
    private StatusOrder status;
    @Column(name = "TOTAL_COST")
    @NotNull(message = "Total cost is required")
    private BigDecimal totalCost;
    @OneToMany(cascade = ALL, orphanRemoval = true, mappedBy = "order")
    private List<OrderLineEntity> items = new ArrayList<>();

    public List<OrderLineEntity> getItems() {
        return Collections.unmodifiableList(items);
    }

    public void addItems(List<OrderLineEntity> items) {
        items.forEach(this::addItem);
    }
    
    public void addItem(OrderLineEntity item) {
        items.add(item);
        item.setOrder(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderEntity that = (OrderEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
