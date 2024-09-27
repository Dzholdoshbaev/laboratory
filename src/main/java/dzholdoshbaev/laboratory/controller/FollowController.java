package dzholdoshbaev.laboratory.controller;

import dzholdoshbaev.laboratory.model.Posts;
import dzholdoshbaev.laboratory.model.Users;
import dzholdoshbaev.laboratory.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Controller
@RequestMapping("/follow")
@RequiredArgsConstructor
public class FollowController {
    private final UsersService usersService;


    @GetMapping("/{userId}")
    public String follow(@PathVariable Long userId, RedirectAttributes redirectAttributes, Principal principal) {
        Users user = usersService.getUserByEmail(principal.getName());
        Users followUser = usersService.getUserById(userId)
                .orElseThrow(() -> new NoSuchElementException("No such user found"));

        boolean isFollow = usersService.checkFollow(user, followUser);

        if (!isFollow) {
            usersService.createFollowing(user, followUser);
            redirectAttributes.addFlashAttribute("message", "Successfully followed user");
        }

        return "redirect:/profile/" + userId;
    }
}
