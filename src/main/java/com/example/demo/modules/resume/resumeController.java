package com.example.demo.modules.resume;

import com.example.demo.Common.Config.AppConfig;
import com.example.demo.modules.resume.service.uploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/resume")
@RequiredArgsConstructor
public class resumeController {
    private final AppConfig appConfig;
    private final uploadService uploadService;
    @PostMapping("/upload")
    public void uploadResume(MultipartFile file){
        uploadService.uploadAndAnalise(file);
    }
}
