package com.academy.backend.service.common;

import com.academy.backend.exception.s3.FailedUploadException;
import com.academy.backend.exception.s3.S3AccessDeniedException;
import com.academy.backend.exception.s3.S3NotConnectedException;
import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class S3Service {

    private final AmazonS3 amazonS3;

    @Value("${s3.bucket-name}")
    private String bucketName;

    public String uploadImage(MultipartFile file, String path) {
        String fileName = path + UUID.randomUUID() + "_" + file.getOriginalFilename();

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        metadata.setContentType(file.getContentType());

        try {
            amazonS3.putObject(bucketName, fileName, file.getInputStream(), metadata);
        } catch (AmazonServiceException e) {
            throw new S3AccessDeniedException();
        } catch (AmazonClientException e) {
            throw new S3NotConnectedException();
        } catch (IOException e) {
            throw new FailedUploadException();
        }

        return amazonS3.getUrl(bucketName, fileName).toString();
    }
}
