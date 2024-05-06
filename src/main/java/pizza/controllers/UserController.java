package pizza.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import pizza.models.ProductItems;
import pizza.models.User;
import pizza.services.*;

import java.io.IOException;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final ProductService productService;
    private final PromotionService promoService;
    private final ProductItemService productItemService;
    private final CommentService commentService;
    private final OrderService orderService;


    @GetMapping("/user/comments")
    public  String userComments(Model model, Principal principal){
        User user = userService.getUserByPrincipal(principal);
        model.addAttribute("user", user);
        model.addAttribute("comments", commentService.listComment());
        return "user-comments";
    }

    @PostMapping("/user/addComment")
    public String userAddComment(
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

    @GetMapping("/user/menu")
    public String showUserMenu(Model model, Principal principal) {

        User user = userService.getUserByPrincipal(principal);

        model.addAttribute("user", user);
        model.addAttribute("products", productService.listProduct());

        return "user-menu";

    }

    @GetMapping("/user/orders")
    public String showUserOrder(Model model, Principal principal) {

        User user = userService.getUserByPrincipal(principal);

        model.addAttribute("user", user);
        model.addAttribute("orders", orderService.getByUser(user));
        model.addAttribute("personalPrice", user.getBucket().getBucketPrice());
        return "user-order";

    }

    @PostMapping("/user/GetPromo")
    public String showUserOrderWithPromo(Model model, Principal principal,
                                         @RequestParam("points") int points,
                                         @RequestParam("promo") String promo) {

        User user = userService.getUserByPrincipal(principal);

        model.addAttribute("orders", orderService.getByUser(user));
        double personalPrice = userService.getPromo(principal, points, promo);
        model.addAttribute("personalPrice", personalPrice);
        model.addAttribute("user", user);
        return "user-order-promo";

    }

    @GetMapping("/user/createOrder/{price}")
    public String createOrder(Model model, Principal principal, @PathVariable("price") String priceStr) {

        double price = Double.parseDouble(priceStr);

        userService.createOrder(principal, price);
        User user = userService.getUserByPrincipal(principal);
        model.addAttribute("user", user);
        model.addAttribute("orders", orderService.getByUser(user));
        model.addAttribute("personalPrice", user.getBucket().getBucketPrice());
        return "user-order";

    }


    @GetMapping("/user/newSizeS/{id}")
    public String sizeS(Model model, Principal principal, @PathVariable("id") Long id) {

        productItemService.setSize(id, "S", principal);
        User user = userService.getUserByPrincipal(principal);
        model.addAttribute("user", user);
        model.addAttribute("orders", orderService.getByUser(user));
        model.addAttribute("personalPrice", user.getBucket().getBucketPrice());

        return "user-order";

    }


    @GetMapping("/user/newSizeM/{id}")
    public String sizeM(Model model, Principal principal, @PathVariable("id") Long id) {

        productItemService.setSize(id, "M", principal);
        User user = userService.getUserByPrincipal(principal);

        model.addAttribute("user", user);
        model.addAttribute("orders", orderService.getByUser(user));
        model.addAttribute("personalPrice", user.getBucket().getBucketPrice());

        return "user-order";

    }

    @GetMapping("/user/newSizeL/{id}")
    public String sizeL(Model model, Principal principal, @PathVariable("id") Long id) {

        productItemService.setSize(id, "L", principal);
        User user = userService.getUserByPrincipal(principal);
        model.addAttribute("user", user);
        model.addAttribute("orders", orderService.getByUser(user));
        model.addAttribute("personalPrice", user.getBucket().getBucketPrice());

        return "user-order";

    }
    @GetMapping("/user/addItem/{id}")
    public String addItem(Model model, Principal principal, @PathVariable("id") Long id) {

        productItemService.addItem(id, principal);
        User user = userService.getUserByPrincipal(principal);
        model.addAttribute("user", user);
        model.addAttribute("orders", orderService.getByUser(user));
        model.addAttribute("personalPrice", user.getBucket().getBucketPrice());

        return "user-order";

    }
    @GetMapping("/user/deleteItem/{id}")
    public String deleteItem(Model model, Principal principal, @PathVariable("id") Long id) {

        productItemService.deleteItem(id,  principal);
        User user = userService.getUserByPrincipal(principal);
        model.addAttribute("user", user);
        model.addAttribute("orders", orderService.getByUser(user));
        model.addAttribute("personalPrice", user.getBucket().getBucketPrice());

        return "user-order";

    }


    @GetMapping("/user/addProductInBucket/{id}")
    public String addProductInBucket(@PathVariable("id") Long id, Principal principal) throws IOException {
        userService.addProductInBucket(id, principal);
        return "redirect:/user/menu";
    }
}
