package Scheduler;

import Other.Sender;
import POJO.BiletPOJO;
import POJO.MiejscePOJO;
import Warning.local.WarningiLocal;
import Warning.remote.WarningiRemote;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Singleton;
import java.util.Timer;


@Local(WarningiLocal.class)
@Remote(WarningiRemote.class)
@Singleton
public class Alert implements WarningiLocal, WarningiRemote {

    @EJB
    private Sender sender;
    private Timer czas = new Timer();

    public void dodajBilet(BiletPOJO bilet){
        czas.schedule(new CheckSpot(bilet.getMiejsce().getId(),this), bilet.getKoniec());
    }

    public void warn(MiejscePOJO miejsce) {

    }

    public void sprawdzMiejsce(int id) {

    }


}
