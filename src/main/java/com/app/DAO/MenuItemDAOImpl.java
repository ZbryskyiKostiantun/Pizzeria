package com.app.DAO;

import com.app.Model.Ingredient;
import com.app.Model.MenuItem;
import com.app.Model.Tag;
import com.app.Model.Type;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.sql.Types.NULL;

public class MenuItemDAOImpl implements MenuItemDAO {
    private DataSource dataSource;

    public static final String GET_MENU_ITEM_BY_ID = "SELECT * FROM menu_items where id = ?;";

    public static final String GET_ALL_MENU_ITEMS = "SELECT *" +
                                                    "FROM menu_items";

    public static final String GET_ALL_MENU_ITEMS_BY_TYPE = "SELECT *" +
                                                            "FROM menu_items" +
                                                            "WHERE type= ?";

    public static final String DELETE_MENU_ITEM_BY_ID = "DELETE FROM menu_items" +
                                                        "WHERE id = ?;";

    public static final String CREATE_MENU_ITEM = "INSERT INTO menu_items (name, discription, price, type) " +
                                                  "VALUES (?, ?, ?, ?);";
    public static final String UPDATE_MENU_ITEM = "INSERT INTO menu_items (id, name, discription, price, type) " +
            "VALUES (?, ?, ?, ?, ?);";


    public MenuItemDAOImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public boolean create(MenuItem menuItem) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement query = connection
                    .prepareStatement(CREATE_MENU_ITEM);
            query.setString(1, menuItem.getName());
            query.setString(2, menuItem.getDiscription());
            query.setLong(3, menuItem.getPrice());
            query.setString(4, String.valueOf(menuItem.getType()));
            query.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
        return true;
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
    public boolean deleteById(Long id) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement query = connection
                    .prepareStatement(DELETE_MENU_ITEM_BY_ID);
            query.setLong(1, id);
            return query.executeUpdate() == 1;
        } catch (
                SQLException e) {
            throw e;
        }
    }



    @Override
    public boolean update(MenuItem menuItem) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement query = connection
                    .prepareStatement(UPDATE_MENU_ITEM);
            query.setLong(1, menuItem.getId());
            query.setString(2, menuItem.getName());
            query.setString(3, menuItem.getDiscription());
            query.setLong(4, menuItem.getPrice());
            query.setString(5, String.valueOf(menuItem.getType()));
            query.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
        return true;
    }

    @Override
    public List<MenuItem> getAll() throws SQLException {
        List<MenuItem> menuItems = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement query = connection
                    .prepareStatement(GET_ALL_MENU_ITEMS);
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                menuItems.add(mapMenuItem(resultSet));
            }
        } catch (SQLException e) {
            throw e;
        }
        return menuItems;
    }

    @Override
    public List<MenuItem> getAllByType(Type type) throws SQLException {
        List<MenuItem> menuItems = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement query = connection
                    .prepareStatement(GET_ALL_MENU_ITEMS_BY_TYPE);
            query.setString(1, String.valueOf(type));
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                menuItems.add(mapMenuItem(resultSet));
            }
        } catch (SQLException e) {
            throw e;
        }
        return menuItems;
    }


}
