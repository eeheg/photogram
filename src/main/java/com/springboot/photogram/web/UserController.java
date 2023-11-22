package com.springboot.photogram.web;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.springboot.photogram.config.auth.PrincipalDetails;
import com.springboot.photogram.service.UserService;
import com.springboot.photogram.web.dto.user.UserProfileDTO;
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
    @GetMapping("/user/{pageUserId}")
    public String profile(@PathVariable Long pageUserId, Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        UserProfileDTO userProfileDTO = userService.회원프로필(pageUserId, principalDetails.getUser().getId());
        model.addAttribute("userProfileDTO",userProfileDTO);
        return "user/profile";
    }

    @GetMapping("/user/{id}/update")
    public String update(@PathVariable Long id, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        return "user/update";
    }
}
