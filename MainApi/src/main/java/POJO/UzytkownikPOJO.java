package POJO;


import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "Uzytkownik")
public class UzytkownikPOJO implements Serializable {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private int id;

    @Column(nullable = false)
    private String nick;

    @Column(nullable = false)
    private String password;

    @OneToOne
    @JoinColumn(name = "zone_id")
    private StrefaPojo strefa;

    public UzytkownikPOJO() {
    }

    public UzytkownikPOJO(String nick, String password, StrefaPojo strefa) {
        this.nick = nick;
        this.password = password;
        this.strefa = strefa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public StrefaPojo getStrefa() {
        return strefa;
    }

    public void setStrefa(StrefaPojo strefa) {
        this.strefa = strefa;
    }
}
