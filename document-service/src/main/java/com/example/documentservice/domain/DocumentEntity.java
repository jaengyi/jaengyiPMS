package com.example.documentservice.domain;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "documents")
public class DocumentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String docId;

    @Column(nullable = false)
    private String docName;

    @Column(nullable = false)
    private String filePath; // This would be the key in the S3 bucket

    @Column(nullable = false)
    private String uploader; // userId

    @Column(nullable = false)
    private String projectId;

    private LocalDateTime createdAt;
}
