package com.app.DAO;

import com.app.Model.Ingredient;
import com.app.Model.MenuItem;
import com.app.Model.Tag;
import com.app.Model.Type;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MenuItemDAOImpl implements MenuItemDAO {
    private DataSource dataSource;
    private TagDAO tagDAO;
    private IngredientDAO ingredientDAO;

    public static final String GET_MENU_ITEM_BY_ID = "SELECT * FROM menu_items where id = ?;";

    public static final String GET_ALL_MENU_ITEMS = "SELECT *" +
                                                    "FROM menu_items";

    public static final String GET_ALL_MENU_ITEMS_BY_TYPE = "SELECT *" +
                                                            "FROM menu_items" +
                                                            "WHERE type = ?";

    public static final String GET_MENU_ITEM_INGREDIENTS_BY_ID = "SELECT menu_items_ingredients.ingredient_id, ingredients.name, ingredients.discription" +
                                                           "FROM menu_items_ingredients" +
                                                           "INNER JOIN ingredients ON menu_items_ingredients.ingredient_id = ingredients.id" +
                                                           "WHERE menu_item_id = ?;";


    public MenuItemDAOImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public boolean create(MenuItem menuItem) {
        return false;
    }

    @Override
    public Optional<MenuItem> getById(Long id) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement query = connection
                    .prepareStatement(GET_MENU_ITEM_BY_ID);
            query.setLong(1, id);
            ResultSet resultSet = query.executeQuery();
            if (resultSet.next()) {
                return Optional.of(mapMenuItem(resultSet));
            }
            return Optional.empty();
        } catch (SQLException e) {
//            throw new DAOException("Unable to find course", e);
        }
    }

    private MenuItem mapMenuItem(ResultSet rs) throws SQLException {
        return new MenuItem(rs.getLong("id"), rs.getString("name"),rs.getString("discription"), rs.getLong("price"), Type.valueOf(rs.getString("type")));
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }
    

    public List<Ingredient> getIngredientsById(Long id) throws SQLException {
        List<Ingredient> ingredients = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement query = connection
                    .prepareStatement(GET_MENU_ITEM_TAGS_BY_ID);
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


    private Ingredient mapIngredient(ResultSet rs) throws SQLException {
        return new Ingredient(rs.getLong("id"), rs.getString("name"),rs.getString("discription"));
    }


    @Override
    public boolean update(MenuItem menuItem) {
        return false;
    }

    @Override
    public List<MenuItem> getAll() {
        return null;
    }

    @Override
    public List<MenuItem> getAllByType(Type type) {
        return null;
    }

    @Override
    public List<MenuItem> getAllByTags(List<Tag> tags) {
        return null;
    }

//    private MenuItem mapMenuItem(ResultSet rs) throws SQLException {
//        return new MenuItem(rs.getLong("id"), rs.getString("name"), rs.getString("description"), rs.getLong("price"), rs.get);
//    }

}
