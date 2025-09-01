package com.example.documentservice.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface DocumentService {
    String uploadFile(String projectId, String uploaderId, MultipartFile file);
    Resource downloadFile(String docId);
}
