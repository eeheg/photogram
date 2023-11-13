package com.springboot.photogram.web;

import com.springboot.photogram.domain.user.User;
import com.springboot.photogram.service.AuthService;
import com.springboot.photogram.web.dto.auth.SignupDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/* log.trace("가장 디테일한 로그");
   log.warn("경고");
   log.info("정보성 로그");
   log.debug("디버깅용 로그");
   log.error("에러",e);
*/
@Slf4j
@RequiredArgsConstructor  // final 필드를 DI 할 때 사용
@RestController  // (IoC)파일을 리턴하는 컨트롤러
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

    @PostMapping("/auth/signup")
    public String signup(@ModelAttribute SignupDTO signupDTO) {
        log.info(signupDTO.toString());  //User <- SignupDTO
        User user = signupDTO.toEntity();
        log.info(user.toString());
        User userEntity = authService.회원가입(user);
        System.out.println(userEntity);
        return "redirect:/auth/signin";
    }
}
