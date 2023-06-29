package com.gaubiz.gorder.api.order.model;

import com.gaubiz.gorder.api.validation.Groups;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetail {
    @Min(
            value = 0,
            message = "{message.009}",
            groups = Groups.modifyOrderGroup.class
    )
    private long orderNo;

    @Min(
            value = 0,
            message = "{message.009}",
            groups = Groups.modifyOrderGroup.class
    )
    private int productNo;

    private String productName;

    private int productPrice;

    private int totalPrice;

    @Min(
            value = 0,
            message = "{message.009}",
            groups = Groups.modifyOrderGroup.class
    )
    private int orderDetailCount;

    @NotBlank(
            message = "{message.012}",
            groups = Groups.modifyOrderGroup.class
    )
    private String orderStatus;
}
