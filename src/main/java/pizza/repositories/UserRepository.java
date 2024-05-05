package pizza.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import pizza.models.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByLogin(String login);
    User getUserById(Long id);
    boolean existsByLogin(String login);
    List<User> findByPointsQuantityBetween(int min, int max);
//    List<User> findUsersByPromotionId(long id);

}