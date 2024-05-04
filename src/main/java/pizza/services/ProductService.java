package pizza.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pizza.models.Image;
import pizza.models.Product;
import pizza.repositories.ImageRepository;
import pizza.repositories.ProductRepository;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ImageRepository imageRepository;


    public List<Product> listProduct() {
        return productRepository.findAll();
    }

    public void add(MultipartFile img, String productName, String productDescription, double basePrice, String type) throws IOException {
        Image image = new Image();
        image.setImageData(img.getBytes());
        imageRepository.save(image);
        Product product = new Product();
        product.setImage(image);
        product.setProductName(productName);
        product.setProductDescription(productDescription);
        product.setType(type);
        product.setBasePrice(basePrice);
        productRepository.save(product);
    }
}

