package pizza.services;
import org.springframework.web.multipart.MultipartFile;
import pizza.models.Image;
import pizza.models.Point;
import pizza.models.User;
import pizza.repositories.ImageRepository;
import pizza.repositories.PointRepository;
import pizza.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.Principal;
import java.util.List;


@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    private final ImageRepository imageRepository;

    private final PasswordEncoder passwordEncoder;

    private final PointRepository pointRepository;

    public boolean createUser(MultipartFile img, String lastName, String firstName, String phone, String login, String password) throws IOException {
        if (userRepository.findByLogin(login) != null) return false;
        User user = new User();
        user.setActive(true);
        user.setRole("USER");
        user.setPassword(passwordEncoder.encode(password));
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPhone(phone);
        user.setLogin(login);
        Point point = new Point();
        point.setQuantity(0);
        pointRepository.save(point);
        user.setPoints(point);
        Image image = new Image();
        image.setImageData(img.getBytes());
        imageRepository.save(image);
        user.setImage(image);
        userRepository.save(user);
        return true;
    }


    public List<User> list(){
        return userRepository.findAll();
    }

    public void banUser(Long id) {
        User user = userRepository.getUserById(id);
        if(user!=null){
            if(user.isActive()){
                user.setActive(false);
                log.info("Ban user with id = {}; email: {}",user.getId(),user.getLogin());
            } else{
                user.setActive(true);
                log.info("Unban user with id = {}; email: {}",user.getId(),user.getLogin());
            }
        }
        userRepository.save(user);
    }

    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return userRepository.findByLogin(principal.getName());
    }



    public boolean createAdmin(User user) {
        String userEmail = user.getLogin();
        if (userRepository.findByLogin(userEmail) != null) return false;
        user.setActive(true);
        user.setRole("ADMIN");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        log.info("Saving new User with email: {}", userEmail);
        userRepository.save(user);
        return true;
    }

    public boolean authenticateUser(String email, String password) {
        User user = userRepository.findByLogin(email);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return true;
        }
        return false;
    }

    public User getUserByEmail(String email) {
        if(userRepository.findByLogin(email)!=null)
            return userRepository.findByLogin(email);
        else return new User();
    }

    public void setRole(Long id) {

        User user = userRepository.getUserById(id);

        if(user.getRole().equals("USER")){
            user.setRole("ADMIN");
        }
        else{
            user.setRole("USER");
        }
        userRepository.save(user);
    }

    public User getUserByID(Long id) {
        if(userRepository.getUserById(id)!=null)
            return userRepository.getUserById(id);
        else return new User();
    }

    public void editUser(Principal principal, MultipartFile img, String lastName, String firstName, String phone, String login, String password) throws IOException {

        User user = userRepository.findByLogin(principal.getName());
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setLogin(login);
        user.setPhone(phone);
        user.setPassword(passwordEncoder.encode(password));
        Image image = new Image();
        image.setImageData(img.getBytes());
        imageRepository.save(image);
        user.setImage(image);
        userRepository.save(user);

    }

    public List<User> getUsersByIds(List<Long> userIds) {
        return userRepository.findAllById(userIds);
    }
}
