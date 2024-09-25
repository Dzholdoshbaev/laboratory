package dzholdoshbaev.laboratory.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "likes")
public class Likes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Posts post;
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;
    @Column(name = "liked_at")
    private LocalDateTime likedAt;
}
