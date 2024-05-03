package pizza.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import pizza.services.UserService;

import java.io.IOException;
import java.security.Principal;
@Controller
@RequiredArgsConstructor
public class EditProfile {

    private final UserService userService;


    @PostMapping("/editProfile")
    public String profileEdit( Principal principal,
                               @RequestParam("image") MultipartFile img,
                               @RequestParam("lastName") String lastName,
                               @RequestParam("firstName") String firstName,
                               @RequestParam("phone") String phone,
                               @RequestParam("login") String login,
                               @RequestParam("password") String password) throws IOException {
        userService.editUser(principal,img, lastName, firstName, phone, login, password);
        return "redirect:/profile";
    }

}
