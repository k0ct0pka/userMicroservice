package org.example.usermicroservice.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class StorageService {
    private static final Logger log = LoggerFactory.getLogger(StorageService.class);
    @Value("${application.bucket.name}")
    private String bucketName;
    @Autowired
    private AmazonS3 s3Client;

    public void uploadFile(MultipartFile file, Integer id, String profileImageId) {
        File fileObj = getFile(file);
        String fileName = "/profile-images/" + id + "/" + profileImageId;
        s3Client.putObject(new PutObjectRequest(bucketName, fileName, fileObj));
        fileObj.delete();
        log.info("File uploadded{}", fileName);
    }

    public byte[] downloadFile(Integer id, String profileImageId) {
        System.out.println(profileImageId);
        S3Object object = s3Client.getObject(bucketName, "/profile-images/" + id + "/" + profileImageId);
        S3ObjectInputStream inputStream = object.getObjectContent();
        try {
            return IOUtils.toByteArray(inputStream);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    private File getFile(MultipartFile file) {
        File fileObj = new File(file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(fileObj)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return fileObj;
    }

    public void deleteFile(Integer id, String profileImageId) {
        s3Client.deleteObject(bucketName, "/profile-images/" + id + "/" + profileImageId);
    }
}
