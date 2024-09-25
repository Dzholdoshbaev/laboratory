package dzholdoshbaev.laboratory.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "authorities")
public class Authorities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "authority")
    private String authority;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "authorities")
    private List<Users> usersList;
}
