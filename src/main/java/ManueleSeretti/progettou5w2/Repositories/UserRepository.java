package ManueleSeretti.progettou5w2.Repositories;

import ManueleSeretti.progettou5w2.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String s);

    Optional<Object> findByUsername(String username);
}
