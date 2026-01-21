package com.example.demo.modules.resume.service;

import com.example.demo.infrastructure.file.fileHashService;
import com.example.demo.modules.resume.module.resumeEntity;
import com.example.demo.modules.resume.repository.ResumeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;
@Slf4j
@RequiredArgsConstructor
public class ResumePersistenceService {
    private final fileHashService hashService;
    private final ResumeRepository resumeRepository;
    public Optional<resumeEntity> findExistingResume(MultipartFile file) throws IOException, NoSuchAlgorithmException {
        String hash = hashService.calculateHash(file);
        Optional<resumeEntity> entity = resumeRepository.findByHash(hash);
        if(entity.isPresent()){
            log.info("检测到重复简历");
            resumeEntity resumeEntity = entity.get();
            resumeEntity.incrementAccessCount();
            resumeRepository.save(resumeEntity);
        }
        return entity;
    }
}
