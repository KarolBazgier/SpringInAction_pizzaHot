package pizzas;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class Order {

    private Date placedAt;
    private String name;
    private String street;
    private String city;
    private String zip;

    private List<Pizza> pizzas = new ArrayList<>();

}
