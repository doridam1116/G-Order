package com.gaubiz.gorder.api.account.model;

import com.gaubiz.gorder.api.validation.Groups;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Sub {
    @ApiModelProperty(
            value = "서브 고유번호",
            example = "G15881581T14"
    )
    @NotBlank(
            message = "{message.010}",
            groups = {
                    Groups.subActiveGroup.class,
                    Groups.subModifyGroup.class
                    }
    )
    private String subSerial;

    @ApiModelProperty(
            value = "서브 활성화 여부",
            example = "1"
    )
    @Range(
            min = 0,
            max = 1,
            message = "{message.013}",
            groups = Groups.subActiveGroup.class
    )
    private int subActive;

    @ApiModelProperty(
            value = "계정 고유번호",
            example = "G15881581"
    )
    @NotBlank(
            groups = {
                    Groups.subAddGroup.class,
                    Groups.subModifyGroup.class
                    },
            message = "{message.010}")
    private String accountSerial;

    @NotBlank(
            groups = {
                    Groups.subModifyGroup.class
            },
            message = "{message.010}"
    )
    private String newSubSerial;

    @ApiModelProperty(
            value = "테이블 번호",
            example = "T14"
    )
    @NotBlank(
            groups = Groups.subAddGroup.class,
            message = "{message.011}"
    )
    @Size(
            groups = Groups.subAddGroup.class,
            min = 1, max = 10,
            message = "{message.014}"
    )
    private String tableNo;

    @ApiModelProperty(
            value = "연락처",
            example = "15881588"
    )
    private int accountTel;

    @ApiModelProperty(
            value = "계정명",
            example = "한신포차"
    )
    private String accountName;
}
