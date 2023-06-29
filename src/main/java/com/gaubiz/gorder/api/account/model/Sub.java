package com.gaubiz.gorder.api.account.model;

import com.gaubiz.gorder.api.validation.Groups;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Sub {
    @NotBlank(
            message = "{message.010}",
            groups = Groups.subActiveGroup.class
    )
    private String subSerial;

    @Range(
            min = 0,
            max = 1,
            message = "{message.013}",
            groups = Groups.subActiveGroup.class
    )
    private int subActive;

    @NotBlank(
            groups = Groups.subAddGroup.class,
            message = "{message.010}")
    private String accountSerial;

    @NotBlank(
            groups = Groups.subAddGroup.class,
            message = "{message.011}")
    private String tableNo;


    private int accountTel;

    private String accountName;
}
