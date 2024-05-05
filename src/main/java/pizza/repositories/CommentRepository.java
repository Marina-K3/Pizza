package pizza.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pizza.models.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
