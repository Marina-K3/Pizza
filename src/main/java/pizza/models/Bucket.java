package pizza.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "buckets")
@Getter
@Setter
@NoArgsConstructor
public class Bucket {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //одностор связь
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy = "bucket", orphanRemoval = true)
    private List<ProductItems> productItems;

    @Transient
    private Double bucketPrice;

}
