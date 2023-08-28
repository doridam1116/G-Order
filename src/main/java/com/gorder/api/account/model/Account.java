package com.gorder.api.account.model;


import com.gorder.api.validation.Groups;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @ApiModelProperty(
            value = "계정 고유번호"
            , example = "G15881581"
    )
    @NotBlank(
            groups = {
                    Groups.loginGroup.class
            },
            message = "{message.003}"
    )
    private String accountSerial;

    @ApiModelProperty(
            value = "계정명"
            , example = "한신포차"
    )
    @NotBlank(
            groups = {
                    Groups.registerGroup.class
            },
            message = "{message.001}"
    )
    private String accountName;

    @ApiModelProperty(
            value = "계정 연락처"
            , example = "15881581"
    )
    @Min(
            value = 0,
            groups = {
                    Groups.registerGroup.class
            },
            message = "{message.015}"
    )
    private int accountTel;

    @ApiModelProperty(
            value = "계정 타입"
            , example = "MASTER"
    )
    @NotBlank(
            groups = {
                    Groups.loginGroup.class
            },
            message = "{message.004}"
    )
    private String accountType;

}
