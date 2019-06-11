package Warning;

import java.util.Date;
import java.util.List;

public interface Wiadomosci {

    void dodaj(String text);
    List<String> get(int id);
    Date ostatni();
}
