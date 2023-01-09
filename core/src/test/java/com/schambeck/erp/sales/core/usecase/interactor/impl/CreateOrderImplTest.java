package com.schambeck.erp.sales.core.usecase.interactor.impl;

import com.schambeck.erp.sales.core.dataprovider.OrderRepository;
import com.schambeck.erp.sales.core.entity.Order;
import com.schambeck.erp.sales.core.usecase.generator.IdGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.UUID;

import static com.schambeck.erp.sales.core.entity.vo.StatusOrder.CREATED;
import static java.time.Month.JANUARY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateOrderImplTest {
    @InjectMocks
    CreateOrderImpl createOrder;
    @Mock
    OrderRepository repository;
    @Mock
    IdGenerator idGenerator;

    @Test
    void create_ValidOrder_ShouldPass() {
        UUID orderId = UUID.fromString("dd9c24cc-b336-4f25-95de-bfd2ce7520fc");
        UUID clientId = UUID.fromString("89fc02d7-af79-473a-a792-ce4d6c188527");
        Order createdOrder = Order.builder().id(orderId).clientId(clientId).issuedDate(LocalDate.of(2023, JANUARY, 19)).status(CREATED).build();
        when(idGenerator.generate()).thenReturn(orderId);
        when(repository.create(createdOrder)).thenReturn(createdOrder);

        Order created = createOrder.execute(createdOrder);

        assertNotNull(created);
        assertEquals(orderId, created.getId());
        assertEquals(clientId, created.getClientId());
        assertEquals(CREATED, created.getStatus());
        verify(repository).create(createdOrder);
    }
}