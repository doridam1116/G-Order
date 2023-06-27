package com.gaubiz.gorder.api.product.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    private int categoryNo;
    private String categoryName;
    private String accountSerial;

    public Category(int categoryNo, String accountSerial) {
        this.categoryNo = categoryNo;
        this.accountSerial = accountSerial;
    }
}
