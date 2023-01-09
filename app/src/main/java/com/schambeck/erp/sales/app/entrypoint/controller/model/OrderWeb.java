package com.schambeck.erp.sales.app.entrypoint.controller.model;

import com.schambeck.erp.sales.app.dataprovider.entity.StatusOrder;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Builder(toBuilder = true)
@ToString(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
public class OrderWeb {
    @ToString.Include
    private UUID id;
    @NotNull(message = "Client is required")
    private UUID clientId;
    @NotNull(message = "Issued date is required")
    private LocalDate issuedDate;
    private StatusOrder status;
    private BigDecimal totalCost;
    @Singular
    private List<OrderLineWeb> items;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderWeb orderWeb = (OrderWeb) o;
        return Objects.equals(id, orderWeb.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
