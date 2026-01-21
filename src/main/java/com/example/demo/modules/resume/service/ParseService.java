package com.example.demo.modules.resume.service;

import com.example.demo.infrastructure.file.documentParseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
public class ParseService {
    public final documentParseService parService;
    public String parseResume(MultipartFile file){
        return parService.parse(file);
    }
}
