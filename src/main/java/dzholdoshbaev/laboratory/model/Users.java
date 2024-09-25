package dzholdoshbaev.laboratory.model;

import jakarta.persistence.*;
import lombok.*;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "login")
    private String login;
    @Column(name = "name")
    private String name;
    @Column(name = "bio")
    private String bio;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "reset_password_token")
    private String resetPasswordToken;
    @Column(name = "avatar")
    private String avatar;
    @Column(name = "enabled")
    private Boolean enabled;
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "authority_id")
    private Authorities authorities;
}
