package dzholdoshbaev.laboratory.repository;

import dzholdoshbaev.laboratory.model.Likes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikesRepository extends JpaRepository<Likes, Long> {
}
