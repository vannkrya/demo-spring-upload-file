package com.example.demouploadfile.controller;


import com.example.demouploadfile.response.FileResponse;
import com.example.demouploadfile.service.FileService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@AllArgsConstructor
@RequestMapping("/files")
public class FileController {

    private final FileService fileService;

//    public FileUploadController(FileUploadService fileUploadService) {
//        this.fileUploadService = fileUploadService;
//    }

    @PostMapping(value = "/upload-file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadFile(@RequestParam MultipartFile multipartFile) throws IOException {
        String fileName = fileService.uploadFile(multipartFile);
        return ResponseEntity.ok(new FileResponse<>(
                "successfully upload file",
                201,
                fileName
        ));
    }

    @GetMapping("/get-file")
    public ResponseEntity<?> getFileByName(@RequestParam String fileName) throws IOException {
        Resource resource = fileService.getFileByName(fileName);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG)
                .body(resource);
    }
}
