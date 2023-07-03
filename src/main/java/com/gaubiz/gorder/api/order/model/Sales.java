package com.gaubiz.gorder.api.order.model;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Sales {
    @ApiModelProperty(value = "상품 이름", example = "닭발")
    private String productName;

    @ApiModelProperty(value = "판매량", example = "3")
    private int salesCount;

    @ApiModelProperty(value = "매출", example = "56000")
    private int salesPrice;
}
