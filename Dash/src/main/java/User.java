import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class User implements HttpSessionBindingListener {

    // All logins.
    private static Map<User, HttpSession> logins = new ConcurrentHashMap<>();

    // Normal properties.
    private Long id;
    private String username;


    // Etc.. Of course with public getters+setters.

    @Override
    public boolean equals(Object other) {
        return (other instanceof User) && (username != null) ? username.equals(((User) other).username) : (other == this);
    }

    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        HttpSession session = logins.remove(this);
        if (session != null) {
            session.invalidate();
        }
        logins.put(this, event.getSession());
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        logins.remove(this);
    }

    public static Map<User, HttpSession> getLogins() {
        return logins;
    }

    public static void setLogins(Map<User, HttpSession> logins) {
        User.logins = logins;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}


