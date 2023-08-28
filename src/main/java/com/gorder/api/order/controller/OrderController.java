package com.gorder.api.order.controller;

import com.gorder.api.order.model.Order;
import com.gorder.api.order.model.OrderDetail;
import com.gorder.api.order.service.OrderService;
import com.gorder.api.validation.Groups;
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
//            @Validated(Groups.newOrderGroup.class)
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

    @ApiOperation(value = "모든 주문 조회")
    @GetMapping("/all")
    public ResponseEntity<?> getOrderByAll(
            @ApiParam(value = "계정 시리얼")
            @RequestParam String accountSerial
    ){
        return orderService.getOrderByAccountSerial(accountSerial);
    }

    @ApiOperation(value = "주문 조회/erp")
    @GetMapping("/getorder")
    public ResponseEntity<?> getOrderByOrderNo(
            @RequestParam Long orderNo
    ){
        return orderService.getOrderByOrderNo(orderNo);
    }

    /*
        param :
        long orderNo
     */
    @ApiOperation(value = "주문 조회")
    @GetMapping
    public ResponseEntity<?> getOrderBySubSerial(
            @ApiParam(value = "주문 번호")
            @RequestParam String subSerial
    ){
        return orderService.getOrderBySubSerial(subSerial);
    }


    /*
        param :
     */
    @ApiOperation(value = "당일 매출 조회")
    @GetMapping("/sales")
    public ResponseEntity<?> getSalesOrder(
            @RequestParam String accountSerial
    ){
        return orderService.getSalesOrder(accountSerial);
    }

}
