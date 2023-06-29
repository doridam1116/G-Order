package com.gaubiz.gorder.api.order.model;

import com.gaubiz.gorder.api.validation.Groups;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Min(
            value = 0,
            message = "{message.009}"
    )
    private long orderNo;

    @NotBlank(
            message = "{message.010}",
            groups = Groups.newOrderGroup.class
    )
    private String subSerial;


    private Date orderDate;

    @Min(
            value = 0,
            message = "{message.009}"
    )
    private int orderPaymentNo;


    private List<OrderDetail> orderDetailList;
}
