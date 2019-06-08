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

    @OneToMany(mappedBy = "zone", orphanRemoval = true, cascade = CascadeType.REMOVE)
    private List<MiejscePOJO> parkingSpots = new ArrayList<MiejscePOJO>();

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

    public List<MiejscePOJO> getParkingSpots() {
        return parkingSpots;
    }

    public void setParkingSpots(List<MiejscePOJO> parkingSpots) {
        this.parkingSpots = parkingSpots;
    }

    public UzytkownikPOJO getUzytkownik() {
        return uzytkownik;
    }

    public void setUzytkownik(UzytkownikPOJO uzytkownik) {
        this.uzytkownik = uzytkownik;
    }
}