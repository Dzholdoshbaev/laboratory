package dzholdoshbaev.laboratory.service.impl;

import dzholdoshbaev.laboratory.model.Posts;
import dzholdoshbaev.laboratory.model.Users;
import dzholdoshbaev.laboratory.repository.PostsRepository;
import dzholdoshbaev.laboratory.repository.UsersRepository;
import dzholdoshbaev.laboratory.service.PostImagesService;
import dzholdoshbaev.laboratory.service.UsersService;
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
import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class PostImagesServiceImpl implements PostImagesService {
    private final UsersRepository usersRepository;
    private final PostsRepository postsRepository;

    static String UPLOAD_DIR = "data/posts/";

    @SneakyThrows
    public void uploadImage(MultipartFile file, String username , String description) {
        String uuidFile = UUID.randomUUID().toString();
        String resultFileName = uuidFile + "_" + file.getOriginalFilename();

        Path pathDir = Paths.get(UPLOAD_DIR);
        Files.createDirectories(pathDir);
        Optional<Users> user = Optional.of(usersRepository.findByEmail(username).orElseThrow(NoSuchElementException::new));

        Path filePath = Paths.get(pathDir + "/" + resultFileName);
        if (!Files.exists(filePath)) {
            Files.createFile(filePath);
        }
        try (OutputStream os = Files.newOutputStream(filePath)) {
            os.write(file.getBytes());

            Posts post = new Posts();
            post.setImage(resultFileName);
            post.setDescription(description);
            post.setAuthorId(user.get());
            post.setComments(0L);
            post.setLikes(0L);
            post.setCreatedAt(LocalDateTime.now());
            postsRepository.save(post);
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
