package com.gaubiz.gorder.api.order.model;

import com.gaubiz.gorder.api.validation.Groups;
import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty(value = "주문 번호",example = "2023062914083302")
    @Min(
            value = 0,
            message = "{message.009}"
    )
    private Long orderNo;

    @ApiModelProperty(value = "서브 고유번호",example = "G15881588T04")
    @NotBlank(
            message = "{message.010}",
            groups = Groups.newOrderGroup.class
    )
    private String subSerial;

    @ApiModelProperty(value = "주문 날짜",example = "23/05/30")
    private Date orderDate;

    @ApiModelProperty(value = "주문 결제 번호", example = "2023062814")
    @Min(
            value = 0,
            message = "{message.009}"
    )
    private Long orderPaymentNo;

     @ApiModelProperty(value = "주문 상품 리스트")
    private List<OrderDetail> orderDetailList;

     private int totalPrice;
}
