package jm.task.core.jdbc;

import jm.task.core.dao.UserDao;
import jm.task.core.dao.UserDaoHibernateImpl;
import jm.task.core.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        UserDao ud = new UserDaoJDBCImpl();
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

        UserDao Ud = new UserDaoHibernateImpl();
        List<User> usersH = Arrays.asList(new User("Timur", "Muratov", (byte) 10),
                new User("Artur", "Alisherov", (byte) 10),
                new User("Oleg", "Volkov", (byte) 10),
                new User("Ivan", "Muratov", (byte) 10)
        );
        usersH.forEach(u -> ud.saveUser(u.getName(), u.getLastName(), u.getAge()));
        usersH = ud.getAllUsers();
        usersH.forEach(u -> System.out.println(u.toString()));
        Ud.cleanUsersTable();
        Ud.dropUsersTable();

    }
}
