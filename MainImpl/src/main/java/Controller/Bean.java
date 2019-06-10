package Controller;

import DAO.Miejsce;
import DAO.Uzytkownik;
import POJO.MiejscePOJO;
import POJO.UzytkownikPOJO;
import Warning.local.BazaDanychLocal;
import Warning.remote.BazaDanychRemote;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Singleton;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Local(BazaDanychLocal.class)
@Remote(BazaDanychRemote.class)
@Singleton
public class Bean implements BazaDanychLocal, BazaDanychRemote{

    public UzytkownikPOJO getUzytkownik(String name) {
        return Uzytkownik.get(name);
    }

    public List<MiejscePOJO> getMiejsca(UzytkownikPOJO uzytkownik){
        if (uzytkownik.getNick().equals("admin")){
            return Miejsce.getAll();
        }
        else {
            return Miejsce.getByUzytkownik(uzytkownik.getId());
        }
    }

    public void changePassword(int id, String oldPassword, String newPassword) throws NoSuchAlgorithmException {
        if (!Uzytkownik.ustawHaslo(id, oldPassword, newPassword)){
            System.out.println("Podales zle haslo");
        }
    }

    public List<UzytkownikPOJO> getAllUzytkownicy(){
        return Uzytkownik.getAll();
    }


}
