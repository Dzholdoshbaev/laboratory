package dzholdoshbaev.laboratory.controller;



import dzholdoshbaev.laboratory.dto.UsersDto;
import dzholdoshbaev.laboratory.model.Users;
import dzholdoshbaev.laboratory.service.AuthoritiesService;
import dzholdoshbaev.laboratory.service.UsersService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;



@Controller
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {
    private final UsersService usersService;
    private final AuthoritiesService authoritiesService;


    @GetMapping
    public String profile(Model model, Principal principal) {
        String username = principal.getName();
        Users user = usersService.getUserByEmail(username);
        model.addAttribute("userDto", user);
        return "auth/profile";
    }


    @PostMapping("/register")
    public String registerUser(
            @ModelAttribute @Valid UsersDto usersDto,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("authoritiesUser", authoritiesService.getAllAuthorities());
            return "auth/register";
        }

        usersService.createUser(usersDto);
        return "redirect:/auth/login";
    }


    @GetMapping("/register")
    public String create(Model model) {
        model.addAttribute("authoritiesUser", authoritiesService.getAllAuthorities());
        model.addAttribute("usersDto", new UsersDto());
        return "auth/register";
    }

    @PostMapping("/edit")
    public String editResume(@Valid UsersDto usersDto , BindingResult bindingResult, Principal principal , HttpServletRequest request, HttpServletResponse response , Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("authoritiesUser", authoritiesService.getAllAuthorities());
            return "auth/editUser";
        }

        String username = principal.getName();
        usersService.editResume(usersDto, username);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return "redirect:/auth/login";
    }

    @GetMapping("/edit")
    public String editResume(Model model) {
        model.addAttribute("usersDto", new UsersDto());
        return "auth/editUser";
    }
}
