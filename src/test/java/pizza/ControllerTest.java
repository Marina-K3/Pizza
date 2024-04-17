package pizza;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import pizza.controllers.ShowController;
import pizza.dto.ProductDTO;
import pizza.dto.ShowDTO;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

@SpringBootTest
public class ControllerTest {

    private final ShowController showController;

    @Autowired
    public ControllerTest(ShowController showController) {
        this.showController = showController;
    }

    @Test
    public void testWrongFrom() throws JsonProcessingException {
//        String json = "{\"from\": \"Electronics\", \"count\": 2, \"sort\": \"id\"}";
//        ShowDTO showDTO = new ObjectMapper().readValue(json, ShowDTO.class);
//        showDTO.validateFrom(mock(Errors.class)); // Проверяем значение from
//        ResponseEntity<List<ProductDTO>> response = showController.showProducts(showDTO, null);
//        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}
