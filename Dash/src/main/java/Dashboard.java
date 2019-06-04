import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serializable;

@ManagedBean(name = "Dashboard")
@SessionScoped
public class Dashboard implements Serializable {



    private String username;

    @PostConstruct
    public void init() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        username = context.getExternalContext().getRemoteUser();
        User user = new User();
        user.setUsername(username);
        context.getExternalContext().getSessionMap().put("user",user);

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
