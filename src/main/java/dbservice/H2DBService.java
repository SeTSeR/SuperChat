package dbservice;

import accounts.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import services.DBService;

/**
 * Created by setser on 06.07.17.
 */
public class H2DBService implements DBService {

    private final SessionFactory sessionFactory;

    public H2DBService() {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable th) {
            System.err.println("Initial SessionFactory creation failed: " + th);
            throw new ExceptionInInitializerError(th);
        }
    }

    @Override
    public User getUser(String login) throws DBException {
        try {
            Session session = sessionFactory.openSession();
            UsersDAO dao = new UsersDAO(session);
            long id = dao.getUserIdByLogin(login);
            UsersDataSet dataSet = dao.get(id);
            return new User(dataSet.getName(), dataSet.getPassword());
        } catch (HibernateException e) {
            throw new DBException(e);
        }
    }

    @Override
    public User getUserBySessionId(String sessionId) throws DBException {
        try {
            Session session = sessionFactory.openSession();
            UsersDAO dao = new UsersDAO(session);
            long id = dao.getUserIdBySessionId(sessionId);
            UsersDataSet dataSet = dao.get(id);
            return new User(dataSet.getName(), dataSet.getPassword());
        } catch (HibernateException e) {
            throw new DBException(e);
        }
    }

    @Override
    public void addUser(User user, String sessionId) throws DBException {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            UsersDAO dao = new UsersDAO(session);
            long id = dao.insertUser(user.getLogin(), user.getPassword());
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            throw new DBException(e);
        }
    }

    @Override
    public void setSessionId(User user, String sessionId) throws DBException {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            UsersDAO dao = new UsersDAO(session);
            long id = dao.getUserIdByLogin(user.getLogin());
            UsersDataSet dataSet = dao.get(id);
            dataSet.setSessionId(sessionId);
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            throw new DBException(e);
        }
    }

}
