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

    public static final String GET_INGREDIENT_BY_ID = "SELECT * FROM ingredients WHERE id = ?;";
    public static final String DELETE_INGREDIENT_BY_ID = "DELETE FROM ingredients WHERE id = ?;";
    public static final String CREATE_INGREDIENT = "INSERT INTO ingredients (name, discription) VALUES (?, ?);";
    public static final String UPDATE_INGREDIENT = "INSERT INTO ingredients (id, name, discription) VALUES (?, ?, ?);";

    DataSource dataSource;

    public IngredientDAOImpl(DataSource dataSource)  {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<Ingredient> getById(Long id) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement query = connection
                    .prepareStatement(GET_INGREDIENT_BY_ID);
            query.setLong(1, id);
            ResultSet resultSet = query.executeQuery();
            if (resultSet.next()) {
                return Optional.of(mapIngredient(resultSet));
            }
        } catch (SQLException e) {
            throw e;
        }
        return Optional.empty();
    }

    @Override
    public boolean create(Ingredient ingredient) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement query = connection
                    .prepareStatement(CREATE_INGREDIENT);
            query.setString(1, ingredient.getName());
            query.setString(2, ingredient.getDiscription());
            query.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
        return true;
    }

    @Override
    public boolean update(Ingredient ingredient) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement query = connection
                    .prepareStatement(UPDATE_INGREDIENT);
            query.setLong(1, ingredient.getId());
            query.setString(2, ingredient.getName());
            query.setString(3, ingredient.getDiscription());
            query.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
        return true;
    }

    @Override
    public boolean deleteById(Long id) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement query = connection
                    .prepareStatement(DELETE_INGREDIENT_BY_ID_BY_ID);
            query.setLong(1, id);
            return query.executeUpdate() == 1;
        } catch (
                SQLException e) {
            throw e;
        }
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
