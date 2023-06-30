package com.gaubiz.gorder.api.order.service.logic;

import com.gaubiz.gorder.api.order.model.Order;
import com.gaubiz.gorder.api.order.model.OrderDetail;
import com.gaubiz.gorder.api.order.model.Sales;
import com.gaubiz.gorder.api.order.repository.OrderRepository;
import com.gaubiz.gorder.api.order.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

import static com.gaubiz.gorder.config.PropertyConfig.getMessageSource;

@Service
public class OrderServiceLogic implements OrderService {
    private final OrderRepository orderRepository;

    public OrderServiceLogic(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public ResponseEntity<?> createNewOrder(Order order) {
        int result = orderRepository.createNewOrder(order);
        if (result > 0) {
            Long orderNo = orderRepository.getOrderNoByOrder(order);
            order.setOrderNo(orderNo);
            result = orderRepository.createOrderDetail(order);
            if (result > 0) {
                return ResponseEntity.ok().body(getMessageSource().getMessage("HTTP_OK", null, Locale.getDefault()));
            } else {
                return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(getMessageSource().getMessage("HTTP_SERVER_ERROR", null, Locale.getDefault()));
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(getMessageSource().getMessage("HTTP_SERVER_ERROR", null, Locale.getDefault()));
        }
    }

    @Override
    public ResponseEntity<?> modifyOrder(OrderDetail orderDetail) {
        int result = orderRepository.modifyOrder(orderDetail);
        if (result > 0) {
            return ResponseEntity.ok().body(getMessageSource().getMessage("HTTP_OK", null, Locale.getDefault()));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(getMessageSource().getMessage("HTTP_SERVER_ERROR", null, Locale.getDefault()));
        }
    }

    @Override
    public ResponseEntity<?> deleteOrderByNo(long orderNo) {
        int result = orderRepository.deleteOrderByNo(orderNo);
        if (result > 0) {
            return ResponseEntity.ok().body(getMessageSource().getMessage("HTTP_OK", null, Locale.getDefault()));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(getMessageSource().getMessage("HTTP_SERVER_ERROR", null, Locale.getDefault()));
        }
    }

    @Override
    public ResponseEntity<?> getOrderByNo(long orderNo) {
        List<OrderDetail> orderList = orderRepository.getOrderListByNo(orderNo);
        if (!orderList.isEmpty()) {
            return ResponseEntity.ok().body(orderList);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(getMessageSource().getMessage("HTTP_NOT_FOUND", null, Locale.getDefault()));
        }
    }

    @Override
    public ResponseEntity<?> getSalesOrder() {
        List<Sales> salesList = orderRepository.getSalesOrder();
        if (!salesList.isEmpty()) {
            return ResponseEntity.ok().body(salesList);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(getMessageSource().getMessage("HTTP_NOT_FOUND",null, Locale.getDefault()));
        }
    }
}



