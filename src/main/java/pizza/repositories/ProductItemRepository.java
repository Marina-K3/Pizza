package pizza.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pizza.models.ProductItems;

public interface ProductItemRepository extends JpaRepository<ProductItems, Long> {
}
