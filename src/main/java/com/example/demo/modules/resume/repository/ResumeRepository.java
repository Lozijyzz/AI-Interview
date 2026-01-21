package com.example.demo.modules.resume.repository;

import com.example.demo.modules.resume.module.resumeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResumeRepository extends JpaRepository<resumeEntity,Long> {
    public Optional<resumeEntity> findByHash(String Hash);
}
