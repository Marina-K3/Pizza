package pizza.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import pizza.models.User;
import pizza.services.UserService;

import java.io.IOException;
import java.security.Principal;


@Controller
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserService userService;


    // общие (авторизация и регистрация(пользователя))
    @GetMapping("/log")
    public String login() {
        return "log";
    }



    @GetMapping("/reg")
    public String registration(Principal principal, Model model) {
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "reg";
    }

    @PostMapping("/reg")
    public String createUser(@RequestParam("image") MultipartFile img,
                             @RequestParam("lastName") String lastName,
                             @RequestParam("firstName") String firstName,
                             @RequestParam("phone") String phone,
                             @RequestParam("login") String login,
                             @RequestParam("password") String password) throws IOException {
        if (!userService.createUser(img, lastName, firstName, phone, login, password)) {
            return "reg";
        }
        return "redirect:/log";
    }


    @GetMapping("/profile")
    public String showProfile(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            if (authentication.getAuthorities().stream()
                    .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"))) {
                return "redirect:/admin/profile";
            } else if (authentication.getAuthorities().stream()
                    .anyMatch(auth -> auth.getAuthority().equals("ROLE_USER"))) {
                return "redirect:/user/profile";
            }
        }
        return "redirect:/log";
    }

    @GetMapping("/admin/profile")
    public String showAdminProfile(Model model, Principal principal) {
        User user = userService.getUserByPrincipal(principal);
        if(user.isActive()){
            model.addAttribute("admin", user);
            return "admin";
        }
        else{
            return "redirect:/";
        }
    }


    @GetMapping("/user/profile")
    public String showUserProfile(Model model, Principal principal) {

        User user = userService.getUserByPrincipal(principal);
        if(user.isActive()){
            model.addAttribute("user", user);
            return "user";
        }
        else{
            return "redirect:/";
        }

    }

}
