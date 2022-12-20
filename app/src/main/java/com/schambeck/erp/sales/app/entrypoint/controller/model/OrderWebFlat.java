package com.schambeck.erp.sales.app.entrypoint.controller.model;

import com.schambeck.erp.sales.app.dataprovider.entity.StatusOrder;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Builder(toBuilder = true)
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrderWebFlat {
    private UUID id;
    @NotNull(message = "Client is required")
    private UUID clientId;
    private StatusOrder status;
    private BigDecimal totalCost;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderWebFlat orderWeb = (OrderWebFlat) o;
        return Objects.equals(id, orderWeb.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
