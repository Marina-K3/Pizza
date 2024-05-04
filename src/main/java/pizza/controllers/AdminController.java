package pizza.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import pizza.models.User;
import pizza.services.ProductService;
import pizza.services.PromotionService;
import pizza.services.UserService;

import java.io.IOException;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;
    private final ProductService productService;
    private final PromotionService promoService;

    @GetMapping("/admin/menu")
    public String showUserProfile(Model model, Principal principal) {

        User user = userService.getUserByPrincipal(principal);

        model.addAttribute("admin", user);
        model.addAttribute("products", productService.listProduct());

        return "admin-menu";

    }


    @PostMapping("/admin/addProduct")
    public String addProduct(
                               @RequestParam("image") MultipartFile img,
                               @RequestParam("productName") String productName,
                               @RequestParam("productDescription") String productDescription,
                               @RequestParam("basePrice") double basePrice,
                               @RequestParam("type") String type) throws IOException {
        productService.add(img, productName, productDescription, basePrice, type);
        return "redirect:/admin/menu";
    }

    @GetMapping("/admin/deleteProduct/{id}")
    public String deleteProduct(@PathVariable("id") Long id)
    {

        productService.deleteProductById(id);
        return "redirect:/admin/menu";
    }

    @GetMapping("/admin/promos")
    public String promos(Model model, Principal principal) {

        User user = userService.getUserByPrincipal(principal);

        model.addAttribute("admin", user);
        model.addAttribute("promos", promoService.listPromos());

        return "admin-promos";

    }


    @PostMapping("/admin/addPromo")
    public String addPromo(
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("promocode") String promocode,
            @RequestParam("min_points") int min,
            @RequestParam("max_points") int max,
            @RequestParam("discount") int discount) {

        promoService.createPromotion(name, description, promocode, min, max, discount);
        return "redirect:/admin/promos";
    }

    @GetMapping("/admin/deletePromo/{id}")
    public String deletePromo(@PathVariable("id") Long id)
    {

        promoService.deletePromotion(id);
        return "redirect:/admin/promos";
    }
    @GetMapping("/admin/deactivatePromo/{id}")
    public String deactivatePromo(@PathVariable("id") Long id)
    {

        promoService.deactivatePromotion(id);
        return "redirect:/admin/promos";
    }

    @GetMapping("/admin/activatePromo/{id}")
    public String activatePromo(@PathVariable("id") Long id)
    {

        promoService.activatePromotion(id);
        return "redirect:/admin/promos";
    }

}
