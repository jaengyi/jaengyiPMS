package com.example.documentservice.service;

import com.example.documentservice.domain.DocumentEntity;
import com.example.documentservice.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.UUID;

@Service
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;
    // private final AmazonS3 s3client; // S3 client would be injected

    @Autowired
    public DocumentServiceImpl(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    @Override
    public String uploadFile(String projectId, String uploaderId, MultipartFile file) {
        // 1. Generate a unique file path/key for S3
        String filePath = "projects/" + projectId + "/" + UUID.randomUUID() + "-" + file.getOriginalFilename();

        // 2. S3 Upload Logic
        // try {
        //     s3client.putObject(new PutObjectRequest(bucketName, filePath, file.getInputStream(), null));
        // } catch (IOException e) {
        //     throw new RuntimeException("File upload failed", e);
        // }

        // 3. Save metadata to the database
        DocumentEntity document = new DocumentEntity();
        document.setDocId(UUID.randomUUID().toString());
        document.setDocName(file.getOriginalFilename());
        document.setFilePath(filePath);
        document.setProjectId(projectId);
        document.setUploader(uploaderId);
        documentRepository.save(document);

        return document.getDocId();
    }

    @Override
    public Resource downloadFile(String docId) {
        DocumentEntity document = documentRepository.findByDocId(docId)
            .orElseThrow(() -> new RuntimeException("Document not found"));

        // S3 Download Logic
        // S3Object s3object = s3client.getObject(new GetObjectRequest(bucketName, document.getFilePath()));
        // return new InputStreamResource(s3object.getObjectContent());
        
        // Placeholder return
        return null;
    }
}
