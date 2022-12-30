package com.schambeck.erp.sales.core.usecase.interactor.impl;

import com.schambeck.erp.sales.core.dataprovider.OrderNotifier;
import com.schambeck.erp.sales.core.dataprovider.OrderRepository;
import com.schambeck.erp.sales.core.entity.Order;
import com.schambeck.erp.sales.core.entity.OrderLine;
import com.schambeck.erp.sales.core.usecase.exception.BusinessException;
import com.schambeck.erp.sales.core.usecase.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static com.schambeck.erp.sales.core.entity.vo.StatusOrder.CLOSED;
import static com.schambeck.erp.sales.core.entity.vo.StatusOrder.CREATED;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CloseOrderImplTest {
    @InjectMocks
    CloseOrderImpl closeOrder;
    @Mock
    OrderRepository repository;
    @Mock
    OrderNotifier notifier;
    @Test
    void close_ValidId_ShouldPass() {
        UUID orderId = UUID.fromString("dd9c24cc-b336-4f25-95de-bfd2ce7520fc");
        UUID clientId = UUID.fromString("89fc02d7-af79-473a-a792-ce4d6c188527");
        Order createdOrder = Order.builder()
                .id(orderId)
                .clientId(clientId)
                .status(CREATED)
                .item(OrderLine.builder()
                        .productId(UUID.fromString("c90e4992-3e48-43ca-9da3-7ca012f44236"))
                        .quantity(new BigDecimal("3.00"))
                        .price(new BigDecimal("1.50"))
                        .build())
                .build();
        when(repository.findById(orderId)).thenReturn(Optional.of(createdOrder));
        doNothing().when(repository).updateStatus(orderId, CLOSED);
        doNothing().when(notifier).sendMessage(createdOrder);

        closeOrder.execute(orderId);

        verify(repository).findById(orderId);
        verify(repository).updateStatus(orderId, CLOSED);
        verify(notifier).sendMessage(createdOrder);
    }

    @Test
    void close_InvalidId_ShouldFail() {
        UUID orderId = UUID.fromString("dd9c24cc-b336-4f25-95de-bfd2ce7520fc");
        when(repository.findById(orderId)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> closeOrder.execute(orderId));

        assertEquals("Entity %s not found".formatted(orderId), exception.getMessage());
        verify(repository).findById(orderId);
        verifyNoMoreInteractions(repository);
        verifyNoMoreInteractions(notifier);
    }

    @Test
    void close_ClosedStatus_ShouldFail() {
        UUID orderId = UUID.fromString("dd9c24cc-b336-4f25-95de-bfd2ce7520fc");
        UUID clientId = UUID.fromString("89fc02d7-af79-473a-a792-ce4d6c188527");
        Order closedOrder = Order.builder().id(orderId).clientId(clientId).status(CLOSED).build();
        when(repository.findById(orderId)).thenReturn(Optional.of(closedOrder));

        BusinessException exception = assertThrows(BusinessException.class, () -> closeOrder.execute(orderId));

        assertEquals("Order already closed: %s".formatted(orderId), exception.getMessage());
        verify(repository).findById(orderId);
        verifyNoMoreInteractions(repository);
        verifyNoMoreInteractions(notifier);
    }

    @Test
    void close_NoItems_ShouldFail() {
        UUID orderId = UUID.fromString("dd9c24cc-b336-4f25-95de-bfd2ce7520fc");
        UUID clientId = UUID.fromString("89fc02d7-af79-473a-a792-ce4d6c188527");
        Order createdOrder = Order.builder().id(orderId).clientId(clientId).status(CREATED).build();
        when(repository.findById(orderId)).thenReturn(Optional.of(createdOrder));

        BusinessException exception = assertThrows(BusinessException.class, () -> closeOrder.execute(orderId));

        assertEquals("Order has no items: %s".formatted(orderId), exception.getMessage());
        verify(repository).findById(orderId);
        verifyNoMoreInteractions(repository);
        verifyNoMoreInteractions(notifier);
    }
}