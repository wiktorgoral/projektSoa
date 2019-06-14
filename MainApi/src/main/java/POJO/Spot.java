package POJO;

import java.io.Serializable;

public class Spot implements Serializable {
    private int id;
    private int strefa;
    private boolean wolne;
    private boolean oplacone;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStrefa() {
        return strefa;
    }

    public void setStrefa(int strefa) {
        this.strefa = strefa;
    }

    public boolean isWolne() {
        return wolne;
    }

    public void setWolne(boolean wolne) {
        this.wolne = wolne;
    }

    public boolean isOplacone() {
        return oplacone;
    }

    public void setOplacone(boolean oplacone) {
        this.oplacone = oplacone;
    }
}
