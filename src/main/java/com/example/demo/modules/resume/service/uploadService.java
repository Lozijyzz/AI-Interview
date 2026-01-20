package com.example.demo.modules.resume.service;

import com.example.demo.Common.Config.AppConfig;
import com.example.demo.infrastructure.file.FilevalidationService;
import com.example.demo.infrastructure.file.fileStorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
@Slf4j
@Service
@RequiredArgsConstructor
public class uploadService {
    private AppConfig appConfig;
    private ParseService parseService;
    private fileStorageService filestorageservice;
    private static final long MAX_FILE_SIZE = 10 * 1024 * 1024;
    public void uploadAndAnalise(MultipartFile file){
        //验证文件
        FilevalidationService.validateFile(file,MAX_FILE_SIZE,"简历");
        //检验文件类型
        String typeName = parseService.getTypeName(file);
        validate(typeName);
        //解析简历
        String resumeText=parseService.parseResume(file);

        //检测简历是否存在(去重)
        //保存简历到Restfs
        String fileKey = filestorageservice.uploadResume(file);
        log.info("简历已保存到RESTFS");
        //保存简历到数据库

        //发送分析任务到 Redis Stream（异步处理）

    }
    public void validate(String typeName){
        FilevalidationService.validateFile();

    }
}
