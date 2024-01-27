package pizza.models;

import jakarta.persistence.*;

@Entity
@Table(name = "bucket_item")
public class BucketItem {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "quantity")
    private int quantity;

    @ManyToOne()
    @JoinColumn(name = "bucket_id", referencedColumnName = "id")
    private Bucket bucket;

    @ManyToOne()
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;


    @Transient
    private Double itemPrice;

    public Double getItemPrice() {
        return quantity * productVariant.getProductVariantPrice();
    }

    public BucketItem(){

    }

    public BucketItem(int quantity, Bucket bucket, Product product, ProductVariant productVariant) {
        this.quantity = quantity;
        this.bucket = bucket;
        this.product = product;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Bucket getBucket() {
        return bucket;
    }

    public void setBucket(Bucket bucket) {
        this.bucket = bucket;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }


}
