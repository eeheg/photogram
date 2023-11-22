package com.springboot.photogram.domain.subscribe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface SubscribeRepository extends JpaRepository<Subscribe, Long> {

    @Modifying  //INSERT,DELETE, UPDATE를 네이티브 쿼리로 작성하려면 @Modifying 어노테이션 필요!!
    @Query(value = "INSERT INTO subscribe(from_user_id, to_user_id, create_date) VALUES(:fromUserId, :toUserId, now())", nativeQuery=true)
    int mSubscribe(Long fromUserId, Long toUserId);  // 1(변경된 행의 개수가 리턴됨), -1

    @Modifying
    @Query(value = "DELETE FROM subscribe WHERE from_user_id = :fromUserId AND to_user_id = :toUserId", nativeQuery=true)
    int mUnSubscribe(Long fromUserId, Long toUserId);  // 1(변경된 행의 개수가 리턴됨), -1

    @Query(value="select count(*) from subscribe where from_user_id = :pageUserId", nativeQuery=true)
    int mSubscribeCount(Long pageUserId);

    @Query(value="select count(*) from subscribe where from_user_id = :principalId and to_user_id = :pageUserId", nativeQuery = true)
    int mSubscribeState(Long principalId, Long pageUserId);

}