package com.springboot.photogram.domain.image;

import com.springboot.photogram.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Image {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY) //번호 증가 전략이 데이터베이스를 따라간다.
    private Long id;
    private String caption;  //오늘 나 너무 피곤해!
    private String postImageUrl;  //사진을 전송 받아서 그 사진을 서버의 특정폴더에 저장 - DB에 그 저장된 경로를 insert

    @JoinColumn(name="userId")
    @ManyToOne
    private User user;  //user:image = 1:N

    //이미지 좋아요
    //댓글

    private LocalDateTime createDate;

    @PrePersist
    public void createDate() {
        this.createDate = LocalDateTime.now();
    }

    //System.out.println으로 인한 무한참조 -> getter 오버라이딩하여 User부분 삭제
//    @Override
//    public String toString() {
//        return "Image{" +
//                "id=" + id +
//                ", caption='" + caption + '\'' +
//                ", postImageUrl='" + postImageUrl + '\'' +
//                ", createDate=" + createDate +
//                '}';
//    }
}
