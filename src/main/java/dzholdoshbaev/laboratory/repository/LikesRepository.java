package dzholdoshbaev.laboratory.repository;

import dzholdoshbaev.laboratory.model.Likes;
import dzholdoshbaev.laboratory.model.Posts;
import dzholdoshbaev.laboratory.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LikesRepository extends JpaRepository<Likes, Long> {

    @Query("select l from Likes l where l.users = :users and l.post = :posts")
     Likes findByUsersAndPosts(Users users, Posts posts);
}
