package com.roman.bookshelf.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AmazonS3Service {
    private final S3Client s3Client;
    @Value("${aws.s3.bucket-name}")
    private String bucketName;
    public String uploadPhoto(MultipartFile photo, String folder){
        String filename = UUID.randomUUID() + "-" + photo.getOriginalFilename();
        String key = folder + filename;
        try {
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(key)
                    .contentType(photo.getContentType())
                    .build();
            s3Client.putObject(putObjectRequest, RequestBody.fromBytes(photo.getBytes()));
            return String.format("https://%s.s3.%s.amazonaws.com/%s",
                    bucketName,
                    s3Client.serviceClientConfiguration().region(),
                    key);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private String getFileName(String fileName){
        return UUID.randomUUID() + "-" + fileName;
    }
}
