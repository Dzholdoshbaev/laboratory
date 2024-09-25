package dzholdoshbaev.laboratory.dto;

import dzholdoshbaev.laboratory.annotations.UniqueEmail;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsersDto {
    private Long id;
    @NotBlank(message = "Should be not blank")
    private String name;
    @NotBlank(message = "Should be not blank")
    private String bio;
    @NotBlank(message = "Should be not blank")
    private String login;
    @UniqueEmail
    @NotBlank(message = "Should be not blank")
    @Email
    private String email;
    @NotBlank(message = "Should be not blank")
    @Size(min = 4, max = 20, message = "Length must be >= 4 and <= 20")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).+$", message = "Should contain at least one uppercase letter, one number")
    private String password;
    private String avatar;
    private Boolean enabled;
    private Long authorityId;
}
