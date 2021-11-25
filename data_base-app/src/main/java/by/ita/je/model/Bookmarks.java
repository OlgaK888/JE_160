package by.ita.je.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Bookmarks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ToString.Exclude
    @ManyToMany(cascade ={CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH},
            fetch = FetchType.LAZY)
    @JoinTable(name = "bookmarks_product",
            joinColumns = @JoinColumn(name = "bookmarks_id")
            , inverseJoinColumns = @JoinColumn(name = "product_id"))
    private Collection<Product> products;

    @OneToOne
    @JsonIgnore
    private Account account;
}
