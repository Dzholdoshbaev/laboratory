package dzholdoshbaev.laboratory.repository;


import dzholdoshbaev.laboratory.model.Posts;
import dzholdoshbaev.laboratory.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostsRepository extends JpaRepository<Posts, Long> {

    @Query("select p from Posts p where p.authorId.id = :authorId")
    List<Posts> findAllByAuthorId(Long authorId);
}
