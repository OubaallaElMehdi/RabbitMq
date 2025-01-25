package com.example.consumer1.listener;

import com.example.consumer1.config.RabbitMQConfig;
import com.example.consumer1.entity.Order;
import com.example.consumer1.repository.OrderRepository;
import com.example.consumer1.service.OrderService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderListener {

    @Autowired
    private OrderService orderService;

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void receiveOrder(Order order) {
//        order.setVersion(1); // Initialize version
        orderService.saveOrder(order);
        System.out.println(order);

    }
}
