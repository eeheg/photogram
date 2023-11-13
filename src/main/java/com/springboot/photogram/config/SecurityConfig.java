package com.springboot.photogram.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration  //IoC
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder encode() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())  // csrf를 disable 한다.
                .authorizeHttpRequests(request -> request
                        .requestMatchers(
                                new AntPathRequestMatcher("/"),
                                new AntPathRequestMatcher("/user/**"),
                                new AntPathRequestMatcher("/image/**"),
                                new AntPathRequestMatcher("/subscribe/**"),
                                new AntPathRequestMatcher("/comment/**")
                        ).authenticated()
                        .anyRequest().permitAll())  //다른 경로들은 모두 허용한다.
                .formLogin(login -> login
                        .loginPage("/auth/signin")	// 인증해야 하는 경로에 접근했을 경우 이 로그인페이지로 이동한다.
                        .defaultSuccessUrl("/", true)); //여기서 로그인을 성공하면 여기로 이동한다.
        return http.build();
    }
}
