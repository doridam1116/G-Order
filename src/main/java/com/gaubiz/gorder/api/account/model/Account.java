package com.gaubiz.gorder.api.account.model;


import com.gaubiz.gorder.api.account.validation.Groups;
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
    @NotBlank(groups = {Groups.loginGroup.class}, message = "{message.003}")
    private String accountSerial;

    @NotBlank(groups = {Groups.registerGroup.class}, message = "{message.001}")
    private String accountName;

    @Min(value = 0, groups = {Groups.registerGroup.class}, message = "{message.002}")
    private Integer accountTel;

    @NotBlank(groups = {Groups.loginGroup.class, Groups.registerGroup.class}, message = "{message.004}")
    private String accountType;

}
