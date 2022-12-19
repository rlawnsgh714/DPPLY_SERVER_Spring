package com.stuent.dpply.domain.upload.service;

import org.springframework.web.multipart.MultipartFile;

public interface UploadService {

    String uploadFile(MultipartFile multipartFile);
}
