package com.example.documentservice.repository;

import com.example.documentservice.domain.DocumentEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DocumentRepository extends CrudRepository<DocumentEntity, Long> {
    Optional<DocumentEntity> findByDocId(String docId);
}
