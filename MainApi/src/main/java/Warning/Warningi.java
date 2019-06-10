package Warning;

import POJO.BiletPOJO;
import POJO.MiejscePOJO;

public interface Warningi {

    void dodajBilet(BiletPOJO bilet);
    void warn(MiejscePOJO miejsce);
    void sprawdzMiejsce(int id);
}
