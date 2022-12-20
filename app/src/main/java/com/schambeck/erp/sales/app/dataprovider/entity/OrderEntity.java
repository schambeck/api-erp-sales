package com.schambeck.erp.sales.app.dataprovider.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.*;

import static javax.persistence.CascadeType.ALL;

@Getter
@Setter
@Entity
@Table(name = "order")
public class OrderEntity {
    public OrderEntity() {
    }

    public OrderEntity(UUID id) {
        this.id = id;
    }

    @Id
    @NotNull(message = "Id is required")
    private UUID id;
    @NotNull(message = "Client is required")
    private UUID clientId;
    @NotNull(message = "Status is required")
    private StatusOrder status;
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

    @Override
    public String toString() {
        return "OrderEntity{" +
                "id=" + id +
                ", clientId=" + clientId +
                ", status=" + status +
                ", totalCost=" + totalCost +
                '}';
    }
}
