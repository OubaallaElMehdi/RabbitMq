package com.example.consumer2.listener;

import com.example.consumer2.entity.Order;
import com.example.consumer2.repository.OrderRepository;
import com.example.consumer2.service.OrderService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderListener {

    @Autowired
    private OrderService orderService;

    @RabbitListener(queues = "myQueue")
    public void receiveOrder(Order order) {
        System.out.println("Received order: " + order);
        if (order != null) {
            orderService.saveOrder(order);
        } else {
            System.err.println("Received null order!");
        }
    }
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }
    @Autowired
    private OrderRepository orderRepository;
}