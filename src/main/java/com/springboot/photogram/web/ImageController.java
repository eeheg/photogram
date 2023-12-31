package com.springboot.photogram.web;

import com.springboot.photogram.config.auth.PrincipalDetails;
import com.springboot.photogram.handler.ex.CustomValidationException;
import com.springboot.photogram.service.ImageService;
import com.springboot.photogram.web.dto.image.ImageUploadDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class ImageController {

    private final ImageService imageService;
    @GetMapping({"/", "/image/story"})
    public String story() {
        return "image/story";
    }

    @GetMapping({"/image/popular"})
    public String popular() {
        return "image/popular";
    }

    @GetMapping({"/image/upload"})
    public String upload() {
        return "image/upload";
    }

    @PostMapping("/image")
    public String imageUpload(ImageUploadDTO imageUploadDTO, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        if(imageUploadDTO.getFile().isEmpty()) {
            throw new CustomValidationException("이미지가 첨부되지 않았습니다.", null);
        }
        imageService.사진업로드(imageUploadDTO, principalDetails);
        return "redirect:/user/"+principalDetails.getUser().getId();
    }
}
