package pizza.services;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pizza.models.Promotion;
import pizza.models.User;
import pizza.repositories.PromotionRepository;
import pizza.repositories.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PromotionService {

    private final PromotionRepository promotionRepository;
    private final UserRepository userRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public void createPromotion(String name, String description, String promocode, int min, int max, int discount) {

        Promotion promotion = new Promotion();
        promotion.setName(name);
        promotion.setDescription(description);
        promotion.setPromocode(promocode);
        promotion.setMin_points(min);
        promotion.setMax_points(max);
        promotion.setDiscount(discount);
        promotion.setActive(true);

        List<User> eligibleUsers = userRepository.findByPointsQuantityBetween(min, max);
        promotion.setUsers(eligibleUsers);
        promotionRepository.save(promotion);

        for (User user : eligibleUsers) {
            user.getPromotions().add(promotion);
            userRepository.save(user); // Обновление пользователя в базе данных
        }

    }

    public List<Promotion> listPromos() {
        return promotionRepository.findAll();
    }

    public void deactivatePromotion(Long promotionId) {
        Promotion promotion = promotionRepository.findById(promotionId).orElse(null);
        if (promotion != null) {
            promotion.setActive(false);
            promotionRepository.save(promotion);
        }
    }
    public void activatePromotion(Long promotionId) {
        Promotion promotion = promotionRepository.findById(promotionId).orElse(null);
        if (promotion != null) {
            promotion.setActive(true);
            promotionRepository.save(promotion);
        }
    }
    @Transactional
    public void deletePromotion(Long promotionId) {
        Promotion promotion = promotionRepository.findById(promotionId).orElse(null);
        if (promotion != null) {
            Query query = entityManager.createNativeQuery("DELETE FROM user_promotions WHERE promotion_id = :promotionId");
            query.setParameter("promotionId", promotionId);
            query.executeUpdate();

            promotionRepository.delete(promotion);
        }
            promotionRepository.delete(promotion);
    }

}
