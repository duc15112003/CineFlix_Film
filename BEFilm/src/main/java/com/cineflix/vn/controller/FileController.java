package com.cineflix.vn.controller;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;
@CrossOrigin("*")
@RestController
@RequestMapping("/api/files")
public class FileController {


    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("username") String username) {
        String UPLOAD_DIR = "D:\\NhacUploader\\" + username;
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No file selected to upload");
        }

        try {
            File uploadDir = new File(UPLOAD_DIR);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            String originalFilename = file.getOriginalFilename();
            String extension = "";

            if (originalFilename != null && originalFilename.contains(".")) {
                extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            }

            String uniqueFilename = UUID.randomUUID().toString() + extension;
            File destinationFile = new File(UPLOAD_DIR + File.separator + uniqueFilename);

            file.transferTo(destinationFile);

            return ResponseEntity.ok("File uploaded successfully: " + destinationFile.getAbsolutePath());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while uploading file: " + e.getMessage());
        }
    }

    @GetMapping("/getVideo")
    public ResponseEntity<Resource> getVideo(@RequestParam("username") String username, @RequestParam("filename") String filename) {
        String VIDEO_DIR = "D:\\NhacUploader\\" + username;
        File folder = new File(VIDEO_DIR);

        if (!folder.exists() || !folder.isDirectory()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        File[] matchingFiles = folder.listFiles((dir, name) -> name.startsWith(filename));
        if (matchingFiles == null || matchingFiles.length == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        File videoFile = matchingFiles[0]; // Lấy file đầu tiên khớp tên
        try {
            Path path = videoFile.toPath();
            Resource resource = new UrlResource(path.toUri());
            String contentType = Files.probeContentType(path);

            if (contentType == null) {
                contentType = "application/octet-stream";
            }
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .body(resource);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}

