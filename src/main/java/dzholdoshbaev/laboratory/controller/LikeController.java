package dzholdoshbaev.laboratory.controller;

import dzholdoshbaev.laboratory.model.Users;
import dzholdoshbaev.laboratory.service.LikeService;
import dzholdoshbaev.laboratory.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@Controller
@RequestMapping("/like")
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;
    private final UsersService usersService;

   @GetMapping("/{postId}")
    public String like(@PathVariable Long postId, Model model, Principal principal) {
       String username = principal.getName();
       Users user = usersService.getUserByEmail(username);
       likeService.addLike(user, postId);
       return "redirect:/";
   }



}
