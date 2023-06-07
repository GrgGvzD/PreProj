package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    //public static final Connection connection = Util.getUtil().getConnection();
    public static final Connection connection = null;
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try  {
            Statement statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS mydbtest.Users (id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(45), lastname VARCHAR(45), age INT(3))");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DROP TABLE Users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            PreparedStatement pStat = connection.prepareStatement("INSERT INTO Users (name, lastname, age) VALUES (?, ?, ?)");
            pStat.setString(1, name);
            pStat.setString(2, lastName);
            pStat.setByte(3, age);
            pStat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try {
            PreparedStatement pStat = connection.prepareStatement("DELETE FROM Users WHERE id = ?");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            ResultSet resSet = connection.createStatement().executeQuery("SELECT * From Users");
            while (resSet.next()) {
                User user = new User(resSet.getString("name"), resSet.getString("lastname"),resSet.getByte("age"));
                user.setId(resSet.getLong("id"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("TRUNCATE TABLE Users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
