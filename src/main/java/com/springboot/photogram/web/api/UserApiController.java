package com.springboot.photogram.web.api;

import com.springboot.photogram.config.auth.PrincipalDetails;
import com.springboot.photogram.domain.user.User;
import com.springboot.photogram.handler.ex.CustomValidationApiException;
import com.springboot.photogram.service.SubscribeService;
import com.springboot.photogram.service.UserService;
import com.springboot.photogram.web.dto.CMResponseDTO;
import com.springboot.photogram.web.dto.subscribe.SubscribeDTO;
import com.springboot.photogram.web.dto.user.UserUpdateDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final UserService userService;
    private final SubscribeService subscribeService;

    @GetMapping("/api/user/{pageUserId}/subscribe")
    public ResponseEntity subscribeList(@PathVariable Long pageUserId, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        List<SubscribeDTO> subscribeDTOList = subscribeService.구독리스트(principalDetails.getUser().getId(), pageUserId);
        return new ResponseEntity<>(new CMResponseDTO<>(1, "구독자 정보 불러오기 성공", subscribeDTOList),HttpStatus.OK);
    }

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
            User userEntity = userService.회원수정(id, userUpdateDTO.toEntity());
            principalDetails.setUser(userEntity);  //세션 정보 변경
            return new CMResponseDTO(1, "회원 수정 완료", userEntity);  //응답시에 userEntity의 모든 getter 함수가 호출되고 JSON으로 파싱하여 응답
        }
    }
}
