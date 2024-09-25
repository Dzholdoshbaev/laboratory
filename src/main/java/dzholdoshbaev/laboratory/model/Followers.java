package dzholdoshbaev.laboratory.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "followers")
public class Followers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "follower_id")
    private Users follower;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
