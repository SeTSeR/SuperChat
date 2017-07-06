package services;

import accounts.User;
import dbservice.DBException;

/**
 * Created by setser on 06.07.17.
 */
public interface DBService {
    User getUser(String login) throws DBException;
    User getUserBySessionId(String sessionId) throws DBException;
    void addUser(User user, String sessionId) throws DBException;
    void setSessionId(User user, String sessionId) throws DBException;
}
