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

    public static final String GET_TAG_BY_ID = "SELECT * FROM tags WHERE id = ?;";
    public static final String DELETE_TAG_BY_ID = "DELETE FROM tags WHERE id = ?;";
    public static final String CREATE_TAG = "INSERT INTO tags (name, discription) VALUES (?, ?);";
    public static final String UPDATE_TAG = "INSERT INTO tags (id, name, discription) VALUES (?, ?, ?);";



    DataSource dataSource;

    public TagDAOImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<Tag> getById(Long id) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement query = connection
                    .prepareStatement(GET_TAG_BY_ID);
            query.setLong(1, id);
            ResultSet resultSet = query.executeQuery();
            if (resultSet.next()) {
                return Optional.of(mapTag(resultSet));
            }
        } catch (SQLException e) {
            throw e;
        }
        return Optional.empty();
    }

    @Override
    public boolean create(Tag tag) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement query = connection
                    .prepareStatement(CREATE_TAG);
            query.setString(1, tag.getName());
            query.setString(2, tag.getDiscription());
            query.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
        return true;
    }

    @Override
    public boolean update(Tag tag) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement query = connection
                    .prepareStatement(UPDATE_TAG);
            query.setLong(1, tag.getId());
            query.setString(2, tag.getName());
            query.setString(3, tag.getDiscription());
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
                    .prepareStatement(DELETE_TAG_BY_ID);
            query.setLong(1, id);
            return query.executeUpdate() == 1;
        } catch (
                SQLException e) {
            throw e;
        }
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
