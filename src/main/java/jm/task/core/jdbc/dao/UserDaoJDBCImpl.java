package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public static final Connection connection = Util.getUtil().getConnection();
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try  {
            Statement statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE `mydbtest`.`Users` (`id` INT NOT NULL AUTO_INCREMENT,`name` VARCHAR(45) NULL,`lastname` VARCHAR(45) NULL,`age` INT(3) NULL");
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        String tableCreate = "CREATE TABLE `mydbtest`.`Users` (`id` INT NOT NULL AUTO_INCREMENT,`name` VARCHAR(45) NULL,`lastname` VARCHAR(45) NULL,`age` INT(3) NULL, PRIMARY KEY (`id`))";
//        Statement statement =
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
