package com.gorder.api.order.service;

import com.gorder.api.order.model.Order;
import com.gorder.api.order.model.OrderDetail;
import org.springframework.http.ResponseEntity;

public interface OrderService {
    ResponseEntity<?> createNewOrder(Order order);

    ResponseEntity<?> modifyOrder(OrderDetail orderDetail);

    ResponseEntity<?> deleteOrderByNo(long orderNo);

    ResponseEntity<?> getOrderByNo(long orderNo);

    ResponseEntity<?> getSalesOrder(String accountSerial);

    ResponseEntity<?> getOrderBySubSerial(String subSerial);

    ResponseEntity<?> getOrderByAccountSerial(String accountSerial);

    ResponseEntity<?> getOrderByOrderNo(Long orderNo);
}
