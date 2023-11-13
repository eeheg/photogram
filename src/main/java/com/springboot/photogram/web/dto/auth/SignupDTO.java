package com.springboot.photogram.web.dto.auth;

import com.springboot.photogram.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignupDTO {
    private String username;
    private String password;
    private String email;
    private String name;

    public User toEntity() {
        return User.builder()
                .username(username)
                .password(password)
                .email(email)
                .name(name)
                .build();
    }
}
