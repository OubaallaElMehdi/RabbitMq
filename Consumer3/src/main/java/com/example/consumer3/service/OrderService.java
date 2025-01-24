package com.example.consumer3.service;

import com.example.consumer3.entity.Order;
import com.example.consumer3.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Transactional
    public void saveOrder(Order order) {
        int maxRetries = 3;
        int attempt = 0;

        while (attempt < maxRetries) {
            try {
                orderRepository.save(order);
                break; // Exit loop if successful
            } catch (ObjectOptimisticLockingFailureException e) {
                attempt++;
                if (attempt >= maxRetries) {
                    throw e; // Rethrow exception if max retries are reached
                }
            }
        }
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
