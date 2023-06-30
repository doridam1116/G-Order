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
public class Category {
    @ApiModelProperty(value = "카테고리 번호", example = "1")
    @Min(
            value = 0,
            message = "{message.009}",
            groups = Groups.modifyCategoryGroup.class
    )
    private int categoryNo;

    @ApiModelProperty(value = "카테고리 이름",example = "메인 메뉴")
    @NotBlank(
            message = "{message.001}",
            groups = {
                    Groups.addCategoryGroup.class,
                    Groups.modifyCategoryGroup.class
            }
    )
    private String categoryName;

    @ApiModelProperty(value = "계정 고유번호",example = "G15881588")
    @NotBlank(
            message = "{message.011}",
            groups = Groups.addCategoryGroup.class
    )
    private String accountSerial;

}
