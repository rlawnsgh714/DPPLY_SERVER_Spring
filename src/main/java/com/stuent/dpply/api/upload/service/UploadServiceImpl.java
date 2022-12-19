package com.stuent.dpply.api.upload.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.stuent.dpply.api.upload.exception.UploadServerException;
import com.stuent.dpply.common.config.properties.S3Properties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UploadServiceImpl implements UploadService{

    private final AmazonS3Client amazonS3Client;
    private final S3Properties s3Properties;

    @Override
    public String uploadFile(MultipartFile multipartFile) {
        try {
            File convertFile = convert(multipartFile)
                    .orElseThrow(() -> new IllegalArgumentException("file convert error"));

            String fileName = s3Properties.getBucket() + "/" + UUID.randomUUID() + convertFile.getName();

            amazonS3Client.putObject(
                    new PutObjectRequest(s3Properties.getBucket(), fileName, convertFile).withCannedAcl(CannedAccessControlList.PublicRead));

            convertFile.delete();

            return amazonS3Client.getUrl(s3Properties.getBucket(), fileName).toString();
        }catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        throw UploadServerException.EXCEPTION; //FileUploadException
    }

    private Optional<File> convert(MultipartFile file) throws IOException {
        File convertFile = new File(System.getProperty("user.dir") + "/" + file.getOriginalFilename());
        if (convertFile.createNewFile()) {
            try (FileOutputStream fos = new FileOutputStream(convertFile)) {
                fos.write(file.getBytes());
            }
            return Optional.of(convertFile);
        }
        return Optional.empty();
    }
}
