package com.example.demo.infrastructure.file;

import com.example.demo.Common.Config.AppConfig;
import com.example.demo.Common.Exception.BusinessException;
import lombok.RequiredArgsConstructor;

import java.util.List;
@RequiredArgsConstructor
public class FilevalidationService {
    private final AppConfig appConfig;
    public void validateFile(String contentType, List<String> allowedTypes, String errorMessage) {
        if (!isAllowedType(contentType, allowedTypes)) {
            throw new BusinessException(ErrorCode.BAD_REQUEST,
                    errorMessage != null ? errorMessage : "不支持的文件类型: " + contentType);
        }
    }

    private boolean isAllowedType(String contentType, List<String> allowedTypes) {
    }

}
