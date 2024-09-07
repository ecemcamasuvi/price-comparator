package com.xprice.price_comparator.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResponseDto<T> {
    private String resultCode;
    private String errorMessage;
    private T data;

    public ResponseDto(T data) {
        this.data = data;
        this.resultCode = "success";
        this.errorMessage = null;
    }

    public ResponseDto(String resultCode, String errorMessage) {
        this.resultCode = resultCode;
        this.errorMessage = errorMessage;
        this.data = null;
    }
}
