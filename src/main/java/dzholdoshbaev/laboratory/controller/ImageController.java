package dzholdoshbaev.laboratory.controller;

import dzholdoshbaev.laboratory.service.ImageService;
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
}
