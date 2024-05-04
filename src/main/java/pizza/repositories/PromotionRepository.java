package pizza.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import pizza.models.Promotion;

public interface PromotionRepository extends JpaRepository<Promotion, Long> {

}
