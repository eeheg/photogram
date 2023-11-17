package com.springboot.photogram.web.dto.user;

import com.springboot.photogram.domain.user.User;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserUpdateDTO {
    @NotBlank
    private String name;
    @NotBlank
    private String password;
    private String website;
    private String bio;
    private String phone;
    private String gender;

    public User toEntity() {
        return User.builder()
                .name(name)
                .password(password)
                .website(website)
                .bio(bio)
                .phone(phone)
                .gender(gender)
                .build();
    }
}
