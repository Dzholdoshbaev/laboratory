package dzholdoshbaev.laboratory.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "posts")
public class Posts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Users authorId;
    @Column(name = "image")
    private String image;
    @Column(name = "description")
    private String description;
    @Column(name = "likes")
    private Long likes;
    @Column(name = "comments")
    private Long comments;
    @Column(name = "created")
    private LocalDateTime createdAt;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "post")
    private List<Comments> commentsList;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "post")
    private List<Likes> likesList;
}
