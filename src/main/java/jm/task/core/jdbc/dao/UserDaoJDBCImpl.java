package jm.task.core.jdbc.dao;

import jdk.jshell.execution.Util;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class UserDaoJDBCImpl implements UserDao {
    Util util = new Util();

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        try {
            Statement statement = util.getConnection().createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS users (id BIGINT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(30), lastName VARCHAR(30), age INT(2))");
        } catch (SQLException throwables) {
            System.out.println("Ошибка в создании таблицы");
        }
    }

    public void dropUsersTable() {
        util = null;
        try {
            Statement statement = util.getConnection().createStatement();
            statement.executeUpdate("DROP TABLE users");
        } catch (SQLException throwables) {
            System.out.println("Таблицы не существует!");
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        private static final String INSERT = "INSERT INTO users VALUES (id,?,?,?)";
        util = null;
        PreparedStatement preparedStatement = null;
        try {
            Statement statement = util.getConnection().createStatement();
            preparedStatement = statement.getConnection().prepareStatement(INSERT);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        private static final String DELETE = "DELETE FROM users WHERE (id = ?)";
        util = null;
        PreparedStatement preparedStatement = null;
        try {
            Statement statement = util.getConnection().createStatement();
            preparedStatement = statement.getConnection().prepareStatement(DELETE);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        private static final String GET_ALL = "SELECT * FROM users";
        util = null;
        PreparedStatement preparedStatement = null;
        List<User> list = new LinkedList<User>();
        User user = new User();
        try {
            Statement statement = util.getConnection().createStatement();
            preparedStatement = statement.getConnection().prepareStatement(GET_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge((byte) resultSet.getInt("age"));
                list.add(user);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return list; // Тут придумать как вернуть лист
    }

    public void cleanUsersTable() {
        util = null;
        try {
            Statement statement = util.getConnection().createStatement();
            statement.executeUpdate("DELETE FROM users");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
