package com.gaubiz.gorder.api.order.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Sales {
    private String productName;
    private int salesCount;
    private int salesPrice;
}
