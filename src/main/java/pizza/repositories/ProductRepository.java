package pizza.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pizza.models.Product;

public interface ProductRepository extends JpaRepository<Product,Long> {

}
