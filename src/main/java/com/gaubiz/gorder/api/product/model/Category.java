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
public class Category {
    @Min(
            value = 0,
            message = "{message.009}",
            groups = Groups.modifyCategoryGroup.class
    )
    private int categoryNo;

    @NotBlank(
            message = "{message.001}",
            groups = {
                    Groups.addCategoryGroup.class,
                    Groups.modifyCategoryGroup.class
            }
    )
    private String categoryName;

    @NotBlank(
            message = "{message.011}",
            groups = Groups.addCategoryGroup.class
    )
    private String accountSerial;

    public Category(int categoryNo, String accountSerial) {
        this.categoryNo = categoryNo;
        this.accountSerial = accountSerial;
    }
}
