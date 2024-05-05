package pizza.services;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pizza.models.Comment;
import pizza.models.User;
import pizza.repositories.CommentRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public Object listComment() {
        return commentRepository.findAll();
    }

    public void createComment(String comment, User user) {
        Comment comment1 = new Comment();
        comment1.setComment(comment);
        comment1.setUser(user);
        commentRepository.save(comment1);
    }

    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
}
