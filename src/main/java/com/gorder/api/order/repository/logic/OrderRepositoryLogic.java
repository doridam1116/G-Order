package com.gorder.api.order.repository.logic;

import com.gorder.api.order.model.Order;
import com.gorder.api.order.model.OrderDetail;
import com.gorder.api.order.model.OrderList;
import com.gorder.api.order.repository.OrderRepository;
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
    public List<Order> getSalesOrder(String accountSerial) {
        return session.selectList("OrderMapper.getSalesOrder",accountSerial);
    }

    @Override
    public List<OrderList> getOrderListBySubSerial(String subSerial) {
        return session.selectList("OrderMapper.getOrderBySubSerial",subSerial);
    }

    @Override
    public Order selectOrderBySubSerial(String subSerial) {
        
        return session.selectOne("OrderMapper.selectOrderNoByOrder", subSerial);
    }

    @Override
    public int updateOrderDetail(OrderDetail order) {
        return session.update("OrderMapper.updateOrderList",order);
    }

    @Override
    public void insertAddOrder(OrderDetail orderDetails) {
        session.insert("OrderMapper.insertAddOrder",orderDetails);
    }

    @Override
    public List<Order> getOrderListByAccountSerial(String accountSerial) {
        return session.selectList("OrderMapper.selectOrderListByAccountSerial",accountSerial);
    }

    @Override
    public List<OrderDetail> selectOrderByOrderNo(Long orderNo) {
        return session.selectList("OrderMapper.selectOrderByOrderNo",orderNo);
    }

}
