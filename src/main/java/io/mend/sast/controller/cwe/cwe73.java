package io.mend.sast.controller.cwe;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Controller
@RequestMapping("/cwe73")
public class cwe73 {
    private static final List<String> ALLOWED_EXTENSIONS = List.of(".txt", ".pdf", ".doc");
    private static final String UPLOAD_DIRECTORY = "/app/uploads/";
    
    private boolean isValidFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex == -1) {
            return false;
        }

        String fileExtension = fileName.substring(dotIndex);
        return ALLOWED_EXTENSIONS.contains(fileExtension);
    }
    @GetMapping(value = "/upload_file")
    public String createFileUnsafe1(HttpServletRequest request, HttpServletResponse response){
        return "create_file"; 
    }

    @PostMapping("/unsafe/upload")
    public ResponseEntity<String> handleUnsafeFileUpload(HttpServletRequest request, HttpServletResponse response, @RequestParam("file") MultipartFile file){
        String fileName = file.getOriginalFilename();
        Path path = Paths.get(UPLOAD_DIRECTORY + fileName);       
        
        if (!isValidFileExtension(fileName)) {
            return ResponseEntity.badRequest().body("Invalid file extension. Allowed extensions: " + ALLOWED_EXTENSIONS);
        }

        try {
            byte[] bytes = file.getBytes();
            Files.write(path, bytes); //SINK
        } catch (Exception e) {
            ResponseEntity.badRequest().body("Failed to upload the file");
        }
        return ResponseEntity.ok().body("File uploaded successfully");
    }

    @PostMapping("/unsafe/upload2")
    public ResponseEntity<String> handleUnsafeFileUpload2(HttpServletRequest request, HttpServletResponse response, @RequestParam("file") MultipartFile file){
        String fileName = file.getOriginalFilename();
        Path path = Paths.get(UPLOAD_DIRECTORY + fileName);       
        
        if (!isValidFileExtension(fileName)) {
            return ResponseEntity.badRequest().body("Invalid file extension. Allowed extensions: " + ALLOWED_EXTENSIONS);
        }

        try (OutputStream os = Files.newOutputStream(path)) {
            byte[] bytes = file.getBytes();
            os.write(bytes); //SINK
        } catch (Exception e) {
            ResponseEntity.badRequest().body("Failed to upload the file");
        }
        return ResponseEntity.ok().body("File uploaded successfully");
    }

    @PostMapping("/unsafe/upload")
    public ResponseEntity<String> handleUnsafeFileUpload3(HttpServletRequest request, HttpServletResponse response, @RequestParam("file") MultipartFile file){
        String fileName = file.getOriginalFilename();
        Path path = Paths.get(UPLOAD_DIRECTORY + fileName);       
        
        if (!isValidFileExtension(fileName)) {
            return ResponseEntity.badRequest().body("Invalid file extension. Allowed extensions: " + ALLOWED_EXTENSIONS);
        }

        try (OutputStream os = Files.newOutputStream(path)) {
            os.write(file.getBytes()); //SINK
        } catch (Exception e) {
            ResponseEntity.badRequest().body("Failed to upload the file");
        }
        return ResponseEntity.ok().body("File uploaded successfully");
    }

    @PostMapping("/safe/upload")
    public ResponseEntity<String> handleSafeFileUpload(HttpServletRequest request, HttpServletResponse response, @RequestParam("file") MultipartFile file) {
        String fileName = file.getOriginalFilename();

        // Validate file extension
        if (!isValidFileExtension(fileName)) {
            return ResponseEntity.badRequest().body("Invalid file extension. Allowed extensions: " + ALLOWED_EXTENSIONS);
        }

        // Save file to disk
        try {
            File uploadDirectory = new File(UPLOAD_DIRECTORY);
            if (!uploadDirectory.exists()) {
                uploadDirectory.mkdirs();
            }
            String safe_filename = fileName.replaceAll("/", "");

            if(fileName.contains("..")){
                throw new IOException();
            }

            File dest = new File(UPLOAD_DIRECTORY + safe_filename); //SANITIZER
            file.transferTo(dest);

        } catch (IOException e) {
            return ResponseEntity.badRequest().body("Failed to save file: " + e.getMessage());
        }

        return ResponseEntity.ok().body("File uploaded successfully");
    }
}
