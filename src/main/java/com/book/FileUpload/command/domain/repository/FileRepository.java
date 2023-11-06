package com.book.FileUpload.command.domain.repository;

import com.book.FileUpload.command.domain.aggregate.entity.DownloadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<DownloadEntity, Long> {
}
