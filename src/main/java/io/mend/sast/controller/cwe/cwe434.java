package io.mend.sast.controller.cwe;

import java.io.File;
import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import io.mend.sast.util.ApplicationConstants;


@Controller
@RequestMapping("/cwe434")
public class cwe434 {
    
    @PostMapping("/safe/upload")
    public ResponseEntity<String> handleUploadSafe(@RequestParam MultipartFile file){
        String fileName = file.getOriginalFilename();
        if (!ApplicationConstants.ALLOWED_FILE_TYPES.contains(file.getContentType())) { // SANITIZER
            return new ResponseEntity<>("Unsupported filetype", HttpStatus.BAD_REQUEST);
        }
        
        try {
            File uploadDirectory = new File(ApplicationConstants.UPLOAD_DIRECTORY);
            if (!uploadDirectory.exists()) {
                uploadDirectory.mkdirs();
            }
            File dest = new File(ApplicationConstants.UPLOAD_DIRECTORY + fileName);
            file.transferTo(dest);
        } catch (IOException e) {
            return new ResponseEntity<>("Failed to save file", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("File uploaded successfully", HttpStatus.OK);
    }

    @PostMapping("/unsafe/upload")
    public ResponseEntity<String> handleUploadUnsafe(@RequestParam MultipartFile file){
        String fileName = file.getOriginalFilename();
        
        try {
            File uploadDirectory = new File(ApplicationConstants.UPLOAD_DIRECTORY);
            if (!uploadDirectory.exists()) {
                uploadDirectory.mkdirs();
            }
            File dest = new File(ApplicationConstants.UPLOAD_DIRECTORY + fileName); //SANITIZER
            file.transferTo(dest);
        } catch (IOException e) {
            return new ResponseEntity<>("Failed to save file", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("File uploaded successfully", HttpStatus.OK);
    }
}
