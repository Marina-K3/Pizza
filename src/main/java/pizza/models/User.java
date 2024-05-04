package pizza.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "first_name")
    private String firstName;

    private String phone;

    private String login;

    @Column(name = "is_active")
    private boolean isActive;

    private String password;

    private String role;

    //одностор связь
    @OneToOne(cascade = CascadeType.REFRESH, orphanRemoval = true)
    @JoinColumn(name = "image_id")
    private Image image;

    //одностор связь
    @OneToOne(cascade = CascadeType.MERGE, orphanRemoval = true)
    @JoinColumn(name = "bucket_id")
    private Bucket bucket;

    //одностор связь
    @OneToOne(cascade = CascadeType.MERGE, orphanRemoval = true)
    @JoinColumn(name = "point_id")
    private Point points;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.ALL})
    @JoinTable(
            name = "user_promotions",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "promotion_id")
    )
    private List<Promotion> promotions;



    public void removePromotion(Promotion promotion) {
        promotions.remove(promotion);
        promotion.getUsers().remove(this);
    }
}

