package com.example.consumer3.listener;

import com.example.consumer3.entity.Order;
import com.example.consumer3.repository.OrderRepository;
import com.example.consumer3.service.OrderService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderListener {

    @Autowired
    private OrderService orderService;

    @RabbitListener(queues = "myQueue")
    public void receiveOrder(Order order) {
        order.setVersion(3); // Initialize version
        orderService.saveOrder(order);
    }
}
