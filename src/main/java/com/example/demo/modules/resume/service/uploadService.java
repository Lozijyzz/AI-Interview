package com.example.demo.modules.resume.service;

import com.example.demo.Common.Config.AppConfig;
import com.example.demo.infrastructure.file.Filevalidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class uploadService {
    private AppConfig appConfig;
    private ParseService parseService;
    private static final long MAX_FILE_SIZE = 10 * 1024 * 1024;
    public void uploadAndAnalise(MultipartFile file){
        //验证文件
        Filevalidation.validateFile(file,MAX_FILE_SIZE,"简历");
        //检验文件类型
        String typeName = parseService.getTypeName(file);
        validate(typeName);
        //
    }
    public void validate(String typeName){
        Filevalidation.validateFile();

    }
}
