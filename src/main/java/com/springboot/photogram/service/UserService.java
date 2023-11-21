package com.springboot.photogram.service;

import com.springboot.photogram.domain.user.User;
import com.springboot.photogram.domain.user.UserRepository;
import com.springboot.photogram.handler.ex.CustomValidationApiException;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public User 회원프로필(Long userId) {
        //SELECT * FROM image WHERE user_id = :userId;
        User userEntity = userRepository.findById(userId).orElseThrow(() ->
                new ValidationException("해당 프로필 페이지는 없는 페이지입니다.", null)
        );
        userEntity.getImages().get(0);
        return userEntity;
    }

    @Transactional
    public User 회원수정(Long id, User user) {
        /*  1.영속화
         - get(): 무조건 찾았다. 걱정마
         - orElseThrow(): 못찾았어 Exception 발동
        */
        User userEntity = userRepository.findById(id).orElseThrow(() -> new CustomValidationApiException("찾을 수 없는 id입니다."));

        //2. 영속화된 오브젝트를 수정
        String rawPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        userEntity.setName(user.getName());
        userEntity.setPassword(encPassword);
        userEntity.setBio(user.getBio());
        userEntity.setWebsite(user.getWebsite());
        userEntity.setPhone(user.getPhone());
        userEntity.setGender(user.getGender());

        // 3. 더티체킹 (업데이트 완료)
        return userEntity;
    }
}
