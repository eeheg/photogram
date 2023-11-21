package com.springboot.photogram.web.dto.image;

import com.springboot.photogram.domain.image.Image;
import com.springboot.photogram.domain.user.User;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ImageUploadDTO {
    private MultipartFile file;
    private String caption;

    public Image toEntity(String postImageUrl, User user) {
        return Image.builder()
                .caption(caption)
                .postImageUrl(postImageUrl)
                .user(user)
                .build();
    }
}
