package com.app.DAO;

import com.app.Model.Ingredient;
import com.app.Model.Tag;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface TagDAO {
    Optional<Tag> getById(Long id);
    boolean create(Tag tag);
    boolean update(Tag tag);
    boolean deleteById(Long id);

    Tag mapTag(ResultSet rs) throws SQLException;
    List<Tag> getTagsByMenuItemId(Long id) throws SQLException;
}
