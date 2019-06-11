package Controller;


import Warning.local.WiadomosciLocal;
import Warning.remote.WiadomosciRemote;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.json.JSONObject;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Local(WiadomosciLocal.class)
@Remote(WiadomosciRemote.class)
@Stateless
public class BazaWiadomosci implements WiadomosciLocal, WiadomosciRemote{

    private List<String> wiadomosci = new ArrayList<String>();
    private Date ostatni = new Date();

    public void dodaj(String text) {
        if(text.contains("Data")){
            String data = text.split(":")[1];
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
            try {
                ostatni = format.parse(data);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }else {
            wiadomosci.add(text);
        }

    }

    public List<String> get(int id) {
        List<String> wiadomosciUzytkownika = new ArrayList<String>();
        for (int i=0; i<wiadomosci.size();i++){
            if (wiadomosci.get(i).contains(id+":")) {
                wiadomosciUzytkownika.add(wiadomosci.get(i));
                wiadomosci.remove(i);
            }
        }
        return wiadomosciUzytkownika;
    }

    public Date ostatni() {
        return ostatni;
    }

    private static Boolean warningOK(String warning) {
        String[] id = warning.split(":");
        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target("http://localhost:8080/projektSoa/warnings/"+ id[1]);
        String response = target.request().get(String.class);
        JSONObject json = new JSONObject(response);
        return json.getBoolean("OK");
    }



    public List<String> getWiadomosci() {
        return wiadomosci;
    }

    public void setWiadomosci(List<String> wiadomosci) {
        this.wiadomosci = wiadomosci;
    }

    public Date getOstatni() {
        return ostatni;
    }

    public void setOstatni(Date ostatni) {
        this.ostatni = ostatni;
    }
}
