package com.gaubiz.gorder.api.order.service;

import com.gaubiz.gorder.api.order.model.Order;
import org.springframework.http.ResponseEntity;

public interface OrderService {
    ResponseEntity<?> createNewOrder(Order order);
}
