package com.springboot.photogram.service;

import com.springboot.photogram.domain.subscribe.SubscribeRepository;
import com.springboot.photogram.handler.ex.CustomValidationApiException;
import com.springboot.photogram.web.dto.subscribe.SubscribeDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SubscribeService {

    private final SubscribeRepository subscribeRepository;
    private final EntityManager em;  //Repository는 EntityManager를 구현해서 만들어진 구현체

    @Transactional
    public void 구독하기(Long fromUserId, Long toUserId) {
        try {
            int result = subscribeRepository.mSubscribe(fromUserId, toUserId);
            System.out.println(result);
        } catch (Exception e){
            throw new CustomValidationApiException("이미 구독을 하였습니다.");
            //throw new CustomApiException("이미 구독을 하였습니다.");
        }
    }

    @Transactional
    public void 구독취소하기(Long fromUserId, Long toUserId) {
        subscribeRepository.mUnSubscribe(fromUserId, toUserId);
    }

    @Transactional(readOnly = true)
    public List<SubscribeDTO> 구독리스트(Long principalId, Long pageUserId) {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT u.id, u.username, u.profile_image_url, ");
        sb.append("if ((SELECT 1 FROM subscribe WHERE from_user_id=? AND to_user_id=u.id), 1, 0) subscribe_state, ");  //principalId
        sb.append("if ((?=u.id), 1, 0) equal_user_state ");  //principalId
        sb.append("FROM user u INNER JOIN subscribe s ");
        sb.append("ON u.id=s.to_user_id ");
        sb.append("WHERE s.from_user_id=?"); //세미콜론 금지  //pageUserId

        Query query = em.createNativeQuery(sb.toString()).setParameter(1,principalId).setParameter(2,principalId).setParameter(3,pageUserId);
        return new JpaResultMapper().list(query, SubscribeDTO.class);
    }
}
