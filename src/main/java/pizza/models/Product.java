package pizza.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "products")
public class Product {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @NotNull
    @NotEmpty(message = "Название продукта не может быть пустым")
    @Size(max = 128, message = "Название может быть максимум 128 символов")
    private String productName;

    @Column(name = "description")
    @Size(max = 255, message = "Описание может быть максимум 255 символов")
    private String productDescription;

    @Column(name = "base_price")
    @NotNull
    @NotEmpty(message = "Цена продукта не может быть пустой")
    private double basePrice;

    @Column(name = "type")
    @Size(max = 40, message = "Тип может быть максимум 40 символов")
    private String type;


    @OneToOne(cascade = CascadeType.MERGE, orphanRemoval = true)
    @JoinColumn(name = "image_id")
    private Image image;

}

