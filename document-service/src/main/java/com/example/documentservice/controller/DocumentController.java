package com.example.documentservice.controller;

import com.example.documentservice.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/")
public class DocumentController {

    private final DocumentService documentService;

    @Autowired
    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @PostMapping("/projects/{projectId}/documents")
    public ResponseEntity<String> uploadDocument(@PathVariable String projectId,
                                                 @RequestParam("file") MultipartFile file) {
        // Assuming uploaderId is retrieved from JWT token in a real scenario
        String uploaderId = "temp-user";
        String docId = documentService.uploadFile(projectId, uploaderId, file);
        return ResponseEntity.ok("File uploaded successfully. Document ID: " + docId);
    }

    @GetMapping("/documents/{docId}")
    public ResponseEntity<Resource> downloadDocument(@PathVariable String docId) {
        Resource resource = documentService.downloadFile(docId);
        // This part needs the actual file name to be retrieved
        String contentType = "application/octet-stream";
        String headerValue = "attachment; filename=\"" + resource.getFilename() + "\"";

        return ResponseEntity.ok()
                .contentType(org.springframework.http.MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
                .body(resource);
    }
}
