package dzholdoshbaev.laboratory.config;

import dzholdoshbaev.laboratory.constants.Authority;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        if (authentication.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals(Authority.USER.getAuthority()))) {
            response.sendRedirect("/profile");
        } else {
            response.sendRedirect("/auth/login");
        }
    }
}
