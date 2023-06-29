package com.gaubiz.gorder.api.order.controller;

import com.gaubiz.gorder.api.order.model.Order;
import com.gaubiz.gorder.api.order.model.OrderDetail;
import com.gaubiz.gorder.api.order.service.OrderService;
import com.gaubiz.gorder.api.validation.Groups;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /*
        param :
        String subSerial
        List<orderDetailList>
            - int productNo
            - int orderDetailCount
     */
    @PostMapping("/new")
    public ResponseEntity<?> newOrder(
            @Validated(Groups.newOrderGroup.class)
            @RequestBody Order order
    ){
        return orderService.createNewOrder(order);
    }

    /*
        param :
        long orderNo,
        int productNo,
        int orderCount,
        String orderStatus
     */
    @PatchMapping("/modify")
    public ResponseEntity<?> modifyOrder(
            @Validated(Groups.modifyOrderGroup.class)
            @RequestBody OrderDetail orderDetail
            ){
        return orderService.modifyOrder(orderDetail);
    }


    /*
        param :
        long orderNo
     */
    @DeleteMapping("/delete/{orderNo}")
    public ResponseEntity<?> deleteOrder(
            @PathVariable long orderNo
    ){
        return orderService.deleteOrderByNo(orderNo);
    }


    /*
        param :
        long orderNo
     */
    @GetMapping
    public ResponseEntity<?> getOrderByNo(
            @RequestParam long orderNo
    ){
        return orderService.getOrderByNo(orderNo);
    }


    /*
        param :
     */
    @GetMapping("/sales")
    public ResponseEntity<?> getSalesOrder(){
        return orderService.getSalesOrder();
    }

}
