package com.app.DAO;

import com.app.Model.Ingredient;
import com.app.Model.Tag;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TagDAOImpl implements TagDAO {

    public static final String GET_TAGS_BY_MENU_ITEM_ID = "SELECT menu_items_tags.tag_id, tags.name, tags.discription" +
            "FROM menu_items_tags" +
            "INNER JOIN tags ON menu_items_tags.tag_id = tags.id" +
            "WHERE menu_item_id = ?;";



    DataSource dataSource;

    public TagDAOImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<Tag> getById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean create(Tag tag) {
        return false;
    }

    @Override
    public boolean update(Tag tag) {
        return false;
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }

    @Override
    public Tag mapTag(ResultSet rs) throws SQLException {
        return new Tag(rs.getLong("id"), rs.getString("name"),rs.getString("discription"));
    }

    @Override
    public List<Tag> getTagsByMenuItemId(Long id) throws SQLException {
        List<Tag> tags = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement query = connection
                    .prepareStatement(GET_TAGS_BY_MENU_ITEM_ID);
            query.setLong(1, id);
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                tags.add(mapTag(resultSet));
            }
        } catch (SQLException e) {
            throw e;
        }
        return tags;
    }
}
