package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {


    public UserDaoJDBCImpl() {

    }

    @Override
    public void createUsersTable() {
        String createTableCom = "CREATE TABLE IF NOT EXISTS users (\n" +
                "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                "  `name` VARCHAR(45) NOT NULL,\n" +
                "  `lastName` VARCHAR(45) NOT NULL,\n" +
                "  `age` VARCHAR(45) NOT NULL,\n" +
                "  PRIMARY KEY (`id`),\n" +
                "  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)\n" +
                "ENGINE = InnoDB\n" +
                "DEFAULT CHARACTER SET = utf8;";
        try (Connection connection = Util.getConnection()) {
            connection.prepareStatement(createTableCom).executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void dropUsersTable() {
        String removeTable = "DROP TABLE IF EXISTS users";
        try (Connection connection = Util.getConnection()) {
            connection.prepareStatement(removeTable).executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        String sql = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";
        try (Connection connection = Util.getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, lastName);
            pstmt.setByte(3, age);
            pstmt.executeUpdate();
            System.out.printf("User с именем – %s добавлен в базу данных\n", name);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void removeUserById(long id) {

        String removeTable = "DELETE FROM users WHERE id = ?";
        try (Connection connection = Util.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(removeTable);
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> listOfUsers = new ArrayList<>();
        String sql = "SELECT * FROM users";

        try (Connection connection = Util.getConnection()) {
            ResultSet result = connection.prepareStatement(sql).executeQuery();
            while (result.next()) {
                User user = new User();
                user.setId(result.getLong("id"));
                user.setName(result.getString("name"));
                user.setLastName(result.getString("lastName"));
                user.setAge((byte) result.getInt("age"));
                listOfUsers.add(user);
            }
            System.out.println(listOfUsers);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listOfUsers;
    }

    @Override
    public void cleanUsersTable() {
        String cleanUp = "DELETE FROM users";
        try (Connection connection = Util.getConnection()) {
            connection.prepareStatement(cleanUp).executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}