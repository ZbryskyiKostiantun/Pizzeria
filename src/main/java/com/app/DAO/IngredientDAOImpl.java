package com.app.DAO;

import com.app.Model.Ingredient;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class IngredientDAOImpl implements IngredientDAO {
    public static final String GET_INGREDIENTS_BY_MENU_ITEM_ID = "SELECT menu_items_ingredients.ingredient_id, ingredients.name, ingredients.discription" +
            "FROM menu_items_ingredients" +
            "INNER JOIN ingredients ON menu_items_ingredients.ingredient_id = ingredients.id" +
            "WHERE menu_item_id = ?;";

    DataSource dataSource;

    public IngredientDAOImpl(DataSource dataSource)  {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<Ingredient> getById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean create(Ingredient Ingredient) {
        return false;
    }

    @Override
    public boolean update(Ingredient Ingredient) {
        return false;
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }

    @Override
    public Ingredient mapIngredient(ResultSet rs) throws SQLException {
        return new Ingredient(rs.getLong("id"), rs.getString("name"),rs.getString("discription"));
    }


    @Override
    public List<Ingredient> getIngredientsByMenuItemId(Long id) throws SQLException {
        List<Ingredient> ingredients = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement query = connection
                    .prepareStatement(GET_INGREDIENTS_BY_MENU_ITEM_ID);
            query.setLong(1, id);
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                ingredients.add(mapIngredient(resultSet));
            }
        } catch (SQLException e) {
            throw e;
        }
        return ingredients;
    }
}
