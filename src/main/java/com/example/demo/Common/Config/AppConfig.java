package com.example.demo.Common.Config;

import org.springframework.stereotype.Component;

import java.util.List;

@Component

public class AppConfig {
    private String uploadDir;
    private List<String> allowedTypes;

    public String getUploadDir() {
        return uploadDir;
    }

    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }

    public List<String> getAllowedTypes() {
        return allowedTypes;
    }

    public void setAllowedTypes(List<String> allowedTypes) {
        this.allowedTypes = allowedTypes;
    }
}
