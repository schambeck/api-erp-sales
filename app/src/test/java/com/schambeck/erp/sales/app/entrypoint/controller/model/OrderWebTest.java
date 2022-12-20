package com.schambeck.erp.sales.app.entrypoint.controller.model;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

import static nl.jqno.equalsverifier.Warning.ALL_FIELDS_SHOULD_BE_USED;

class OrderWebTest {
    @Test
    void equalsContract() {
        EqualsVerifier.simple()
                .suppress(ALL_FIELDS_SHOULD_BE_USED)
                .forClass(OrderWeb.class)
                .verify();
    }

}