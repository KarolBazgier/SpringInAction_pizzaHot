//package pl.edu.wszib.pizzahot.Repository;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.stereotype.Repository;
//import pl.edu.wszib.pizzahot.Model.Ingredient;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//@Slf4j
//@Repository
//public class JdbcIngredientRepository implements IngredientRepository {
//    private JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    public JdbcIngredientRepository(JdbcTemplate jdbcTemplate){
//        this.jdbcTemplate = jdbcTemplate;
//    }
//    @Override
//    public Iterable<Ingredient> findAll() {
//        return jdbcTemplate.query("select id, name, type from Ingredient",
//                this::mapRowToIngredient);
//    }
//
//    private Ingredient mapRowToIngredient(ResultSet resultSet, int i) throws SQLException {
//        return new Ingredient(
//                resultSet.getString("id"),
//                resultSet.getString("name"),
//                Ingredient.Type.valueOf(resultSet.getString("type")));
//    }
//
//    @Override
//    public Ingredient findById(String id) {
//        return jdbcTemplate.queryForObject("select id, name, type from Ingredient where id=?",
////
////                new RowMapper<Ingredient>() {
////                    @Override
////                    public Ingredient mapRow(ResultSet rs, int rowNum) throws SQLException {
////                        return new Ingredient(
////                                rs.getString("id"),
////                                rs.getString("name"),
////                                Ingredient.Type.valueOf(rs.getString("type"))
////                        );
////                    }
////                }, id);
//                ///// POWYZSZY KOD MOZE BYC ZASTAPIONY LAMBDA JAK NA DOLE
//        (rs, rowNum) -> new Ingredient(
//                        rs.getString("id"),
//                        rs.getString("name"),
//                        Ingredient.Type.valueOf(rs.getString("type"))
//                ),id);
//    }
//
//    @Override
//    public Ingredient save(Ingredient ingredient) {
//        jdbcTemplate.update("insert into Ingredient (id, name, type) values (?, ?, ?)",
//                ingredient.getId(),
//                ingredient.getName(),
//                ingredient.getType().toString());
//        return ingredient;
//    }
//}
