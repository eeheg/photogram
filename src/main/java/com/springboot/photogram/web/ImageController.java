package com.springboot.photogram.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ImageController {

    @GetMapping({"/", "/image/story"})
    public String story() {
        System.out.println("들어오긴했니?");
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

}
