package dzholdoshbaev.laboratory.repository;


import dzholdoshbaev.laboratory.model.Followers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowersRepository extends JpaRepository<Followers, Long> {
}
