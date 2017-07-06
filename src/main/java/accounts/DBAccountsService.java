package accounts;

import dbservice.DBException;
import dbservice.H2DBService;
import services.AccountsService;
import services.DBService;

/**
 * Created by setser on 06.07.17.
 */
public class DBAccountsService implements AccountsService {

    private final DBService dbService;

    private static DBAccountsService instance;

    private DBAccountsService() {
        dbService = new H2DBService();
    }

    public static DBAccountsService instance() {
        if(instance == null) {
            instance = new DBAccountsService();
        }
        return instance;
    }

    @Override
    public void addUser(User user) throws AccountException {
        try {
            dbService.addUser(user, null);
        } catch (DBException e) {
            throw new AccountException(e);
        }
    }

    @Override
    public User getUserByName(String name) throws AccountException {
        try {
            return dbService.getUser(name);
        } catch (DBException e) {
            throw new AccountException(e);
        }
    }

    @Override
    public User getUserBySessionId(String sessionId) throws AccountException {
        try {
            return dbService.getUserBySessionId(sessionId);
        } catch (DBException e) {
            throw new AccountException(e);
        }
    }

    @Override
    public void addSession(User user, String sessionId) throws AccountException {
        try {
            dbService.setSessionId(user, sessionId);
        } catch(DBException e) {
            throw new AccountException(e);
        }
    }

    @Override
    public void deleteSession(String sessionId) throws AccountException {
        try {
            User user = dbService.getUserBySessionId(sessionId);
            dbService.setSessionId(user, null);
        } catch (DBException e) {
            throw new AccountException(e);
        }
    }
}
