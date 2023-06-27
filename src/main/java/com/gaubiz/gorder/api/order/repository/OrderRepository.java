package com.gaubiz.gorder.api.order.repository;

import com.gaubiz.gorder.api.order.model.Order;

public interface OrderRepository {
    int createNewOrder(Order order);
}
