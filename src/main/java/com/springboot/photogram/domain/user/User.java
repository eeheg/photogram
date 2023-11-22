package com.springboot.photogram.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.springboot.photogram.domain.image.Image;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

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

    @Column(length = 20, unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String name;
    private String website;
    private String bio;
    @Column(nullable = false)
    private String email;
    private String phone;
    private String gender;
    private String profileImageUrl;
    private String role;
    private LocalDateTime createDate;

    //나는 연관관계의 주인이 아니다. 그러므로 테이블에 컬럼을 만들지 마.
    // User를 Select할 때 해당 User id로 등록된 image들을 가져와.
    // FetchType.Lazy=User를 Select할 때 해당 User id로 등록된 image들을 가져오지 마. - 대신 getImages()가 호출될 때 가져와.
    // FetchType.Eager=User를 Select할 때 해당 User id로 등록된 image들을 전부 Join해서 가져와.
    @OneToMany(mappedBy = "user", fetch=FetchType.LAZY)
    @JsonIgnoreProperties("{user}")  //순환참조 문제 해결
    public List<Image> images;  //양방향 매핑

    @PrePersist  //디비에 insert 되기 직전에 실행
    public void createDate() {
        this.createDate = LocalDateTime.now();
    }
}
