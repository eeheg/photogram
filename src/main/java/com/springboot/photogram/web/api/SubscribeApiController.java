package com.springboot.photogram.web.api;

import com.springboot.photogram.config.auth.PrincipalDetails;
import com.springboot.photogram.domain.subscribe.SubscribeRepository;
import com.springboot.photogram.service.SubscribeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class SubscribeApiController {

    private final SubscribeService subscribeService;

    @PostMapping("/api/subscribe/{toUserId}")
    public ResponseEntity subscribe(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable Long toUserId) {
        subscribeService.구독하기(principalDetails.getUser().getId(), toUserId);
        return null;
    }

    @PostMapping("/api/unSubscribe/{toUserId}")
    public ResponseEntity unSubscribe(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable Long toUserId) {
        subscribeService.구독취소하기(principalDetails.getUser().getId(), toUserId);
        return null;
    }
}
