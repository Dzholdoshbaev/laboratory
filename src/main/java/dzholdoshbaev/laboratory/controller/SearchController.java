package dzholdoshbaev.laboratory.controller;

import dzholdoshbaev.laboratory.model.Users;
import dzholdoshbaev.laboratory.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/search")
@RequiredArgsConstructor
public class SearchController {
    private final UsersService usersService;

    @GetMapping
    public String search(@RequestParam(value = "search", required = false, defaultValue = "") String search, Model model) {
        List<Users> users;
        if (search != null && !search.isEmpty()) {
            users = usersService.searchUsers(search);
        } else {
            users = new ArrayList<>();
        }
        model.addAttribute("search", search);
        model.addAttribute("users", users);
        return "microgram/search";
    }

}
