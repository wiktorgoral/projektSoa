import com.google.gson.Gson;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.json.JSONArray;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import java.io.IOException;
import java.io.Serializable;
import java.util.Vector;

@ManagedBean(name = "Dashboard")
@SessionScoped
public class Dashboard implements Serializable {

    private String username;

    private Bilet[] bilets;

    private Vector<String> powiadomienia;


    @PostConstruct
    public void init() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        username = context.getExternalContext().getRemoteUser();
        User user = new User();
        user.setUsername(username);
        context.getExternalContext().getSessionMap().put("user",user);
        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target("http://localhost:8080/LAB8/");
        String response = target.request().get(String.class);
        JSONArray arr = new JSONArray(response);
        Gson gson = new Gson();
        bilets = gson.fromJson(response, Bilet[].class);
        if (!username.equals("admin")){
            Bilet[] out=new Bilet[20];
            int numer = Character.getNumericValue(username.charAt(username.length() - 1));
            for (int i=0;i<20;i++){
                out[i]=bilets[numer*20+i];
            }
            bilets=out;
        }
    }

    public Vector<String> getPowiadomienia() {
        return powiadomienia;
    }

    public void setPowiadomienia(Vector<String> powiadomienia) {
        this.powiadomienia = powiadomienia;
    }

    public Bilet[] getBilets() {
        return bilets;
    }

    public void setBilets(Bilet[] bilets) {
        this.bilets = bilets;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
