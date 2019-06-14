package Warning;

import POJO.Spot;
import POJO.UzytkownikPOJO;

import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface BazaDanych {

    UzytkownikPOJO getUzytkownik(String name);

     List<Spot> getMiejsca(UzytkownikPOJO uzytkownik);

     void changePassword(int id, String oldPassword, String newPassword)throws NoSuchAlgorithmException;

     List<UzytkownikPOJO> getAllUzytkownicy();
}
