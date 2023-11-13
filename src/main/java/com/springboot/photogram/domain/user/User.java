package com.springboot.photogram.domain.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

//JPA-Java Persistence API (자바로 데이터를 영구적으로 저장(DB)할 수 있는 API)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY) //번호 증가 전략이 데이터베이스를 따라간다.
    private Long id;

    @Column(unique=true)
    private String username;

    private String password;
    private String name;
    private String website;
    private String bio;
    private String email;
    private String phone;
    private String gender;
    private String profileImageUrl;
    private String role;
    private LocalDateTime createDate;

    @PrePersist  //디비에 insert 되기 직전에 실행
    public void createDate() {
        this.createDate = LocalDateTime.now();
    }
}
