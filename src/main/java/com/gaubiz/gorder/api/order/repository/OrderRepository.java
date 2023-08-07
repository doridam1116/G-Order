package com.gaubiz.gorder.api.order.repository;

import com.gaubiz.gorder.api.order.model.Order;
import com.gaubiz.gorder.api.order.model.OrderDetail;
import com.gaubiz.gorder.api.order.model.OrderList;

import java.util.List;

public interface OrderRepository {
    int createNewOrder(Order order);

    Long createPayment();

    Long getOrderNoByOrder(Order order);

    int createOrderDetail(Order order);

    int modifyOrder(OrderDetail orderDetail);

    int deleteOrderByNo(long orderNo);

    List<OrderDetail> getOrderListByNo(long orderNo);

    List<Order> getSalesOrder(String accountSerial);

    List<OrderList> getOrderListBySubSerial(String subSerial);

    Order selectOrderBySubSerial(String subSerial);

    int updateOrderDetail(OrderDetail order);

    void insertAddOrder(OrderDetail orderDetails);

    List<Order> getOrderListByAccountSerial(String accountSerial);

    List<OrderDetail> selectOrderByOrderNo(Long orderNo);
}
