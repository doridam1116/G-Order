package com.gaubiz.gorder.api.order.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private int orderNo;
    private String subSerial;
    private Date orderDate;
    private int orderPaymentNo;
}
