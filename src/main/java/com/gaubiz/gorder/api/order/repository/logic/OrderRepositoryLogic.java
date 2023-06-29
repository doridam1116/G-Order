package com.gaubiz.gorder.api.order.repository.logic;

import com.gaubiz.gorder.api.order.model.Order;
import com.gaubiz.gorder.api.order.model.OrderDetail;
import com.gaubiz.gorder.api.order.model.Sales;
import com.gaubiz.gorder.api.order.repository.OrderRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

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



    @Override
    public Long createPayment() {
        session.insert("OrderMapper.insertPaymentTbl");
        return session.selectOne("OrderMapper.selectPaymentNo");
    }

    @Override
    public Long getOrderNoByOrder(Order order) {
        return session.selectOne("OrderMapper.selectOrderNoByOrder",order);
    }

    @Override
    public int createOrderDetail(Order order) {
        return session.insert("OrderMapper.insertOrderDetails",order);
    }

    @Override
    public int modifyOrder(OrderDetail orderDetail) {
        return session.update("OrderMapper.updateOrderDetail",orderDetail);
    }

    @Override
    public int deleteOrderByNo(long orderNo) {
        return session.delete("OrderMapper.deleteOrderByNo",orderNo);
    }

    @Override
    public List<OrderDetail> getOrderListByNo(long orderNo) {
        return session.selectList("OrderMapper.getOrderListByNo", orderNo);
    }

    @Override
    public List<Sales> getSalesOrder() {
        return session.selectList("OrderMapper.getSalesOrder");
    }

}
