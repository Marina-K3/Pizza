package pizza.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import pizza.models.User;
import pizza.services.ProductService;
import pizza.services.UserService;

import java.io.IOException;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;
    private final ProductService productService;

    @GetMapping("/admin/menu")
    public String showUserProfile(Model model, Principal principal) {

        User user = userService.getUserByPrincipal(principal);

        model.addAttribute("admin", user);
        model.addAttribute("products", productService.listProduct());

        return "admin-menu";

    }


    @PostMapping("/admin/addProduct")
    public String profileEdit(
                               @RequestParam("image") MultipartFile img,
                               @RequestParam("productName") String productName,
                               @RequestParam("productDescription") String productDescription,
                               @RequestParam("basePrice") double basePrice,
                               @RequestParam("type") String type) throws IOException {
        productService.add(img, productName, productDescription, basePrice, type);
        return "redirect:/admin/menu";
    }


}
