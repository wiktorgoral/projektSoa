import POJO.MiejscePOJO;
import POJO.UzytkownikPOJO;
import Warning.BazaDanych;
import Warning.Wiadomosci;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

@ManagedBean(name = "Dashboard")
@SessionScoped
public class Dashboard implements Serializable {

    private UzytkownikPOJO uzytkownik;

    private List<MiejscePOJO> stan;

    private List<String> wiadomosci;

    @Inject
    Wiadomosci bazaWiadomosci;

    @Inject
    BazaDanych bazaDanych;

    @PostConstruct
    public void init() {
        if (uzytkownik == null) {
            String nick = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
            if (!nick.isEmpty()) {
                uzytkownik = bazaDanych.getUzytkownik(nick);
                stan = bazaDanych.getMiejsca(uzytkownik);
                wiadomosci = bazaWiadomosci.get(uzytkownik.getId());
            }
        }

    }

    public void updateWiadomosci(){
        wiadomosci.addAll(bazaWiadomosci.get(uzytkownik.getId()));
    }

    public void updateStan(){
        stan = bazaDanych.getMiejsca(uzytkownik);
    }

    public UzytkownikPOJO getUzytkownik() {
        return uzytkownik;
    }

    public void setUzytkownik(UzytkownikPOJO uzytkownik) {
        this.uzytkownik = uzytkownik;
    }

    public List<MiejscePOJO> getStan() {
        return stan;
    }

    public void setStan(List<MiejscePOJO> stan) {
        this.stan = stan;
    }

    public List<String> getWiadomosci() {
        return wiadomosci;
    }

    public void setWiadomosci(List<String> wiadomosci) {
        this.wiadomosci = wiadomosci;
    }
}
