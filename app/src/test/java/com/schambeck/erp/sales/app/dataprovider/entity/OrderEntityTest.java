package com.schambeck.erp.sales.app.dataprovider.entity;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static nl.jqno.equalsverifier.Warning.SURROGATE_KEY;

class OrderEntityTest {
    @Test
    void equalsContract() {
        EqualsVerifier.simple()
                .forClass(OrderEntity.class)
                .withPrefabValues(OrderEntity.class, new OrderEntity(UUID.fromString("ceea6a32-7e5a-4c39-9b3a-a1ad000148d2")), new OrderEntity(UUID.fromString("12025f35-4d6a-42d8-9e86-97dfbd27b80f")))
                .suppress(SURROGATE_KEY)
                .verify();
    }
}