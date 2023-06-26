package com.gaubiz.gorder.common;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;

import java.util.ArrayList;
import java.util.List;

@Component
public class ErrorResponse {

    public List<ErrorDetail> errorMsg(BindException ex, HttpStatus httpStatus ){
        List<ErrorDetail> errorDetails = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(fieldError -> {
            ErrorDetail errorDetail = new ErrorDetail();
            errorDetail.setCode(httpStatus.value());
            errorDetail.setTitle(httpStatus.getReasonPhrase());
            errorDetail.setError(fieldError.getCode());
            errorDetail.setMsg(fieldError.getDefaultMessage());
            errorDetails.add(errorDetail);
        });
        return errorDetails;
    }
}
