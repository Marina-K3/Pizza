package pizza.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pizza.models.Bucket;
import pizza.models.ProductItems;
import pizza.models.User;
import pizza.repositories.BucketRepository;
import pizza.repositories.ProductItemRepository;
import pizza.repositories.UserRepository;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductItemService {

    private final ProductItemRepository productItemRepository;
    private final UserRepository userRepository;
    private  final BucketService bucketService;
    private  final BucketRepository bucketRepository;


    public void setSize(Long id, String s, Principal principal) {
        ProductItems productItem = productItemRepository.getById(id);
        productItem.setSize(s); // Установите желаемый размер

        productItem.setItemPrice(productItem.calculatePrice()); // Обновляем itemPrice

        User user = userRepository.findByLogin(principal.getName());
        Bucket bucket = user.getBucket(); // Получите корзину пользователя из объекта User

        List<ProductItems> productItems = bucket.getProductItems();
        for (int i = 0; i < productItems.size(); i++) {
            if (productItems.get(i).getId().equals(id)) {
                productItems.set(i, productItem); // Заменяем объект ProductItems в списке
                break;
            }
        }
        bucket.setBucketPrice(bucketService.calculateBucketTotal(bucket));
        user.setBucket(bucket);
        productItem.setBucket(bucket);
        productItemRepository.save(productItem);
        bucketRepository.save(bucket);
        userRepository.save(user);
    }

    public void addItem(Long id, Principal principal) {
        ProductItems productItem = productItemRepository.getById(id);

        User user = userRepository.findByLogin(principal.getName());
        Bucket bucket = user.getBucket(); // Получите корзину пользователя из объекта User

        List<ProductItems> productItems = bucket.getProductItems();
        for (int i = 0; i < productItems.size(); i++) {
            if (productItems.get(i).getId().equals(id)) {
                ProductItems existingItem = productItems.get(i);
                if (existingItem.getQuantity() < 10) { // Проверяем, что количество не превышает 10
                    existingItem.setQuantity(existingItem.getQuantity() + 1); // Увеличиваем количество на 1
                    existingItem.setItemPrice(existingItem.calculatePrice()); // Рассчитываем новую цену товара
                }
                break;
            }
        }
        bucket.setBucketPrice(bucketService.calculateBucketTotal(bucket));
        user.setBucket(bucket);
        productItem.setBucket(bucket);
        productItemRepository.save(productItem);
        bucketRepository.save(bucket);
        userRepository.save(user);
    }
    public void deleteItem(Long id, Principal principal) {
        User user = userRepository.findByLogin(principal.getName());
        Bucket bucket = user.getBucket(); // Получите корзину пользователя из объекта User

        List<ProductItems> productItems = bucket.getProductItems();
        for (int i = 0; i < productItems.size(); i++) {
            if (productItems.get(i).getId().equals(id)) {
                ProductItems existingItem = productItems.get(i);
                if (existingItem.getQuantity() > 0) { // Проверяем, что количество больше 0
                    existingItem.setQuantity(existingItem.getQuantity() - 1); // Уменьшаем количество на 1
                    existingItem.setItemPrice(existingItem.calculatePrice()); // Рассчитываем новую цену товара
                    if (existingItem.getQuantity() == 0) { // Если количество достигло 0, удаляем productItem
                        productItems.remove(i);
                        bucket.getProductItems().remove(existingItem);
                        break;
//                        productItemRepository.delete(existingItem); // Удаляем productItem из репозитория
                    }
                }
                break;
            }
        }
        bucket.setBucketPrice(bucketService.calculateBucketTotal(bucket));
        user.setBucket(bucket);
        bucketRepository.save(bucket);
//        userRepository.save(user);
    }
}
