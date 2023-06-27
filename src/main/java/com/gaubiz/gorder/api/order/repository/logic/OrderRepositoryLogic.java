package com.gaubiz.gorder.api.order.repository.logic;

import com.gaubiz.gorder.api.order.model.Order;
import com.gaubiz.gorder.api.order.repository.OrderRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepositoryLogic implements OrderRepository {
    private final SqlSession session;

    public OrderRepositoryLogic(SqlSession session) {
        this.session = session;
    }

    @Override
    public int createNewOrder(Order order) {
        return session.insert("OrderMapper.insertNewOrder",order);
    }
}
