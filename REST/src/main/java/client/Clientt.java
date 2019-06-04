package client;

import model.Bilet;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


public class Clientt {


    public void add(Bilet x){
        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target("http://localhost:8080/LAB8/bilet");
        String response = target.request().post(Entity.entity(x, MediaType.APPLICATION_XML),String.class);
        System.out.println(response);
    }

    public void delete(String x){
        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target("http://localhost:8080/LAB8/bilet"+x);
        Response response = target.request().delete();
        System.out.println(response.getStatus());
        response.close();
    }

    public String get(String x){
        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target("http://localhost:8080/LAB8/"+x);
        String response = target.request().get(String.class);
        return response;
    }

    public String getAll(){
        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target("http://localhost:8080/LAB8/");
        String response = target.request().get(String.class);
        return response;
    }

}
