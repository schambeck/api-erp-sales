package com.schambeck.erp.sales.core.usecase.interactor.impl;

import com.schambeck.erp.sales.core.dataprovider.OrderRepository;
import com.schambeck.erp.sales.core.entity.Order;
import com.schambeck.erp.sales.core.usecase.exception.NotFoundException;
import com.schambeck.erp.sales.core.usecase.interactor.impl.FindOrderImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.schambeck.erp.sales.core.entity.vo.StatusOrder.CREATED;
import static java.time.Month.JANUARY;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindOrderImplTest {
    @InjectMocks
    FindOrderImpl findOrder;
    @Mock
    OrderRepository repository;
    @Test
    void findBy_ValidId_ShouldPass() {
        UUID orderId = UUID.fromString("dd9c24cc-b336-4f25-95de-bfd2ce7520fc");
        UUID clientId = UUID.fromString("89fc02d7-af79-473a-a792-ce4d6c188527");
        Order foundOrder = Order.builder().id(orderId).clientId(clientId).issuedDate(LocalDate.of(2023, JANUARY, 19)).status(CREATED).build();
        when(repository.findById(orderId)).thenReturn(Optional.of(foundOrder));

        Order found = findOrder.findById(orderId);

        assertNotNull(found);
        assertEquals(orderId, found.getId());
        assertEquals(clientId, found.getClientId());
        assertEquals(CREATED, found.getStatus());
        verify(repository).findById(orderId);
    }
    @Test
    void findBy_InvalidId_ShouldFail() {
        UUID orderId = UUID.fromString("dd9c24cc-b336-4f25-95de-bfd2ce7520fc");
        when(repository.findById(orderId)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> findOrder.findById(orderId));

        assertEquals("Entity %s not found".formatted(orderId), exception.getMessage());
        verify(repository).findById(orderId);
    }
    @Test
    void findAll_ValidOrders_ShouldPass() {
        UUID orderId = UUID.fromString("dd9c24cc-b336-4f25-95de-bfd2ce7520fc");
        UUID clientId = UUID.fromString("89fc02d7-af79-473a-a792-ce4d6c188527");
        Order foundOrder = Order.builder().id(orderId).clientId(clientId).issuedDate(LocalDate.of(2023, JANUARY, 19)).status(CREATED).build();
        when(repository.findAll()).thenReturn(List.of(foundOrder));

        List<Order> ordersFound = findOrder.findAll();

        assertNotNull(ordersFound);
        assertEquals(1, ordersFound.size());
        Order found = ordersFound.get(0);
        assertEquals(orderId, found.getId());
        assertEquals(clientId, found.getClientId());
        assertEquals(CREATED, found.getStatus());
        verify(repository).findAll();
    }
}