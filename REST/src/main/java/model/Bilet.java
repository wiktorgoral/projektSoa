package model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;
import java.util.UUID;


@XmlRootElement(name="Bilet")
public class Bilet {
    private String id=  UUID.randomUUID().toString();
    private LocalDateTime time;
    private int place;

    public Bilet(){super();}

    public Bilet(LocalDateTime time, int place) {
        this.id = id;
        this.time = time;
        this.place = place;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }
}
