package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    Util util = Util.getUtil();
    SessionFactory sessionFactory = util.getSessionFactory();
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            session.createQuery("create table if not exists Users (id bigint not null auto_increment, age tinyint, last_name varchar(255), name varchar(255) primary key (id))" )
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println("createUsersTable - ne rabotaet");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void dropUsersTable() {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            session.createQuery("DROP TABLE Users").executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println("dropUsersTable - ne rabotaet");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try{
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            session.save(new User(name, lastName, (byte)age));
            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println("saveUser - ne rabotaet");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeUserById(long id) {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println("removeUserById - ne rabotaet");
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> getAllUsers() {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            List<User> users = session.createQuery("from User").getResultList();
            return users;
        } catch (HibernateException e) {
            System.out.println("getAllUsers - ne rabotaet");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void cleanUsersTable() {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            session.createQuery("delete User").executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println("cleanUsersTable - ne rabotaet");
            throw new RuntimeException(e);
        }
    }
}
