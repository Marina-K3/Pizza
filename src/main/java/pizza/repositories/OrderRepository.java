package pizza.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pizza.models.Order;
import pizza.models.User;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUser(User user);
}
