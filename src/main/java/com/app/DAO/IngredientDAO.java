package com.app.DAO;

import com.app.Model.Ingredient;
import com.app.Model.Tag;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface IngredientDAO {
    Optional<Ingredient> getById(Long id) throws SQLException;
    boolean create(Ingredient Ingredient) throws SQLException;
    boolean update(Ingredient Ingredient) throws SQLException;
    boolean deleteById(Long id) throws SQLException;

    Ingredient mapIngredient(ResultSet rs) throws SQLException;
    List<Ingredient> getIngredientsByMenuItemId(Long id) throws SQLException;
}
