package com.gaubiz.gorder.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDetail {
    private int code;
    private String title;
    private String error;
    private String msg;
}
