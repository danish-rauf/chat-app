package com.chatapp.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/media")
public class MediaController {

    private static final String UPLOAD_DIR = "uploads/";

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) throws IOException {

        File dir = new File(UPLOAD_DIR);
        if (!dir.exists()) dir.mkdirs();

        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();

        File dest = new File(UPLOAD_DIR + fileName);
        file.transferTo(dest);

        // return public URL
        return "http://10.0.2.2:8080/media/" + fileName;
    }
}