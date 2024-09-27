package dzholdoshbaev.laboratory.repository;

import dzholdoshbaev.laboratory.model.Comments;
import dzholdoshbaev.laboratory.model.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentsRepository extends JpaRepository<Comments, Long> {
    
    List<Comments> findAllByPost(Posts postId);

    void deleteByPostId(Long postId);
}
