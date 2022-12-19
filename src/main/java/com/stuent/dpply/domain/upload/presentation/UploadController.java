package com.stuent.dpply.domain.upload.presentation;

import com.stuent.dpply.domain.upload.service.UploadService;
import com.stuent.dpply.common.response.ResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/upload")
@RequiredArgsConstructor
public class UploadController {

    private final UploadService uploadService;

    @PostMapping
    public ResponseData<String> uploadFile(
            @RequestParam("file") MultipartFile multipartFile
    ) {
        String fileUrl = uploadService.uploadFile(multipartFile);
        return new ResponseData<>(
                HttpStatus.OK,
                "파일 업로드 성공",
                fileUrl
        );
    }
}
