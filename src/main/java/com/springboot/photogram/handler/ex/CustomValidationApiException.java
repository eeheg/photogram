package com.springboot.photogram.handler.ex;

import java.util.Map;

public class CustomValidationApiException extends RuntimeException{
    private static final long serialVersionUID=1L;  //객체를 구분할 때 (하나도 안 중요ㅋㅋ)

    private Map<String, String> errorMap;

    public CustomValidationApiException(String message, Map<String,String> errorMap) {
        super(message);
        this.errorMap = errorMap;
    }

    public CustomValidationApiException(String message) {
        super(message);
    }

    public Map<String,String> getErrorMap() {
        return errorMap;
    }
}
