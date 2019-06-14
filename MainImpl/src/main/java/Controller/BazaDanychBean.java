package Controller;

import DAO.Miejsce;
import DAO.Uzytkownik;
import POJO.MiejscePOJO;
import POJO.Spot;
import POJO.UzytkownikPOJO;
import Warning.local.BazaDanychLocal;
import Warning.remote.BazaDanychRemote;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Singleton;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Local(BazaDanychLocal.class)
@Remote(BazaDanychRemote.class)
@Singleton
public class BazaDanychBean implements BazaDanychLocal, BazaDanychRemote{

    public UzytkownikPOJO getUzytkownik(String name) {
        return Uzytkownik.get(name);
    }

    public List<Spot> getMiejsca(UzytkownikPOJO uzytkownik){
        if (uzytkownik.getNick().equals("admin")){
            List<MiejscePOJO> miejsca = Miejsce.getAll();
            List<Spot> spots = new ArrayList<Spot>();

            for (int i=0;i<miejsca.size();i++){
                Spot nowy = new Spot();
                nowy.setId(miejsca.get(i).getId());
                nowy.setStrefa(miejsca.get(i).getStrefa().getId());
                nowy.setOplacone(Miejsce.oplacone(miejsca.get(i)));
                nowy.setWolne(miejsca.get(i).getWolne());
                spots.add(nowy);
            }
            return spots;
        }
        else {
            List<MiejscePOJO> miejsca = Miejsce.getByUzytkownik(uzytkownik.getId());
            List<Spot> spots = new ArrayList<Spot>();

            for (int i=0;i<miejsca.size();i++){
                Spot nowy = new Spot();
                nowy.setId(miejsca.get(i).getId());
                nowy.setStrefa(miejsca.get(i).getStrefa().getId());
                nowy.setOplacone(Miejsce.oplacone(miejsca.get(i)));
                nowy.setWolne(miejsca.get(i).getWolne());
                spots.add(nowy);
            }
            return spots;
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
