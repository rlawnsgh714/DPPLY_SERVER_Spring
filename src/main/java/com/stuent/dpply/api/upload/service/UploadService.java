package com.stuent.dpply.api.upload.service;

import org.springframework.web.multipart.MultipartFile;

public interface UploadService {

    String uploadFile(MultipartFile multipartFile);
}
