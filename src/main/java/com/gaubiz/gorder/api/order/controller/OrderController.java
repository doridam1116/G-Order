package com.gaubiz.gorder.api.order.controller;

import com.gaubiz.gorder.api.order.model.Order;
import com.gaubiz.gorder.api.order.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/new")
    public ResponseEntity<?> newOrder(
            @RequestBody Order order
    ){
        return orderService.createNewOrder(order);
    }

}
