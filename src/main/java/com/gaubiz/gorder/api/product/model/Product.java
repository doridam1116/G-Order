package com.gaubiz.gorder.api.product.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private int productNo;
    private String productName;
    private String productDetail;
    private int productPrice;
    private int productActive;
    private int categoryNo;
}
