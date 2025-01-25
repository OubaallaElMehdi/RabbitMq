package com.example.consumer2.listener;

import com.example.consumer2.entity.Order;
import com.example.consumer2.service.OrderService;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
public class OrderListener {

    @Autowired
    private OrderService orderService;

    @RabbitListener(queues = "myQueue")
    public void receiveOrder(Order order) {
        order.setVersion(2); // Initialize version
        orderService.saveOrder(order);
    }
    @RabbitListener(queues = "myQueue")
    public void receiveOrder(Order order, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
        try {
            orderService.saveOrder(order);
            channel.basicAck(tag, false); // Acknowledge the message
        } catch (Exception e) {
            channel.basicNack(tag, false, true); // Requeue the message
            throw e; // Re-throw to allow retry
        }
    }
}
