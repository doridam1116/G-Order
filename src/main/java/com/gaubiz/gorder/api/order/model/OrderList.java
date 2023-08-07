package com.gaubiz.gorder.api.order.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderList {
    private int productNo;
    private String productName;
    private int productPrice;
    private int orderDetailCount;
    private int price;
}
