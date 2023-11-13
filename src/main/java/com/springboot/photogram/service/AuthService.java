package com.springboot.photogram.service;

import com.springboot.photogram.domain.user.User;
import com.springboot.photogram.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service  // (IoC) 트랜잭션 관리
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional  //Write(Insert, Update, Delete)할 때 항상 이거 걸자.
    public User 회원가입(User user) {
        String rawPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        user.setPassword(encPassword);
        user.setRole("ROLE_USER");  //관리자 ROLE_ADMIN
        return userRepository.save(user); // Entity 를 리턴
    }
}
