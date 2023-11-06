package com.book.FileUpload.command.application.controller;

import com.book.FileUpload.command.application.service.FileService;
import com.book.FileUpload.command.domain.aggregate.entity.DownloadEntity;
import com.book.FileUpload.command.domain.repository.FileRepository;
import com.nimbusds.jose.util.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Controller
@RequiredArgsConstructor

public class FileDownload {
    private final FileService fileService;
    private final FileRepository fileRepository;

    @GetMapping("/view")
    public String view(Model model) {
        List<DownloadEntity> files = fileRepository.findAll();
        model.addAttribute("all",files);
        return "view";
    }


    //   이미지 출력
    @GetMapping("/images/{fileId}")
    @ResponseBody
    public UrlResource downloadImage(@PathVariable("fileId") Long id, Model model) throws IOException {

        DownloadEntity file = fileRepository.findById(id).orElse(null);
        return new UrlResource("file:" + file.getSavedPath());
    }

    // 첨부 파일 다운로드
    @GetMapping("/attach/{id}")
    public ResponseEntity<UrlResource> downloadAttach(@PathVariable Long id) throws MalformedURLException {

        DownloadEntity file = fileRepository.findById(id).orElse(null);

        UrlResource resource = new UrlResource("file:" + file.getSavedPath());



        String encodedFileName = UriUtils.encode(file.getOrgNm(), StandardCharsets.UTF_8);

        // 파일 다운로드 대화상자가 뜨도록 하는 헤더를 설정해주는 것
        // Content-Disposition 헤더에 attachment; filename="업로드 파일명" 값을 준다.
        String contentDisposition = "attachment; filename=\"" + encodedFileName + "\"";

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,contentDisposition).body(resource);
    }

}
