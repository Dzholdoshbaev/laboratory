package dzholdoshbaev.laboratory.repository;


import dzholdoshbaev.laboratory.model.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Posts, Long> {
}
