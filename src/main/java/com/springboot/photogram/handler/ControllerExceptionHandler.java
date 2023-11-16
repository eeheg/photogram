package com.springboot.photogram.handler;

import com.springboot.photogram.handler.ex.CustomValidationException;
import com.springboot.photogram.web.dto.CMResponseDTO;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@ControllerAdvice
public class ControllerExceptionHandler {

      /*
            CRMesponseDTO, Script 비교
            1. 클라이언트에게 응답할 때는 Script가 좋음
            //return Script.back(e.getErrorMap().toString());
            2. Ajax 통신 - CMResponseDTO
            3. Android 통신 - CMResponseDTO
      */

    @ExceptionHandler(CustomValidationException.class) //발생하는 RuntimeException을 모두 이 함수가 가로챔
    public CMResponseDTO<Map<String, String>> validationException(CustomValidationException validationException) {
        //@RestController : json 으로 리턴
        return new CMResponseDTO (-1, validationException.getMessage(), validationException.getErrorMap());

        /* 응답값
        {
            "code": -1,
            "errorMessage": "유효성 검사 실패함",
            "errorMap": {
                "name": "공백일 수 없습니다",
                "email": "공백일 수 없습니다",
                "username": "크기가 1에서 20 사이여야 합니다"
            }
        }
         */

    }
}
