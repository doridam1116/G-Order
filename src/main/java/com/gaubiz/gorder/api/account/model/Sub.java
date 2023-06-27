package com.gaubiz.gorder.api.account.model;

import com.gaubiz.gorder.api.validation.Groups;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Sub {
    private String subSerial;
    private int subActive;

    @NotBlank(groups = Groups.subAddGroup.class, message = "")
    private String accountSerial;

    @NotBlank(groups = Groups.subAddGroup.class, message = "")
    private String tableNo;


}
