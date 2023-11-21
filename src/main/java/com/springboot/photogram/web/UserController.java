package com.springboot.photogram.web;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.springboot.photogram.config.auth.PrincipalDetails;
import com.springboot.photogram.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;
    @GetMapping("/user/{id}")
    public String profile(@PathVariable Long id, Model model) {
        model.addAttribute("user",userService.회원프로필(id));
        return "user/profile";
    }

    @GetMapping("/user/{id}/update")
    public String update(@PathVariable Long id, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        //1. 추천
        System.out.println("세션정보:"+principalDetails.getUser());

        // 2. 극혐
        PrincipalDetails principalDetails1 = (PrincipalDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("직접 찾은 세션정보:"+principalDetails1.getUser());

        return "user/update";
    }
}
