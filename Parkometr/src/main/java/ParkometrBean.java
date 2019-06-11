import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

@ManagedBean(name = "ParkometrBean")
@RequestScoped
public class ParkometrBean {
    private int miejsceId;
    private int czas;

    public ArrayList<Integer> getMiejscaId(){
        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target("http://localhost:8080/Dash_war_exploded/miejsca");
        String response = target.request().get(String.class);
        JSONArray json = new JSONArray(new JSONObject(response));
        ArrayList<Integer> out = new ArrayList<Integer>();
        for(int i=0;i<json.length();i++){
            out.add(json.getJSONObject(i).getInt("id"));
        }
        return out;
    }

    public String kup(){
        JSONObject json = new JSONObject();
        json.put("miejsceId",miejsceId);
        json.put("czas",czas);
        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target("http://localhost:8080/Dash_war_exploded/bilety");
        String response = target.request().post(Entity.entity(json, MediaType.APPLICATION_JSON),String.class);
        System.out.println(response);

        return "success";
    }


    public int getMiejsceId() {
        return miejsceId;
    }

    public void setMiejsceId(int miejsceId) {
        this.miejsceId = miejsceId;
    }
}
