package com.schambeck.erp.sales.app.dataprovider;

import com.schambeck.erp.sales.app.dataprovider.mapper.OrderMsgMapper;
import com.schambeck.erp.sales.app.dataprovider.model.OrderMsg;
import com.schambeck.erp.sales.core.dataprovider.OrderNotifier;
import com.schambeck.erp.sales.core.entity.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
class RabbitOrderProducer implements OrderNotifier {
    private final RabbitTemplate template;
    @Value("${order-exchange}")
    private String orderExchange;
    @Value("${order-routing-key}")
    private String orderRoutingKey;

    @Transactional
    public void sendMessage(Order order) {
        OrderMsg msg = OrderMsgMapper.INSTANCE.toMsg(order);
        log.info("send: {}", msg);
        template.convertAndSend(orderExchange, orderRoutingKey, msg);
    }
}
