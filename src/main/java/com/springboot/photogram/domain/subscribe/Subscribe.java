package com.springboot.photogram.domain.subscribe;

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
@Table(
   uniqueConstraints = {
       @UniqueConstraint(
           name="subscribe_uk",
           columnNames={"fromUserId", "toUserId"}
       )
   }
)
public class Subscribe {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY) //번호 증가 전략이 데이터베이스를 따라간다.
    private Long id;

    @JoinColumn(name="fromUserId")  // 이렇게 컬럼명 만들어! 니 맘대로 만들지 말고!!
    @ManyToOne
    private User fromUser;

    @JoinColumn(name="toUserId")
    @ManyToOne
    private User toUser;

    private LocalDateTime createDate;

    @PrePersist  //디비에 insert 되기 직전에 실행
    public void createDate() {
        this.createDate = LocalDateTime.now();
    }
}
