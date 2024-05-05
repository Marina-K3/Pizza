package pizza.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pizza.models.User;
import pizza.services.CommentService;
import pizza.services.ProductService;
import pizza.services.PromotionService;
import pizza.services.UserService;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final ProductService productService;
    private final PromotionService promoService;
    private final CommentService commentService;


    @GetMapping("/user/comments")
    public  String adminComments(Model model, Principal principal){
        User user = userService.getUserByPrincipal(principal);
        model.addAttribute("user", user);
        model.addAttribute("comments", commentService.listComment());
        return "user-comments";
    }

    @PostMapping("/user/addComment")
    public String addComment(
            @RequestParam("comment") String comment, Principal principal){
        User user = userService.getUserByPrincipal(principal);
        commentService.createComment(comment, user);
        return "redirect:/user/comments";
    }

    @GetMapping("/user/deleteComment/{id}")
    public String deleteComment(@PathVariable("id") Long id)
    {
        commentService.deleteComment(id);
        return "redirect:/user/comments";
    }
}
