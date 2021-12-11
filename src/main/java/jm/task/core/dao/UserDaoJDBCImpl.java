package jm.task.core.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    Util util = new Util();
    Connection connection;

    public void createUsersTable() {
        connection = util.getMySQLConnection();
        PreparedStatement preparedStatement = null;

        String sql = "CREATE TABLE user (\n" +
                "  iduser INT NOT NULL AUTO_INCREMENT,\n" +
                "  name VARCHAR(45) NOT NULL,\n" +
                "  lastname VARCHAR(45) NOT NULL,\n" +
                "  age INT NOT NULL,\n" +
                "  PRIMARY KEY (iduser))\n";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {

            }
        }
    }

    public void dropUsersTable() {
        connection = util.getMySQLConnection();
        PreparedStatement preparedStatement = null;

        String sql = "DROP TABLE user";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {

            }
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        connection = util.getMySQLConnection();
        PreparedStatement preparedStatement = null;

        String sql = "INSERT INTO user(name,lastname,age) VALUES(?,?,?)";


        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {

            }
        }

    }

    public void removeUserById(long id) {
        connection = util.getMySQLConnection();
        PreparedStatement preparedStatement = null;

        String sql = "DELETE FROM user WHERE iduser=" + id;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {

            }
        }
    }

    public List<User> getAllUsers() {
        connection = util.getMySQLConnection();
        List<User> users = new ArrayList<>();
        Statement statement = null;

        String sql = "SELECT * FROM user";

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("iduser"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastname"));
                user.setAge(resultSet.getByte("age"));

                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {

            }
        }
        return users;
    }

    public void cleanUsersTable() {
        connection = util.getMySQLConnection();
        PreparedStatement preparedStatement = null;

        String sql = "DELETE FROM user";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {

            }
        }
    }
}
