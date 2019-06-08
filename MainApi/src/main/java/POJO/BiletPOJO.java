package POJO;


import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity(name = "Bilet")
public class BiletPOJO implements Serializable {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private int id;

    @Column(nullable = false)
    private Timestamp poczatek;

    @Column(nullable = false)
    private Timestamp koniec;

    @ManyToOne
    private MiejscePOJO miejsce;

    public BiletPOJO() {super();
    }

    public BiletPOJO(Timestamp poczatek, Timestamp koniec, MiejscePOJO miejsce) {
        this.poczatek = poczatek;
        this.koniec = koniec;
        this.miejsce = miejsce;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getPoczatek() {
        return poczatek;
    }

    public void setPoczatek(Timestamp poczatek) {
        this.poczatek = poczatek;
    }

    public Timestamp getKoniec() {
        return koniec;
    }

    public void setKoniec(Timestamp koniec) {
        this.koniec = koniec;
    }

    public MiejscePOJO getMiejsce() {
        return miejsce;
    }

    public void setMiejsce(MiejscePOJO miejsce) {
        this.miejsce = miejsce;
    }
}
