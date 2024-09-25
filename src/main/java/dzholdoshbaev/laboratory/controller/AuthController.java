package dzholdoshbaev.laboratory.controller;

import dzholdoshbaev.laboratory.service.UsersService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {
    private final UsersService usersService;

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }


    @GetMapping("forgot_password")
    public String forgotPassword() {
        return "auth/forgot_password_form";
    }

    @PostMapping("forgot_password")
    public String forgotPassword(HttpServletRequest request, Model model) {
        model.addAllAttributes(usersService.forgotPassword(request));
        return "auth/forgot_password_form";
    }

    @GetMapping("reset_password")
    public String resetPassword(@RequestParam String token, Model model) {
        model.addAllAttributes(usersService.resetPasswordGet(token));
        return "auth/reset_password_form";
    }

    @PostMapping("reset_password")
    public String resetPassword(HttpServletRequest request, Model model) {
        model.addAllAttributes(usersService.resetPasswordPost(request));
        return "auth/message";
    }



}
