package com.springboot.photogram.service;

import com.springboot.photogram.config.auth.PrincipalDetails;
import com.springboot.photogram.domain.image.Image;
import com.springboot.photogram.domain.image.ImageRepository;
import com.springboot.photogram.web.dto.image.ImageUploadDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.*;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ImageService {
    private final ImageRepository imageRepository;
    @Value("${file.path}")  //application.yml > file: path: C:/Users/user/fileForPhotogram/
    private String uploadFolder;

    @Transactional
    public void 사진업로드(ImageUploadDTO imageUploadDTO, PrincipalDetails principalDetails) {
        //uuid(Universally Unique Identifier): 네트워크 상에서 고유성이 보장되는 id를 만들기 위한 표준 규약
        UUID uuid = UUID.randomUUID();
        String imageFileName = uuid+"_"+imageUploadDTO.getFile().getOriginalFilename();  // "uuid_파일이름.jpg"
        System.out.println("이미지파일이름:"+imageFileName);
        Path imageFilePath = Paths.get(uploadFolder+imageFileName);

        try {
            Files.write(imageFilePath, imageUploadDTO.getFile().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Image image = imageUploadDTO.toEntity(imageFileName, principalDetails.getUser());
        imageRepository.save(image);

    }
}
