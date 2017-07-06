package dbservice;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by setser on 06.07.17.
 */
@Entity
@Table(name = "users")
public class UsersDataSet implements Serializable {
    private static final long serialVersionUID = -8706689714326132798L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "login", unique = true, updatable = false)
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "sessionId", unique = true)
    private String sessionId;

    public UsersDataSet() {
    }

    public UsersDataSet(long id, String name, String password, String sessionId) {
        this.setId(id);
        this.setName(name);
        this.setPassword(password);
        this.setSessionId(sessionId);
    }

    public UsersDataSet(String name, String password, String sessionId) {
        this.setId(-1);
        this.setName(name);
        this.setPassword(password);
        this.setSessionId(sessionId);
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public String toString() {
        return "UserDataSet{" +
                "id=" + id +
                ", login='" + name + '\'' +
                ", password='" + password + '\'' +
                ", sessionId='" + sessionId + '\'' +
                '}';
    }
}
