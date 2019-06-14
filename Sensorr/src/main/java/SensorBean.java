import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class SensorBean {

    private int id;

    public void ustawZajete(int id){
        Klient.ustawZajete(id);
    }

    public void ustawWolne(int id){
        Klient.ustawWolne(id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
