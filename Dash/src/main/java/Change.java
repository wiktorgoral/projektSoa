import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@ManagedBean(name = "Change")
@RequestScoped
public class Change {

    private String user;
    private String error;
    private String neww;
    private String oldd;
    private String con;

    public void change() {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("JPA-Zajecia");
        EntityManager em = factory.createEntityManager();
        Query q = em.createQuery("SELECT h.passwd FROM users h where h.login =:login").setParameter("login", FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());
        String res = (String) q.getSingleResult();


        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (
                NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        md.update(oldd.getBytes());
        byte[] digest = md.digest();
        String myHash = DatatypeConverter.printBase64Binary(digest);

        if (!res.equals(myHash)) {
            error = "Zle haslo";
        }
        else if (!con.equals(neww)) {
            error = "Nie takie same hasla";
        }
        else {
            em.getTransaction().begin();
            q = em.createQuery("UPDATE users e SET e.passwd=:pass where e.login=:login");
            q.setParameter("login", FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());
            md.update(neww.getBytes());
            digest = md.digest();
            myHash = DatatypeConverter.printBase64Binary(digest);
            q.setParameter("pass",myHash);
            int row = q.executeUpdate();
            em.getTransaction().commit();
            em.close();
            error="Haslo zmienione";
        }

    }

    public void changeadmin() {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("JPA-Zajecia");
        EntityManager em = factory.createEntityManager();
        Query q = em.createQuery("FROM users h where h.login =:login").setParameter("login", user);
        List results = q.getResultList();
        if (results.isEmpty()) {
            error="Brak danego usera";
            return;
        }
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (
                NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        md.update(neww.getBytes());
        byte[] digest = md.digest();
        String myHash = DatatypeConverter.printBase64Binary(digest);
        if (!con.equals(neww)) {
            error = "Nie takie same hasla";
        }
        else {
            em.getTransaction().begin();
            q = em.createQuery("UPDATE users e SET e.passwd=:pass where e.login=:login");
            q.setParameter("login", FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());
            q.setParameter("pass",myHash);
            int row = q.executeUpdate();
            em.getTransaction().commit();
            em.close();
            error="Haslo zmienione";
        }

    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getNeww() {
        return neww;
    }

    public void setNeww(String neww) {
        this.neww = neww;
    }

    public String getOldd() {
        return oldd;
    }

    public void setOldd(String oldd) {
        this.oldd = oldd;
    }

    public String getCon() {
        return con;
    }

    public void setCon(String con) {
        this.con = con;
    }
}
