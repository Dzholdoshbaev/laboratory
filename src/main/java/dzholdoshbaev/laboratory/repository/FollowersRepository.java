package dzholdoshbaev.laboratory.repository;


import dzholdoshbaev.laboratory.model.Followers;
import dzholdoshbaev.laboratory.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FollowersRepository extends JpaRepository<Followers, Long> {

    @Query("select f.users from Followers f where f.follower = :users")
    List<Users> findAllByFollower(Users users);

    @Query("select f.follower from Followers f where f.users = :users")
    List<Users> findAllFollowersByUser(Users users);


    Optional<Followers> findByUsersAndFollower(Users user, Users follower);
}
