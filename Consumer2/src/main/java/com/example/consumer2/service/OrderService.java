package com.example.consumer2.service;

import com.example.consumer2.entity.Order;
import com.example.consumer2.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Order saveOrder(Order order) {
        if (order.getId() != null && orderRepository.existsById(order.getId())) {
            Order existingOrder = orderRepository.findById(order.getId()).orElseThrow();
            existingOrder.setProductName(order.getProductName());
            existingOrder.setQuantity(order.getQuantity());
            return orderRepository.save(existingOrder);
        } else {
            return orderRepository.save(order);
        }
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
