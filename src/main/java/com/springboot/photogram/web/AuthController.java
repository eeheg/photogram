package com.springboot.photogram.web;

import com.springboot.photogram.domain.user.User;
import com.springboot.photogram.handler.ex.CustomValidationException;
import com.springboot.photogram.service.AuthService;
import com.springboot.photogram.web.dto.auth.SignupDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/* log.trace("가장 디테일한 로그");
   log.warn("경고");
   log.info("정보성 로그");
   log.debug("디버깅용 로그");
   log.error("에러",e);
*/
@Slf4j
@RequiredArgsConstructor  // final 필드를 DI 할 때 사용
@Controller  // (IoC)파일을 리턴하는 컨트롤러
public class AuthController {

    private final AuthService authService;

    @GetMapping("/auth/signin")
    public String signinForm() {
        return "auth/signin";
    }

    @GetMapping("/auth/signup")
    public String signupForm() {
        return "auth/signup";
    }

    //@Valid: 유효성검사를 해줌. SignupDTO에 설정된 Validation 어노테이션대로 검사.
    //@Valid 에서 오류가 발생하면 BindingResult 객체에 다 모아줌 -> getFieldErrors()로 꺼내볼 수 있음.
    @PostMapping("/auth/signup")
    public String signup(@Valid @ModelAttribute SignupDTO signupDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            throw new CustomValidationException("유효성 검사 실패함", errorMap);
        } else {
            authService.회원가입(signupDTO.toEntity());
            return "redirect:/auth/signin";
        }
    }
}
