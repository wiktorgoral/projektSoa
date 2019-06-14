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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;


@Local(WarningiLocal.class)
@Remote(WarningiRemote.class)
@Singleton
public class Alert implements WarningiLocal, WarningiRemote {

    @EJB
    private Sender sender;
    private Timer czas = new Timer();

    public void dodajBilet(BiletPOJO bilet){
        Date data = new Date();
        SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        String dateString = form.format(data);
        System.out.println(dateString);
        sender.sendMessage("Date:"+dateString);
        czas.schedule(new CheckSpot(bilet.getMiejsce().getId(),this), bilet.getKoniec());
    }

    public void warn(MiejscePOJO miejsce) {
        int uzytkownikId = miejsce.getStrefa().getUzytkownik().getId();
        sender.sendMessage(uzytkownikId+":"+miejsce.getId());
    }

    public void sprawdzMiejsce(int id) {
        Date data = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        String dataString = format.format(data);
        sender.sendMessage("Data:"+dataString);
        Calendar date = Calendar.getInstance();
        long dataCalendar = date.getTimeInMillis();
        Date czasSprawdzenia = new Date(dataCalendar + (3 * 60000));
        czas.schedule(new CheckSpot(id, this), czasSprawdzenia);
    }

    public void wyslij(String text){ sender.sendMessage(text);}


}
