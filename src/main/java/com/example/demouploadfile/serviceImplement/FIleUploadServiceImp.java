package com.example.demouploadfile.serviceImplement;

import com.example.demouploadfile.service.FileService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FIleUploadServiceImp implements FileService {

    private final Path root = Paths.get("src/main/resources/images/");

    @Override
    public String uploadFile(MultipartFile multipartFile) throws IOException {
        try {
            //get file name
            String fileName = multipartFile.getOriginalFilename();
            if(fileName != null &&
                    fileName.contains(".jpg") ||
                    fileName.contains(".png") ||
                    fileName.contains(".jpeg")
            ) {
                fileName = UUID.randomUUID() + "." + StringUtils.getFilenameExtension(fileName);

                //create directory if not exist
                if(!Files.exists(root)) {
                    Files.createDirectories(root);
                }

                //copy file to directory
                Files.copy(multipartFile.getInputStream(), root.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
                return fileName;
            } else {
                return "File not found";
            }
        } catch (IOException e) {
            throw new IOException("File not found");

        }
    }

    @Override
    public Resource getFileByName(String fileName) throws IOException {
        Path path = Paths.get("src/main/resources/images/" + fileName);
        Resource resource = new ByteArrayResource(Files.readAllBytes(path));
        return resource;
    }
}
