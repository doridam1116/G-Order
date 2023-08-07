package com.gaubiz.gorder.api.order.service.logic;

import com.gaubiz.gorder.api.order.model.Order;
import com.gaubiz.gorder.api.order.model.OrderDetail;
import com.gaubiz.gorder.api.order.model.OrderList;
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

        Order order2 = orderRepository.selectOrderBySubSerial(order.getSubSerial());

        if (order2 != null && order2.getOrderPaymentNo() == null && order2.getOrderNo() != null) {
            for (OrderDetail orderDetails : order.getOrderDetailList()) {
                orderDetails.setOrderNo(order2.getOrderNo());
                int result = orderRepository.updateOrderDetail(orderDetails);
                if (result <= 0) orderRepository.insertAddOrder(orderDetails);
            }
            return ResponseEntity.ok().body(getMessageSource().getMessage("HTTP_OK", null, Locale.getDefault()));
        } else {

            int result = orderRepository.createNewOrder(order);
            if (result > 0) {
                Order orderNo2 = orderRepository.selectOrderBySubSerial(order.getSubSerial());
                order.setOrderNo(orderNo2.getOrderNo());
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
    public ResponseEntity<?> getSalesOrder(String accountSerial) {
        List<Order> salesList = orderRepository.getSalesOrder(accountSerial);
        if (!salesList.isEmpty()) {
            return ResponseEntity.ok().body(salesList);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(getMessageSource().getMessage("HTTP_NOT_FOUND", null, Locale.getDefault()));
        }
    }

    @Override
    public ResponseEntity<?> getOrderBySubSerial(String subSerial) {
        List<OrderList> orderLists = orderRepository.getOrderListBySubSerial(subSerial);
        if (!orderLists.isEmpty()) {
            return ResponseEntity.ok().body(orderLists);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(getMessageSource().getMessage("HTTP_SERVER_ERROR", null, Locale.getDefault()));
        }
    }

    @Override
    public ResponseEntity<?> getOrderByAccountSerial(String accountSerial) {
        List<Order> orderList = orderRepository.getOrderListByAccountSerial(accountSerial);
//        if(orderList.isEmpty()){
//            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(getMessageSource().getMessage("HTTP_SERVER_ERROR",null,Locale.getDefault()));
//        }
        return ResponseEntity.ok().body(orderList);
    }

    @Override
    public ResponseEntity<?> getOrderByOrderNo(Long orderNo) {
        List<OrderDetail> orderDetailList = orderRepository.selectOrderByOrderNo(orderNo);
//        if(orderDetailList.isEmpty()){
//            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(getMessageSource().getMessage("HTTP_SERVER_ERROR",null,Locale.getDefault()));
//        }
        return ResponseEntity.ok().body(orderDetailList);
    }
}



