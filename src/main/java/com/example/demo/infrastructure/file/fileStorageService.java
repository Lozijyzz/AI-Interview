package com.example.demo.infrastructure.file;

import com.example.demo.Common.Config.storageConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.core.sync.ResponseTransformer;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;


@RequiredArgsConstructor
public class fileStorageService {
    final private S3Client s3Client;
    final private storageConfig storageConfig;
    //上传简历
    public String uploadResume(MultipartFile file){return uploadFile(file,"resumes");}
    //下载简历
    //删除简历
    //上传知识库
    //删除知识库
    public String uploadFile(MultipartFile file,String prefix){
        String originalFilename=file.getOriginalFilename();
        String fileKey=generateFileKey(originalFilename,prefix);
        try{
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(storageConfig.getBucket())
                    .key(fileKey)
                    .contentType(file.getContentType())
                    .contentLength(file.getSize())
                    .build();
            s3Client.putObject(putObjectRequest, RequestBody.fromInputStream(file.getInputStream(),file.getSize()));
            return fileKey;
        } catch (IOException e) {
            throw new RuntimeException(e);//需要改
        }
    }
    public byte[] downloadFile(String FileKey) throws Exception {
        if(!fileExist(FileKey)){
            throw new Exception();
        }
        try {
            GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                    .bucket(storageConfig.getBucket())
                    .key(FileKey)
                    .build();
            // 关键点：使用 ResponseTransformer.toBytes()
            ResponseBytes<GetObjectResponse> objectBytes = s3Client.getObject(getObjectRequest,ResponseTransformer.toBytes());
            return objectBytes.asByteArray();
        }catch(Exception e){
            throw e;
        }
    }
    public boolean fileExist(String FileKey){
        return true;
    }
    //需要改
    public String generateFileKey(String name,String prefix){
        return "123";
    }

}
