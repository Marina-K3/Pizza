package pizza.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pizza.models.Bucket;
import pizza.models.ProductItems;

@Service
@Slf4j
@RequiredArgsConstructor
public class BucketService {

    public double calculateBucketTotal(Bucket bucket) {
        double total = 0.0;

        for (ProductItems productItem : bucket.getProductItems()) {
            double itemPrice = productItem.getItemPrice();
            String size = productItem.getSize();

            // Применяем коэффициент в зависимости от размера
            if (size.equals("S")) {
                total += itemPrice * 1.0;
            } else if (size.equals("M")) {
                total += itemPrice * 1.4;
            } else if (size.equals("L")) {
                total += itemPrice * 1.6;
            }
        }

        return total;
    }
}
