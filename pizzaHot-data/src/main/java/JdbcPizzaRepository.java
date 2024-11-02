//package pl.edu.wszib.pizzahot.Repository;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.PreparedStatementCreator;
//import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
//import org.springframework.jdbc.support.GeneratedKeyHolder;
//import org.springframework.jdbc.support.KeyHolder;
//import org.springframework.stereotype.Repository;
//import pl.edu.wszib.pizzahot.Model.Ingredient;
//import pl.edu.wszib.pizzahot.Model.Pizza;
//
//import java.sql.Timestamp;
//import java.sql.Types;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Date;
//import java.util.List;
//@Repository
//public class JdbcPizzaRepository  implements PizzaRepository{
//
//    private JdbcTemplate jdbcTemplate;
//    private IngredientRepository ingredientRepo;
//
//    @Autowired
//    public JdbcPizzaRepository(JdbcTemplate jdbcTemplate, IngredientRepository ingredientRepository){
//        this.jdbcTemplate = jdbcTemplate;
//        this.ingredientRepo = ingredientRepository;
//    }
//
//    @Override
//    public Pizza save(Pizza pizza) {
//        long pizzaId = savePizzaInfo(pizza);
//        pizza.setId(pizzaId);
//
//        List<Ingredient> pizzaIngredients = new ArrayList<>();
//
//        Ingredient dough = ingredientRepo.findById(pizza.getDough());
//        pizzaIngredients.add(dough);
//
//        if (pizza.getVegetables() != null){
//            for (String ingredient : pizza.getVegetables()){
//                Ingredient vegetables = ingredientRepo.findById(ingredient);
//                pizzaIngredients.add(vegetables);
//            }
//        }
//
//        if (pizza.getMeat() != null){
//            for (String ingredient : pizza.getMeat()){
//                Ingredient meat = ingredientRepo.findById(ingredient);
//                pizzaIngredients.add(meat);
//            }
//        }
//
//        if (pizza.getSauce() != null){
//            for (String ingredient : pizza.getSauce()){
//                Ingredient sauce = ingredientRepo.findById(ingredient);
//                pizzaIngredients.add(sauce);
//            }
//        }
//
//        for (Ingredient ingredient : pizzaIngredients){
//            saveIngredientToPizza(ingredient, pizzaId);
//        }
//
//        return pizza;
//    }
//
//    private long savePizzaInfo(Pizza pizza) {
//        pizza.setCreateAt(new Date());
//
//        PreparedStatementCreatorFactory pscFactory = new PreparedStatementCreatorFactory(
//                "INSERT INTO Pizza (name, createdAt) VALUES (?, ?)",
//                Types.VARCHAR, Types.TIMESTAMP);
//                pscFactory.setReturnGeneratedKeys(true);  // Ensure that the generated key is returned
//
//        PreparedStatementCreator psc = pscFactory.newPreparedStatementCreator(Arrays.asList(
//                pizza.getName(), new Timestamp(pizza.getCreateAt().getTime())
//        ));
//
//        KeyHolder keyHolder = new GeneratedKeyHolder();
//        jdbcTemplate.update(psc, keyHolder);
//
//        if (keyHolder.getKey() != null) {
//            return keyHolder.getKey().longValue();
//        } else {
//            throw new IllegalStateException("Failed to retrieve the generated pizza ID");
//        }
//    }
//
//    private void saveIngredientToPizza(Ingredient ingredient, long pizzaId) {
//        jdbcTemplate.update("insert into Pizza_Ingredients (pizza,ingredient) values (?, ?)",
//                                                                        pizzaId, ingredient.getId());
//    }
//}
