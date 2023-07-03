package com.gaubiz.gorder.api.product.model;

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
public class Product {
    @ApiModelProperty(value = "상품 번호",example = "5")
    @Min(
            value = 1,
            message = "{message.002}",
            groups = {
                    Groups.productModifyGroup.class,
                    Groups.soldOutGroup.class
            })
    private Integer productNo;

    @ApiModelProperty(value = "상품 이름",example = "닭발")
    @NotBlank(
            message = "{message.001}",
            groups = {
                    Groups.addProductGroup.class,
                    Groups.productModifyGroup.class
            })
    private String productName;

    @ApiModelProperty(value = "상품 설명",example = "아주 매운 닭발")
    @NotBlank(
            message = "{message.007",
            groups = {
                    Groups.addProductGroup.class,
                    Groups.productModifyGroup.class
            })
    private String productDetail;

    @ApiModelProperty(value = "상품 가격",example = "18000")
    @Min(
            value = 0,
            message = "{message.009}",
            groups = {
                    Groups.addProductGroup.class,
                    Groups.productModifyGroup.class
            })
    private int productPrice;

    @ApiModelProperty(value = "상품 활성화 여부",example = "1")
    private int productActive;

    @ApiModelProperty(value = "카테고리 번호",example = "4")
    @Min(
            value = 1,
            message = "{message.009}",
            groups = {
                    Groups.productModifyGroup.class,
                    Groups.addProductGroup.class
            }
    )
    private Integer categoryNo;
}
