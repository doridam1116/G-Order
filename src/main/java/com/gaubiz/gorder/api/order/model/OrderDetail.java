package com.gaubiz.gorder.api.order.model;

import com.gaubiz.gorder.api.validation.Groups;
import io.swagger.annotations.ApiModelProperty;
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

    @ApiModelProperty(value = "주문 번호", example = "2023062914083302")
    @Min(
            value = 0,
            message = "{message.009}",
            groups = Groups.modifyOrderGroup.class
    )
    private long orderNo;

    @ApiModelProperty(value = "상품 번호", example = "5")
    @Min(
            value = 0,
            message = "{message.009}",
            groups = Groups.modifyOrderGroup.class
    )
    private int productNo;

    @ApiModelProperty(value = "상품 이름", example = "닭발")
    private String productName;

    @ApiModelProperty(value = "상품 가격", example = "18000")
    private int productPrice;

    @ApiModelProperty(value = "총 판매액", example = "56000")
    private int totalPrice;

    @ApiModelProperty(value = "주문 량", example = "3")
    @Min(
            value = 0,
            message = "{message.009}",
            groups = Groups.modifyOrderGroup.class
    )
    private int orderDetailCount;

    @ApiModelProperty(value = "주문 상태", example = "주문 접수")
    @NotBlank(
            message = "{message.012}",
            groups = Groups.modifyOrderGroup.class
    )
    private String orderStatus;
}
