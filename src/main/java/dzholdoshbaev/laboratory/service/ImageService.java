package dzholdoshbaev.laboratory.service;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    void uploadImage(MultipartFile file, String username);


    ResponseEntity<?> downloadImage(String filename, MediaType mediaType);
}
