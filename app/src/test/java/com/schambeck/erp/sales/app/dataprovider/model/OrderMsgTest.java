package com.schambeck.erp.sales.app.dataprovider.model;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

import static nl.jqno.equalsverifier.Warning.ALL_FIELDS_SHOULD_BE_USED;

class OrderMsgTest {
    @Test
    void equalsContract() {
        EqualsVerifier.simple()
                .suppress(ALL_FIELDS_SHOULD_BE_USED)
                .forClass(OrderMsg.class)
                .verify();
    }
}
