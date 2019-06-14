import POJO.Spot;
import POJO.UzytkownikPOJO;
import Warning.BazaDanych;
import Warning.Wiadomosci;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.List;

@ManagedBean(name = "Dashboard")
@SessionScoped
public class Dashboard implements Serializable {

    private UzytkownikPOJO uzytkownik;

    private List<Spot> stan;

    private List<String> wiadomosci;

    @EJB(lookup = "java:global/MainImpl-1.0-SNAPSHOT/BazaWiadomosci!Warning.remote.WiadomosciRemote")
    Wiadomosci bazaWiadomosci;

    @EJB(lookup = "java:global/MainImpl-1.0-SNAPSHOT/BazaDanychBean!Warning.remote.BazaDanychRemote")
    BazaDanych bazaDanych;


    @PostConstruct
    public void init() {
        if (uzytkownik == null) {
            String nick = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
            if (!nick.isEmpty()) {
                uzytkownik = bazaDanych.getUzytkownik(nick);
                System.out.println(uzytkownik.getNick());
                stan = bazaDanych.getMiejsca(uzytkownik);
                wiadomosci = bazaWiadomosci.get(uzytkownik.getId());
            }
        }
    }


    public void updateStan(){
        stan = bazaDanych.getMiejsca(uzytkownik);
    }

    public void updateWiadomosci(){
        wiadomosci.addAll(bazaWiadomosci.get(uzytkownik.getId()));
    }

    public UzytkownikPOJO getUzytkownik() {
        return uzytkownik;
    }

    public void setUzytkownik(UzytkownikPOJO uzytkownik) {
        this.uzytkownik = uzytkownik;
    }

    public List<Spot> getStan() {
        return stan;
    }

    public void setStan(List<Spot> stan) {
        this.stan = stan;
    }

    public List<String> getWiadomosci() {
        return wiadomosci;
    }

    public void setWiadomosci(List<String> wiadomosci) {
        this.wiadomosci = wiadomosci;
    }
}
