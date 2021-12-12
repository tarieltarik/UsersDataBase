package jm.task.core.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;


public class UserDaoHibernateImpl implements UserDao {

    private Session session = null;
    private Transaction transaction = null;

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        session = Util.getSessionFactory().openSession();
        transaction = session.beginTransaction();

        String hql = "CREATE TABLE IF NOT EXISTS user " +
                "(iduser BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(50) NOT NULL, lastName VARCHAR(50) NOT NULL, " +
                "age TINYINT NOT NULL)";

        session.createSQLQuery(hql).addEntity(User.class);

        transaction.commit();
        session.close();


    }

    @Override
    public void dropUsersTable() {
        session = Util.getSessionFactory().openSession();
        transaction = session.beginTransaction();

        String hql = "DROP TABLE IF EXISTS user";

        session.createSQLQuery(hql).addEntity(User.class);

        transaction.commit();
        session.close();

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        session = Util.getSessionFactory().openSession();
        transaction = session.getTransaction();
        try {
            transaction.begin();
            session.save(new User(name, lastName, age));
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

    }

    @Override
    public void removeUserById(long id) {
        session = Util.getSessionFactory().openSession();
        session = Util.getSessionFactory().openSession();
        transaction = session.getTransaction();
        transaction.begin();
        User user = session.get(User.class, id);
        if (user != null) {
            session.delete(user);
        }
        transaction.commit();
        session.close();

    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = Util.getSessionFactory().openSession()) {
            return session.createQuery("FROM User", User.class).list();
        }
    }

    @Override
    public void cleanUsersTable() {
        session = Util.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        String hql = "DELETE FROM user";
        session.createSQLQuery(hql).addEntity(User.class);
        transaction.commit();
        session.close();

    }
}
