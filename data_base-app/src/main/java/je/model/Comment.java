package je.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String message;
    private long accountId;;

    @OneToOne
    private Product product;

}
