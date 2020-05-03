package com.app.DAO;

import com.app.Model.Ingredient;
import com.app.Model.Tag;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface TagDAO {
    Optional<Tag> getById(Long id) throws SQLException;
    boolean create(Tag tag) throws SQLException;
    boolean update(Tag tag) throws SQLException;
    boolean deleteById(Long id) throws SQLException;

    Tag mapTag(ResultSet rs) throws SQLException;
    List<Tag> getTagsByMenuItemId(Long id) throws SQLException;
}
