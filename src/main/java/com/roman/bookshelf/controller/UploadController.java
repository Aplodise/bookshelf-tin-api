package com.roman.bookshelf.controller;

import com.roman.bookshelf.service.AmazonS3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/upload")
@RequiredArgsConstructor
public class UploadController {

    private final AmazonS3Service s3Service;
    @PostMapping("/books")
    public ResponseEntity<String> uploadBookPhoto(@RequestParam("file") MultipartFile file){
        String imageUrl = s3Service.uploadPhoto(file, "BOOKS/");
        return ResponseEntity.ok(imageUrl);
    }

    @PostMapping("/authors")
    public ResponseEntity<String> uploadAuthorPhoto(@RequestParam("file") MultipartFile file){
        String imageUrl = s3Service.uploadPhoto(file, "AUTHORS/");
        return ResponseEntity.ok(imageUrl);
    }
}
