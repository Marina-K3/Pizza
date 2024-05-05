package pizza.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pizza.models.Bucket;

public interface BucketRepository extends JpaRepository<Bucket, Long> {
}
