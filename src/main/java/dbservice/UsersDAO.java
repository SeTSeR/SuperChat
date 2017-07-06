package dbservice;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 * Created by setser on 06.07.17.
 */
public class UsersDAO {

    private Session session;

    public UsersDAO(Session session) {
        this.session = session;
    }

    public UsersDataSet get(long id) throws HibernateException {
        return (UsersDataSet) session.get(UsersDataSet.class, id);
    }

    public Long insertUser(String login, String password) throws HibernateException {
        return (Long) session.save(new UsersDataSet(login, password, null));
    }

    public long getUserIdByLogin(String name) throws HibernateException {
        Criteria criteria = session.createCriteria(UsersDataSet.class);
        UsersDataSet dataSet = ((UsersDataSet) criteria.add(Restrictions.eq("name", name)).uniqueResult());
        if(dataSet == null) throw new HibernateException("Data not found");
        return dataSet.getId();
    }

    public long getUserIdBySessionId(String sessionId) throws HibernateException {
        Criteria criteria = session.createCriteria(UsersDataSet.class);
        UsersDataSet dataSet = ((UsersDataSet) criteria.add(Restrictions.eq("sessionId", sessionId)).uniqueResult());
        if(dataSet == null) throw new HibernateException("Data not found");
        return dataSet.getId();
    }
}
