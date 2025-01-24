package com.example.producer.controller;

import com.example.producer.entity.Order;
import com.example.producer.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        // This will save the order and send a message to RabbitMQ
        return orderService.saveOrderAndSend(order);
    }

    // Optional: retrieve all orders in the producer DB
    // (just to confirm something got saved locally as well)
    @GetMapping
    public java.util.List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }
}
