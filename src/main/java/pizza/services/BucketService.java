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
            int quantity = productItem.getQuantity();

                total += itemPrice;

        }

        return total;
    }
}
