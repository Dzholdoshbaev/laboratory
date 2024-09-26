package dzholdoshbaev.laboratory.controller;

import dzholdoshbaev.laboratory.model.Posts;
import dzholdoshbaev.laboratory.model.Users;
import dzholdoshbaev.laboratory.service.PostsService;
import dzholdoshbaev.laboratory.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class MainController {
    private final UsersService usersService;
    private final PostsService postsService;

    @GetMapping
    public String main(Model model , Principal principal) {

        Users users ;
        List<Posts> followListPosts;

        if (principal != null) {
            String username = principal.getName();
            users = usersService.getUserByEmail(username);
            followListPosts = postsService.findAllFollowingPosts(users);
        }else {
            followListPosts = new ArrayList<>();
        }
        model.addAttribute("followListPosts", followListPosts);

        return "main";
    }
}
