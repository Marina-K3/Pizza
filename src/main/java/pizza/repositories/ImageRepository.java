package pizza.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pizza.models.Image;

public interface ImageRepository extends JpaRepository<Image,Long> {
}
