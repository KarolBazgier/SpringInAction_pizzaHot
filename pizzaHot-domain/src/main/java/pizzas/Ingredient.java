package pizzas;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.*;


@NoArgsConstructor(force = true)
@Data
@Getter
@Setter
@Entity
@ToString
public class Ingredient {
    @Id
    private  String id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Type type;
    public static enum Type{
       DOUGH, VEGETABLES, MEAT, SAUCE
    }
}