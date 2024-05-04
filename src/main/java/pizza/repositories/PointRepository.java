package pizza.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import pizza.models.Image;
import pizza.models.Point;

public interface PointRepository extends JpaRepository<Point,Long> {
}
