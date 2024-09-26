package dzholdoshbaev.laboratory.service.impl;

import dzholdoshbaev.laboratory.repository.UsersRepository;
import dzholdoshbaev.laboratory.service.PostImagesService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class PostImagesServiceImpl implements PostImagesService {
    private final UsersRepository usersRepository;

    static String UPLOAD_DIR = "data/posts/";

    @SneakyThrows
    public void uploadImage(MultipartFile file, String username) {
        String uuidFile = UUID.randomUUID().toString();
        String resultFileName = uuidFile + "_" + file.getOriginalFilename();

        Path pathDir = Paths.get(UPLOAD_DIR);
        Files.createDirectories(pathDir);

        Path filePath = Paths.get(pathDir + "/" + resultFileName);
        if (!Files.exists(filePath)) {
            Files.createFile(filePath);
        }
        try (OutputStream os = Files.newOutputStream(filePath)) {
            os.write(file.getBytes());
            usersRepository.updateUserPhoto(username,resultFileName);
        }
    }

    @Override
    public ResponseEntity<?> downloadImage(String filename, MediaType mediaType) {
        try {
            Path filePath = Paths.get(UPLOAD_DIR, filename);

            if (!Files.exists(filePath)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Image not found");
            }

            byte[] image = Files.readAllBytes(filePath);
            Resource resource = new ByteArrayResource(image);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                    .contentLength(resource.contentLength())
                    .contentType(mediaType)
                    .body(resource);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing the image");
        }
    }
}
