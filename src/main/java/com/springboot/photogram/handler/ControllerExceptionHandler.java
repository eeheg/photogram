package com.springboot.photogram.handler;

import com.springboot.photogram.handler.ex.CustomApiException;
import com.springboot.photogram.handler.ex.CustomValidationApiException;
import com.springboot.photogram.handler.ex.CustomValidationException;
import com.springboot.photogram.util.Script;
import com.springboot.photogram.web.dto.CMResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController //@RestController : json 으로 리턴
@ControllerAdvice
public class ControllerExceptionHandler {
      /*  CRMesponseDTO, Script 비교
            1. 클라이언트에게 응답 - Script
            2. Ajax 통신 - CMResponseDTO
            3. Android 통신 - CMResponseDTO
      */

    @ExceptionHandler(CustomValidationException.class)  //발생하는 CustomValidationException 모두 이 함수가 가로챔
    public String validationException(CustomValidationException e) {
        // 클라이언트에게 응답 - Script (자바스크립트를 리턴)
        if(e.getErrorMap() == null) {
            return Script.back(e.getMessage());
        } else {
            return Script.back(e.getErrorMap().toString());
        }
    }

    @ExceptionHandler(CustomValidationApiException.class)
    public ResponseEntity<CMResponseDTO<Map<String, String>>> validationApiException(CustomValidationApiException e) {

        // Ajax 통신 - CMResponseDTO (데이터를 리턴)
        CMResponseDTO cmResponseDTO = new CMResponseDTO (-1, e.getMessage(), e.getErrorMap());
        // CMResponseDTO(code=-1, errorMessage=유효성 검사 실패함, errorMap={name=공백일 수 없습니다})
        ResponseEntity responseEntity = new ResponseEntity<>(cmResponseDTO, HttpStatus.BAD_REQUEST);
        System.out.println(responseEntity);
        //  <400 BAD_REQUEST Bad Request,CMResponseDTO(code=-1, errorMessage=유효성 검사 실패함, errorMap={name=공백일 수 없습니다}),[]>
        return responseEntity;

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

    @ExceptionHandler(CustomApiException.class)  //발생하는 RuntimeException을 모두 이 함수가 가로챔
    public ResponseEntity<CMResponseDTO<Map<String, String>>> apiException(CustomApiException e) {
        // Ajax 통신 - CMResponseDTO (데이터를 리턴)
        CMResponseDTO cmResponseDTO = new CMResponseDTO (-1, e.getMessage(), null);
        // CMResponseDTO(code=-1, errorMessage=유효성 검사 실패함, errorMap={name=공백일 수 없습니다})
        ResponseEntity responseEntity = new ResponseEntity<>(cmResponseDTO, HttpStatus.BAD_REQUEST);
        System.out.println(responseEntity);
        //  <400 BAD_REQUEST Bad Request,CMResponseDTO(code=-1, errorMessage=유효성 검사 실패함, errorMap=null),[]>
        return responseEntity;
    }
}
