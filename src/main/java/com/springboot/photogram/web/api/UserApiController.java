package com.springboot.photogram.web.api;

import com.springboot.photogram.config.auth.PrincipalDetails;
import com.springboot.photogram.domain.user.User;
import com.springboot.photogram.handler.ex.CustomValidationApiException;
import com.springboot.photogram.handler.ex.CustomValidationException;
import com.springboot.photogram.service.UserService;
import com.springboot.photogram.web.dto.CMResponseDTO;
import com.springboot.photogram.web.dto.user.UserUpdateDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final UserService userService;

    @PutMapping("/api/user/{id}")
    public CMResponseDTO<Map<String, String>> update(
            @PathVariable Long id,
            @Valid UserUpdateDTO userUpdateDTO,
            BindingResult bindingResult,   //BindingResult는 반드시 @Valid 객체의 다음 파라미터로 적어야 함
            @AuthenticationPrincipal PrincipalDetails principalDetails
    ) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            throw new CustomValidationApiException("유효성 검사 실패함", errorMap);
        } else {
            System.out.println(userUpdateDTO);
            User userEntity = userService.회원수정(id, userUpdateDTO.toEntity());
            principalDetails.setUser(userEntity);  //세션 정보 변경
            return new CMResponseDTO(1, "회원 수정 완료", userEntity);
        }
    }
}
