package pizza.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name = "prod_items")
@Getter
@Setter
@NoArgsConstructor
public class ProductItems {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //одностор связь
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "size")
    private String size; // S M L

    private Double itemPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bucket_id")
    private Bucket bucket;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    public Double calculatePrice() {
        double basePrice = this.product.getBasePrice();


        // Применяем коэффициент к itemPrice в зависимости от размера
        if (this.size.equals("S")) {
            basePrice *= 1.0;
        } else if (this.size.equals("M")) {
            basePrice *= 1.4;
        } else if (this.size.equals("L")) {
            basePrice *= 1.6;
        }

        double newPrice = basePrice * this.quantity;
        return newPrice;

    }
}
