package com.example.consumer2.service;

import com.example.consumer2.entity.Order;
import com.example.consumer2.repository.OrderRepository;
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
        if (order != null) {

            Order fOrder = new Order() ;
            fOrder.setQuantity(order.getQuantity());
            fOrder.setProductName(order.getProductName());
            orderRepository.save(fOrder);


            System.out.println( "oreder saved "+fOrder );

        }

    }


    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
