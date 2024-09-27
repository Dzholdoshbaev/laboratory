package dzholdoshbaev.laboratory.controller;

import dzholdoshbaev.laboratory.service.ImageService;
import dzholdoshbaev.laboratory.service.PostImagesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

@Controller
@RequestMapping("/images")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;
    private final PostImagesService postImagesService;

    @PostMapping
    public String uploadAvatar(@RequestParam("file") MultipartFile file , Principal principal, Model model) {
        String username = principal.getName();
        if(file.isEmpty()) {
            return "redirect:/profile";
        }
        imageService.uploadImage(file,username);
        return "redirect:/profile";
    }


    @GetMapping("/{title}")
    public ResponseEntity<?> download(@PathVariable String title) {
        return imageService.downloadImage(title, MediaType.IMAGE_JPEG);
    }


    @PostMapping("/post")
    public String uploadImages(@RequestParam("file") MultipartFile file ,@RequestParam("description") String description, Principal principal, Model model) {
        String username = principal.getName();
        if(file.isEmpty() || description.isEmpty()) {
            return "redirect:/profile";
        }
        postImagesService.uploadImage(file,username,description);
        return "redirect:/profile";
    }


    @GetMapping("/post/{title}")
    public ResponseEntity<?> downloadImages(@PathVariable String title) {
        return postImagesService.downloadImage(title, MediaType.IMAGE_JPEG);
    }




}
