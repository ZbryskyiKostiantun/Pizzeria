package com.app.DAO;

import com.app.Model.MenuItem;
import com.app.Model.Tag;
import com.app.Model.Type;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface MenuItemDAO {
    boolean create(MenuItem menuItem) throws SQLException;
    Optional<MenuItem> getById(Long id);
    boolean deleteById(Long id) throws SQLException;
    boolean update(MenuItem menuItem) throws SQLException;
    List<MenuItem> getAll() throws SQLException;
    List<MenuItem> getAllByType(Type type) throws SQLException;
}
