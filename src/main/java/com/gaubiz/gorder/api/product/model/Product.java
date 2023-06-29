package com.gaubiz.gorder.api.product.model;

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
public class Product {

    @Min(
            value = 1,
            message = "{message.002}",
            groups = {
                    Groups.productModifyGroup.class,
                    Groups.soldOutGroup.class
            })
    private Integer productNo;

    @NotBlank(
            message = "{message.001}",
            groups = {
                    Groups.addProductGroup.class,
                    Groups.productModifyGroup.class
            })
    private String productName;

    @NotBlank(
            message = "{message.007",
            groups = {
                    Groups.addProductGroup.class,
                    Groups.productModifyGroup.class
            })
    private String productDetail;

    @Min(
            value = 0,
            message = "{message.009}",
            groups = {
                    Groups.addProductGroup.class,
                    Groups.productModifyGroup.class
            })
    private int productPrice;

    private int productActive;

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
