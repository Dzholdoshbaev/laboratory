package dzholdoshbaev.laboratory.service.impl;



import dzholdoshbaev.laboratory.common.Utilities;
import dzholdoshbaev.laboratory.constants.Authority;
import dzholdoshbaev.laboratory.dto.UsersDto;
import dzholdoshbaev.laboratory.model.Authorities;
import dzholdoshbaev.laboratory.model.Posts;
import dzholdoshbaev.laboratory.model.Users;
import dzholdoshbaev.laboratory.repository.AuthoritiesRepository;
import dzholdoshbaev.laboratory.repository.PostsRepository;
import dzholdoshbaev.laboratory.repository.UsersRepository;
import dzholdoshbaev.laboratory.service.UsersService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.regex.Pattern;

@Slf4j
@RequiredArgsConstructor
@Service
public class UsersServiceImpl implements UsersService {
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final AuthoritiesRepository authoritiesRepository;
    private final EmailService emailService;
    private final PostsRepository postsRepository;

    @Override
    public void createUser(UsersDto usersDto) {

        Authorities authority = authoritiesRepository.findByAuthority(Authority.USER.getAuthority());

        Users user = Users.builder()
                .bio(usersDto.getBio())
                .name(usersDto.getName())
                .login(usersDto.getLogin())
                .email(usersDto.getEmail())
                .password(passwordEncoder.encode(usersDto.getPassword()))
                .authorities(authority)
                .enabled(true)
                .build();

        usersRepository.save(user);
        log.info("Created user: {}", user.getEmail());
    }

    @Override
    public Optional<Users> getUserById(Long id){
        return usersRepository.findById(id);
    }

    @Override
    public List<Users> getAllUsers() {
        var list = usersRepository.findAll();
        log.info("Printed all users");
        return list;
    }

    @Override
    public Users getUserByEmail(String email){
        log.info("Printed user by email");
        List<Users> users = usersRepository.findAll();
        Users user = users.get(0);
        for (Users user1 : users) {
            if (user1.getEmail().equals(email)) {
                user = user1;
            }
        }
        return user;
    }

    @Override
    public void editResume(UsersDto usersDto , String userEmail) {

        usersRepository.updateUsersByEmail(userEmail,
                usersDto.getEmail(),
                usersDto.getLogin(),
                passwordEncoder.encode(usersDto.getPassword()),
                usersDto.getName(),
                usersDto.getBio());

        log.info("Edited user");
    }

    @Override
    public Map<String, Object> forgotPassword(HttpServletRequest request) {
        Map<String, Object> model = new HashMap<>();
        try {
            makeResetPasswordLink(request);
            model.put("message", "we have sent reset password link to your email. Please check.");
        } catch (UsernameNotFoundException | UnsupportedEncodingException e) {
            model.put("error", e.getMessage());
        } catch (MessagingException e) {
            model.put("error", "Error while sending reset password link to your email.");
        }
        return model;
    }

    @Override
    public List<Users> searchUsers(String keyword) {
        return usersRepository.findByLoginContainingOrNameContainingOrEmailContaining(keyword, keyword, keyword);
    }

    @Override
    public List<Posts> getAllUsersPosts(Users  users) {
        return postsRepository.findAllByAuthorId(users.getId());
    }

    @Override
    public Map<String, Object> resetPasswordGet(String token) {
        Map<String, Object> model = new HashMap<>();
        try {
            getByToken(token);
            model.put("token", token);
        } catch (UsernameNotFoundException e) {
            model.put("error", "Invalid token");
        }
        return model;
    }

    @Override
    public Map<String, Object> resetPasswordPost(HttpServletRequest request) {
        Map<String, Object> model = new HashMap<>();
        String token = request.getParameter("token");
        String password = request.getParameter("password");


        if (password == null || !Pattern.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{4,20}$", password)) {
            model.put("message", "Password should contain at least one uppercase letter, one number, and its length must be between 4 and 20 characters. Please try again.");
            return model;
        }

        try {
            Users user = getByToken(token);
            updatePassword(user, password);
            model.put("message", "You have successfully changed your password.");
        } catch (UsernameNotFoundException e) {
            model.put("message", "Invalid token");
        }
        return model;
    }

    private void makeResetPasswordLink(HttpServletRequest request) throws MessagingException, UnsupportedEncodingException {
        String email = request.getParameter("email");
        String token = UUID.randomUUID().toString();
        updateToken(token, email);

        String resetPasswordLink = Utilities.getSiteUrl(request) + "/auth/reset_password?token=" + token;
        emailService.sendMail(email, resetPasswordLink);
    }

    private Users getByToken(String token) {
        return usersRepository.findByResetPasswordToken(token)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    private void updatePassword(Users user, String password) {
        String encodedPassword = passwordEncoder.encode(password);
        user.setPassword(encodedPassword);
        user.setResetPasswordToken(null);
        usersRepository.save(user);
    }

    private void updateToken(String token, String email) {
        Users user = usersRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Could not find any user with the email: " + email));
        user.setResetPasswordToken(token);
        usersRepository.save(user);
    }
}