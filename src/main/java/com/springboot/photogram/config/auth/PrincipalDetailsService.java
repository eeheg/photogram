package com.springboot.photogram.config.auth;

import com.springboot.photogram.domain.user.User;
import com.springboot.photogram.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PrincipalDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    //1. 패스워드는 알아서 체킹하니까 신경 쓸 필요 없다.
    //2. 리턴이 잘 되면 자동으로 UserDetails 타입을 세션으로 만든다.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userEntity = userRepository.findByUsername(username);
        if(userEntity == null) {
            return null;
        } else {
            return new PrincipalDetails(userEntity);  //PrincipalDetails 얘가 바로 세션정보

            /* 세션정보 찾기
                1. 어노테이션 사용
                    @AuthenticationPrincipal 어노테이션 사용
                     -> principalDetails.getUser()
                2. 직접 찾기
                    PrincipalDetails principalDetails1 = (PrincipalDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                    -> principalDetails1.getUser()
             */

        }
    }
}
