package accounts;

import services.AccountsService;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by setser on 03.07.17.
 */
public class MapAccountsService implements AccountsService {
    private static Map<String, User> loginToUser;
    private static Map<String, User> sessionIdToUser;
    private static MapAccountsService instance;

    private MapAccountsService() {
        loginToUser = new ConcurrentHashMap<>();
        sessionIdToUser = new ConcurrentHashMap<>();
    }

    public static MapAccountsService instance() {
        if(instance == null) instance = new MapAccountsService();
        return instance;
    }

    @Override
    public void addUser(User user) throws AccountException {
        if(loginToUser.containsKey(user.getLogin())) throw new AccountException(AccountError.LOGIN_ALREADY_PRESENT);
        loginToUser.put(user.getLogin(), user);
    }

    @Override
    public User getUserByName(String name) throws AccountException {
        if(!loginToUser.containsKey(name)) throw new AccountException(AccountError.LOGIN_NOT_FOUND);
        return loginToUser.get(name);
    }

    @Override
    public User getUserBySessionId(String sessionId) throws AccountException {
        if(!sessionIdToUser.containsKey(sessionId)) throw new AccountException(AccountError.SESSION_NOT_FOUND);
        return sessionIdToUser.get(sessionId);
    }

    @Override
    public void addSession(User user, String sessionId) {
        sessionIdToUser.put(sessionId, user);
    }

    @Override
    public void deleteSession(String sessionId) throws AccountException {
        if(!sessionIdToUser.containsKey(sessionId)) throw new AccountException(AccountError.SESSION_NOT_FOUND);
        sessionIdToUser.remove(sessionId);
    }
}
