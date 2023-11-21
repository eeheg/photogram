package com.springboot.photogram.handler.ex;

import java.util.Map;

public class CustomApiException extends RuntimeException{
    private static final long serialVersionUID=1L;  //객체를 구분할 때 (하나도 안 중요ㅋㅋ)

    public CustomApiException(String message) {
        super(message);
    }

}
