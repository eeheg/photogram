package com.springboot.photogram.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class CMResponseDTO<T> {
    private int code;  // 1(성공), -1(실패)
    private String errorMessage;
    private T errorMap;
}
