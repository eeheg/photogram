package com.springboot.photogram.service;

import com.springboot.photogram.domain.subscribe.Subscribe;
import com.springboot.photogram.domain.subscribe.SubscribeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class SubscribeService {

    private final SubscribeRepository subscribeRepository;

    @Transactional
    public int 구독하기(Long fromUserId, Long toUserId) {
        int result = subscribeRepository.mSubscribe(fromUserId, toUserId);
        return result;
    }

    @Transactional
    public int 구독취소하기(Long fromUserId, Long toUserId) {
        int result = subscribeRepository.mUnSubscribe(fromUserId, toUserId);
        return result;
    }
}
