package Scheduler;

import DAO.Miejsce;
import POJO.MiejscePOJO;
import Warning.Warningi;

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
        if (Miejsce.warningOK(miejsce)) manager.warn(miejsce);
    }
}
