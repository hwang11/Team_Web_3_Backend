package com.web.yapp.server.controller;

import com.web.yapp.server.domain.service.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class FileController {
    private final S3Uploader s3Uploader;

    @GetMapping("/file") // 파일 업로드 테스트용 뷰
    public String uploadView(){
        return "upload";
    }

    @PostMapping("/upload") //업로드 테스트 API
    public void upload(@RequestParam("data") List<MultipartFile> multipartFiles, String title, Long musicainId) throws IOException {
        System.out.println("------------이름 : "+multipartFiles.get(0).getOriginalFilename());
        String coverUrl = s3Uploader.upload(multipartFiles.get(0), "static");
        System.out.println("cover"+coverUrl);
    }
}
