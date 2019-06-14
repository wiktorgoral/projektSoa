package Scheduler;

import DAO.Miejsce;
import POJO.MiejscePOJO;
import Warning.Warningi;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;

public class CheckSpot extends TimerTask {
    private int miejsceId;
    private Warningi manager;

    public CheckSpot(int miejsceId, Warningi manager) {
        this.miejsceId = miejsceId;
        this.manager = manager;
    }

    public void run(){

        MiejscePOJO miejsce = Miejsce.get(miejsceId);
        if (Miejsce.warningOK(miejsce))
            manager.warn(miejsce);
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        String dataString = format.format(date);
        manager.wyslij("Data:"+dataString);
    }
}
