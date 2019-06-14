package POJO;


import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Miejsce")
public class MiejscePOJO implements Serializable {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private int id;

    @Column(nullable = false, name = "Wolne")
    private Boolean wolne;

    @Column(name = "przyjazd")
    private Timestamp przyjazd;

    @ManyToOne
    private StrefaPojo strefa;

    @OneToMany(mappedBy = "miejsce", orphanRemoval = true, cascade = CascadeType.REMOVE)
    private List<BiletPOJO> Bilety = new ArrayList<BiletPOJO>();

    public MiejscePOJO() { super();
    }

    public MiejscePOJO(Boolean wolne, StrefaPojo strefa) {
        this.wolne = wolne;
        this.strefa = strefa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean getWolne() {
        return wolne;
    }

    public void setWolne(Boolean wolne) {
        this.wolne = wolne;
    }

    public Timestamp getPrzyjazd() {
        return przyjazd;
    }

    public void setPrzyjazd(Timestamp przyjazd) {
        this.przyjazd = przyjazd;
    }

    public StrefaPojo getStrefa() {
        return strefa;
    }

    public void setStrefa(StrefaPojo strefa) {
        this.strefa = strefa;
    }

    public List<BiletPOJO> getBilety() {
        return Bilety;
    }

    public void setBilety(List<BiletPOJO> bilety) {
        Bilety = bilety;
    }
}
