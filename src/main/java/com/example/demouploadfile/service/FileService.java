package com.example.demouploadfile.service;

import com.example.demouploadfile.model.FileUpload;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {
    String uploadFile(MultipartFile file) throws IOException;
    Resource getFileByName(String fileName) throws IOException;
}
