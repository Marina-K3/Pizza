package pizza.models;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "promotions")
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private int discount;

    private String promocode;

    private int min_points;

    private int max_points;

    private boolean active;

    @ManyToMany(mappedBy = "promotions")
    private List<User> users;

}
