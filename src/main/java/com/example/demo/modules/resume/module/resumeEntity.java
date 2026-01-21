package com.example.demo.modules.resume.module;

import com.example.demo.Common.model.AsyncTaskStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name="resumes",indexes={
        @Index(name="idx_resume_hash",columnList ="fileHash", unique=true)
})
@Getter
@Setter
public class resumeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,unique = true,length = 64)
    private String Hash;
    @Column(nullable = false)
    private String originalName;
    @Column(nullable = false)
    private LocalDateTime uploadedAt;
    private long size;
    private String contentType;
    @Column(length = 1000)
    private String storageUrl;
    @Column(columnDefinition = "TEXT")
    private String resumeText;
    private LocalDateTime lastVisit;
    private Integer accessCount=0;
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private AsyncTaskStatus asyncTaskStatus=AsyncTaskStatus.PENDING;
    @PrePersist
    protected void onCreated(){
        this.uploadedAt=LocalDateTime.now();
        this.lastVisit=LocalDateTime.now();
        this.accessCount=1;
    }
    public void incrementAccessCount(){
        this.accessCount++;
    }

}
