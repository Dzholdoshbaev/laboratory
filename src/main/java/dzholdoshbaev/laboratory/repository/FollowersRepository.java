package dzholdoshbaev.laboratory.repository;


import dzholdoshbaev.laboratory.model.Followers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowersRepository extends JpaRepository<Followers, Long> {
}
