package com.app.DAO;

import com.app.Model.Ingredient;
import com.app.Model.Tag;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface IngredientDAO {
    Optional<Ingredient> getById(Long id);
    boolean create(Ingredient Ingredient);
    boolean update(Ingredient Ingredient);
    boolean deleteById(Long id);

    Ingredient mapIngredient(ResultSet rs) throws SQLException;
    List<Ingredient> getIngredientsByMenuItemId(Long id) throws SQLException;
}
