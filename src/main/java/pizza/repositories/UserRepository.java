package pizza.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import pizza.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByLogin(String login);
    User getUserById(Long id);
    boolean existsByLogin(String login);

}