package jm.task.core.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final UserService usi = new UserServiceImpl();
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try {
            usi.createUsersTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try {
            usi.dropUsersTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            usi.saveUser(name,lastName,age);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try {
            usi.removeUserById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        try {
            return usi.getAllUsers();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void cleanUsersTable() {
        try {
            usi.cleanUsersTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
