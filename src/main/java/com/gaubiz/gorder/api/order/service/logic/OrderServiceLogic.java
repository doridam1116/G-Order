package com.gaubiz.gorder.api.order.service.logic;

import com.gaubiz.gorder.api.order.model.Order;
import com.gaubiz.gorder.api.order.repository.OrderRepository;
import com.gaubiz.gorder.api.order.service.OrderService;
import com.gaubiz.gorder.msg.HttpStatusMsg;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceLogic implements OrderService {
    private final OrderRepository orderRepository;

    public OrderServiceLogic(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public ResponseEntity<?> createNewOrder(Order order) {
        int result = orderRepository.createNewOrder(order);
        if(result > 0){
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(HttpStatusMsg.HTTP_SERVER_ERROR);
        }
    }
}
