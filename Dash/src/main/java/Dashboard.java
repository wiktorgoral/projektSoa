
import Controller.BazaDanych;
import POJO.MiejscePOJO;
import POJO.UzytkownikPOJO;


import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import java.io.Serializable;
import java.util.List;

@ManagedBean(name = "Dashboard")
@SessionScoped
public class Dashboard implements Serializable {

    private UzytkownikPOJO uzytkownik;

    private List<MiejscePOJO> stan;

    @EJB(lookup = "java:global/MainImpl/BazaDanych")
    BazaDanych controller;

    @PostConstruct
    public void init() {
        if (uzytkownik == null) {
            String nick = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
            if (!nick.isEmpty()) {
                uzytkownik = controller.pobierzUzytkownika(nick);
            }
        }
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
}
