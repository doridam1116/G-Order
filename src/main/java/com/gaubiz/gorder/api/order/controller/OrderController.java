package com.gaubiz.gorder.api.order.controller;

import com.gaubiz.gorder.api.order.model.Order;
import com.gaubiz.gorder.api.order.model.OrderDetail;
import com.gaubiz.gorder.api.order.service.OrderService;
import com.gaubiz.gorder.api.validation.Groups;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Api(tags = {"주문 API"})
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
    @ApiOperation(value = "주문 생성")
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
    @ApiOperation(value = "주문 수정")
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
    @ApiOperation(value = "주문 삭제")
    @DeleteMapping("/delete/{orderNo}")
    public ResponseEntity<?> deleteOrder(
            @ApiParam(value = "주문 번호")
            @PathVariable long orderNo
    ){
        return orderService.deleteOrderByNo(orderNo);
    }


    /*
        param :
        long orderNo
     */
    @ApiOperation(value = "주문 조회")
    @GetMapping
    public ResponseEntity<?> getOrderByNo(
            @ApiParam(value = "주문 번호")
            @RequestParam long orderNo
    ){
        return orderService.getOrderByNo(orderNo);
    }


    /*
        param :
     */
    @ApiOperation(value = "당일 매출 조회")
    @GetMapping("/sales")
    public ResponseEntity<?> getSalesOrder(){
        return orderService.getSalesOrder();
    }

}
