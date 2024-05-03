package pizza.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pizza.models.Image;
import pizza.services.ImageService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class ImageController {


    private final ImageService imageService;

    @GetMapping("/image/{id}")
    public void getImage(@PathVariable Long id, HttpServletResponse response) throws IOException {
        Image image = imageService.getImageById(id);
        if (image != null && image.getImageData() != null) {
            response.setContentType("image/jpeg"); // Установите правильный тип содержимого для вашего изображения
            response.getOutputStream().write(image.getImageData());
        }
    }
}
