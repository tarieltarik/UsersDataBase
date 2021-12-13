package jm.task.core.jdbc;

import jm.task.core.dao.UserDao;
import jm.task.core.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;


public class Main {
    public static void main(String[] args) throws SQLException {


        UserDao ud = new UserDaoHibernateImpl();
        ud.createUsersTable();
        List<User> users = Arrays.asList(new User("Timur", "Muratov", (byte) 10),
                new User("Artur", "Alisherov", (byte) 10),
                new User("Oleg", "Volkov", (byte) 10),
                new User("Ivan", "Muratov", (byte) 10)
        );
        users.forEach(u -> ud.saveUser(u.getName(), u.getLastName(), u.getAge()));
        users = ud.getAllUsers();
        users.forEach(u -> System.out.println(u.toString()));

        ud.cleanUsersTable();
        ud.dropUsersTable();


    }
}
