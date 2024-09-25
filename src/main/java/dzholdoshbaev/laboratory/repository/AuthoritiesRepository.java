package dzholdoshbaev.laboratory.repository;


import dzholdoshbaev.laboratory.model.Authorities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthoritiesRepository extends JpaRepository<Authorities, Long> {
    Authorities findByAuthority(String authority);
}
