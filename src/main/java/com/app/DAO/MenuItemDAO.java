package com.app.DAO;

import com.app.Model.MenuItem;
import com.app.Model.Tag;
import com.app.Model.Type;

import java.util.List;
import java.util.Optional;

public interface MenuItemDAO {
    boolean create(MenuItem menuItem);
    Optional<MenuItem> getById(Long id);
    boolean deleteById(Long id);
    boolean update(MenuItem menuItem);
    List<MenuItem> getAll();
    List<MenuItem> getAllByType(Type type);
    List<MenuItem> getAllByTags(List<Tag> tags);
}
