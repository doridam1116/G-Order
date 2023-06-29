package com.gaubiz.gorder.api.order.repository;

import com.gaubiz.gorder.api.order.model.Order;
import com.gaubiz.gorder.api.order.model.OrderDetail;
import com.gaubiz.gorder.api.order.model.Sales;

import java.util.List;

public interface OrderRepository {
    int createNewOrder(Order order);

    Long createPayment();

    Long getOrderNoByOrder(Order order);

    int createOrderDetail(Order order);

    int modifyOrder(OrderDetail orderDetail);

    int deleteOrderByNo(long orderNo);

    List<OrderDetail> getOrderListByNo(long orderNo);

    List<Sales> getSalesOrder();
}
