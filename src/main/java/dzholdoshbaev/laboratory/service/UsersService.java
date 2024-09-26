package dzholdoshbaev.laboratory.service;



import dzholdoshbaev.laboratory.dto.UsersDto;
import dzholdoshbaev.laboratory.model.Users;
import jakarta.servlet.http.HttpServletRequest;


import java.util.List;
import java.util.Map;
import java.util.Optional;


public interface UsersService {
    void createUser(UsersDto users );

    Map<String, Object> resetPasswordGet(String token);

    Map<String, Object> resetPasswordPost(HttpServletRequest request);

    Optional<Users> getUserById(Long id);

    List<Users> getAllUsers();

    Users getUserByEmail(String email);

    void editResume(UsersDto usersDto ,String userEmail);

    Map<String, Object> forgotPassword(HttpServletRequest request);

    List<Users> searchUsers(String search);
}
