package pizza.services;
import org.springframework.web.multipart.MultipartFile;
import pizza.models.*;
import pizza.repositories.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;


@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    private final ImageRepository imageRepository;

    private final ProductRepository productRepository;

    private  final ProductItemRepository productItemRepository;

    private final BucketRepository bucketRepository;

    private final PasswordEncoder passwordEncoder;

    private final PointRepository pointRepository;

    private final BucketService bucketService;

    private final PromotionRepository promotionRepository;

    private final OrderRepository orderRepository;

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

    public void addProductInBucket(Long idProduct, Principal principal) {
        Product product = productRepository.getById(idProduct);
        if (product == null) {
            // Обработка ошибки: продукт не существует
            return;
        }
        ProductItems productItem = new ProductItems();
        productItem.setProduct(product);
        productItem.setQuantity(1); // Установите желаемое количество
        productItem.setSize("S"); // Установите желаемый размер
        productItem.setItemPrice(product.getBasePrice()); // Установите цену товара

        User user = userRepository.findByLogin(principal.getName());
        Bucket bucket = user.getBucket(); // Получите корзину пользователя из объекта User
        if (bucket == null) {
            bucket = new Bucket();
            bucket.setProductItems(new ArrayList<>());
            user.setBucket(bucket);

        }

        bucket.getProductItems().add(productItem);
        bucket.setBucketPrice(bucketService.calculateBucketTotal(bucket));
        user.setBucket(bucket);
        productItem.setBucket(bucket);
        productItemRepository.save(productItem);
        bucketRepository.save(bucket);
        userRepository.save(user);
    }

    public double getPromo(Principal principal, int points, String promo) {
        double personalPrice = 0.0;
        User user = userRepository.findByLogin(principal.getName());
        personalPrice = user.getBucket().getBucketPrice();

        // Применяем правило 1 - снимаем все баллы клиента, если введенные баллы больше
        if (points > user.getPoints().getQuantity()) {
            points = user.getPoints().getQuantity();
        }
        personalPrice -= points;
        user.getPoints().setQuantity(user.getPoints().getQuantity() - points);

        // Применяем правило 2 - ищем акцию с соответствующим промокодом и проверяем ее активность и наличие пользователя
        Promotion promotion = promotionRepository.findByPromocode(promo);
            for(Promotion p : user.getPromotions()) {
                if (promotion != null && promotion.isActive() && p.getId() == promotion.getId()) {
                    personalPrice -= (personalPrice * promotion.getDiscount() / 100);

                }
            }


        userRepository.save(user);
        return personalPrice;
    }

    public void createOrder(Principal principal, double price) {

        User user = userRepository.findByLogin(principal.getName());
        Bucket bucket = user.getBucket(); // Получите корзину пользователя из объекта User
        if (bucket == null) {
            bucket = new Bucket();
            bucket.setProductItems(new ArrayList<>());
            user.setBucket(bucket);
            return;
        }

        List<ProductItems> itemsForDeleting = bucket.getProductItems();

        Order order = new Order();

// Округление цены до 3 знаков после запятой
        double roundedPrice = BigDecimal.valueOf(price)
                .setScale(3, RoundingMode.HALF_UP)
                .doubleValue();
        order.setOrderPrice(roundedPrice);

        order.setProductItems(itemsForDeleting);

        order.setCreatedTime(LocalDateTime.now());
        order.setReady(false);
        order.setCancelled(false);

        bucket.setProductItems(new ArrayList<>());
        bucket.setBucketPrice(bucketService.calculateBucketTotal(bucket));
        user.setBucket(bucket);

        user.getPoints().setQuantity(user.getPoints().getQuantity()+1);

        bucketRepository.save(bucket);
        userRepository.save(user);
        order.setUser(user);
        orderRepository.save(order);

        for(ProductItems productItem :  itemsForDeleting) {
            productItem.setBucket(null);
            productItem.setOrder(order);
            productItemRepository.save(productItem);
        }



    }
}
