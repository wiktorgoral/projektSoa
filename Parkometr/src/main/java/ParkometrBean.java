import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

@ManagedBean(name = "ParkometrBean")
@RequestScoped
public class ParkometrBean {
    private int miejsceId;
    private int czas;

    public ArrayList<Integer> getMiejscaId(){
        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target("http://localhost:8080/MainImpl-1.0-SNAPSHOT/miejsca");
        String response = target.request().get(String.class);
        JSONArray json = new JSONArray(response);
        ArrayList<Integer> out = new ArrayList<Integer>();
        for(int i=0;i<json.length();i++){
            out.add(json.getInt(i));
        }
        return out;
    }

    public String kup() throws IOException {
        JSONObject json = new JSONObject();
        json.put("miejsceId",miejsceId);
        json.put("czas",czas);
        System.out.println(miejsceId );
        URL url = new URL("http://localhost:8080/MainImpl-1.0-SNAPSHOT/bilety");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setDoOutput(true);
        con.setRequestProperty("Content-Type", "application/json");
        OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream(), "UTF-8");
        writer.write(json.toString());
        writer.close();
        InputStream inputStream = con.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        StringBuffer jsonString = new StringBuffer();
        String line;
        while ((line = br.readLine()) != null) {
            jsonString.append(line);
        }
        br.close();
        con.disconnect();

        return "success";
    }


    public int getMiejsceId() {
        return miejsceId;
    }

    public void setMiejsceId(int miejsceId) {
        this.miejsceId = miejsceId;
    }

    public int getCzas() {
        return czas;
    }

    public void setCzas(int czas) {
        this.czas = czas;
    }
}
