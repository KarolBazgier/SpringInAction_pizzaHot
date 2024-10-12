package pl.edu.wszib.pizzahot.Model;

import jakarta.persistence.*;
import lombok.*;



import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Pizza {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date createdAt;

    @Length(min = 5, message = "Nazwa min.5 znaków")
    private String name;

    @NotEmpty(message = "Rodzaj ciasta jest wymagany.")
    private String dough;

    @Transient
    private List<Ingredient> vegetables;

    @Transient
    private List<Ingredient> meat;

    @Transient
    private List<Ingredient> sauce;

    @ManyToMany(targetEntity = Ingredient.class)
    @JoinTable(
            name = "pizza_ingredients",
            joinColumns = @JoinColumn(name = "pizza_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id")
    )
    private List<Ingredient> ingredients;

    @PrePersist
    protected void onCreate() {
        this.createdAt = new Date();
    }

    public void combineIngredients() {
        if (this.ingredients == null) {
            this.ingredients = new ArrayList<>();  // Inicjalizujemy listę ingredients, jeśli jest pusta
        }

        // Sprawdzamy i dodajemy składniki z poszczególnych list (jeśli nie są puste)
        if (this.vegetables != null && !this.vegetables.isEmpty()) {
            this.ingredients.addAll(this.vegetables);
        }

        if (this.meat != null && !this.meat.isEmpty()) {
            this.ingredients.addAll(this.meat);
        }

        if (this.sauce != null && !this.sauce.isEmpty()) {
            this.ingredients.addAll(this.sauce);
        }
    }
}
