package pizzas;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Data
@Entity
@Table(name= "Pizza_Order")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date placedAt;

    @NotEmpty(message = "Imię i nazwisko obowiązkowe!")
    private String name;

    @NotEmpty(message = "Ulica obowiązkowa!")
    private String street;

    @NotEmpty(message = "Miasto obowiązkowe!")
    private String city;

    @NotEmpty(message = "Kod pocztowy obowiązkowy!")
    private String zip;

    @CreditCardNumber(message = "Nr. karty niepoprawny!")
    private String cardNumber;

    @Pattern(regexp = "^(0[1-9]|1[0-2])/\\d{2}$", message = "Nieprawidłowy format daty ważności karty (MM/RR)!")
    private String cardExpiration;


    @Length(min = 3, max = 3, message = "CVV ma miec 3 cyfry")
    private String cardCVV;

    @ManyToMany(targetEntity = Pizza.class)
    private List<Pizza> pizzas = new ArrayList<>();

    @ManyToOne
    private User user;

    public void addDesign(Pizza design){
        pizzas.add(design);
    }

    @PrePersist
    void placedAt(){
        this.placedAt = new Date();
    }
}
