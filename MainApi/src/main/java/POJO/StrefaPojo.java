package POJO;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Strefa")
public class StrefaPojo implements Serializable {
    @Id
    @GeneratedValue
    @Column(nullable = false)
    private int id;

    @OneToMany(mappedBy = "strefa", orphanRemoval = true, cascade = CascadeType.REMOVE)
    private List<MiejscePOJO> miejsca = new ArrayList<MiejscePOJO>();

    @OneToOne
    @JoinColumn(name = "Uzytkownik_id")
    private UzytkownikPOJO uzytkownik;

    public StrefaPojo() {super();
    }

    public StrefaPojo(UzytkownikPOJO uzytkownik) {
        this.uzytkownik = uzytkownik;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<MiejscePOJO> getMiejsca() {
        return miejsca;
    }

    public void setMiejsca(List<MiejscePOJO> miejsca) {
        this.miejsca = miejsca;
    }

    public UzytkownikPOJO getUzytkownik() {
        return uzytkownik;
    }

    public void setUzytkownik(UzytkownikPOJO uzytkownik) {
        this.uzytkownik = uzytkownik;
    }
}
