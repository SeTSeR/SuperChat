package services;

import accounts.AccountException;
import accounts.User;

/**
 * Created by setser on 03.07.17.
 */
public interface AccountsService {
    void addUser(User user) throws AccountException;
    User getUserByName(String name) throws AccountException;
    User getUserBySessionId(String sessionId) throws AccountException;
    void addSession(User user, String sessionId);
    void deleteSession(String sessionId) throws AccountException;
}
