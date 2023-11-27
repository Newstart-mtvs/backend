package com.book.FileUpload.command.application.controller;

import com.book.FileUpload.command.application.service.FileService;
import com.book.FileUpload.command.domain.aggregate.entity.DownloadEntity;
import com.book.FileUpload.command.domain.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.apache.http.entity.FileEntity;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class FileController {

    private final FileService fileService;


    @GetMapping("/upload")
    public String testUploadForm() {

        return "download";
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("files") List<MultipartFile> files) throws IOException {
        fileService.saveFile(file);

        for (MultipartFile multipartFile : files) {
            fileService.saveFile(multipartFile);
        }

        return "redirect:/";
    }





}
