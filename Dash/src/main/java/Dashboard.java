
import Controller.BazaDanych;
import DAO.Miejsce;
import DAO.Uzytkownik;

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

    private Uzytkownik user;

    private List<Miejsce> stan;

    @EJB(lookup = "java:global/MainImpl/BazaDanych")
    BazaDanych controller;

    @PostConstruct
    public void init() {
        if (user == null) {
            String nick = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
            if (!nick.isEmpty()) {
                user = BazaDanych.pobierzUzytkownika(nick);
            }
        }
    }

    public Uzytkownik getUser() {
        return user;
    }

    public void setUser(Uzytkownik user) {
        this.user = user;
    }

    public List<Miejsce> getStan() {
        stan = controller.pobierzStan(user);
        return stan;
    }

    public void setStan(List<Miejsce> stan) {
        this.stan = stan;
    }
}
