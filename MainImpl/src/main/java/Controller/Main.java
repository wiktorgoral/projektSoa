package Controller;

import Other.Hash;
import POJO.BiletPOJO;
import POJO.MiejscePOJO;
import POJO.StrefaPojo;
import POJO.UzytkownikPOJO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("Baza");
        EntityManager em = factory.createEntityManager();
        try{
            BiletPOJO bilet = new BiletPOJO();
            StrefaPojo strefa2 = new StrefaPojo();
            StrefaPojo strefa1 = new StrefaPojo();
            UzytkownikPOJO uzytkownik1 = new UzytkownikPOJO("Nowak", Hash.hash("Nowak"), strefa1);
            UzytkownikPOJO uzytkownik2 = new UzytkownikPOJO("Kowalski", Hash.hash("Kowalski"),strefa2);
            UzytkownikPOJO admin = new UzytkownikPOJO();
            admin.setNick("admin");
            admin.setPassword(Hash.hash("admin"));
            strefa2.setUzytkownik(uzytkownik2);
            strefa1.setUzytkownik(uzytkownik1);
            em.getTransaction().begin();
            for (int i=0;i<10;i++) {

                MiejscePOJO miejsce = new MiejscePOJO(true, strefa1);
                em.persist(miejsce);
                List<MiejscePOJO> miejsca = strefa1.getMiejsca();
                miejsca.add(miejsce);
                strefa1.setMiejsca(miejsca);
            }
            for (int i=0;i<10;i++) {
                MiejscePOJO miejsce = new MiejscePOJO(true, strefa2);
                em.persist(miejsce);
                List<MiejscePOJO> miejsca = strefa2.getMiejsca();
                miejsca.add(miejsce);
                strefa2.setMiejsca(miejsca);
            }

            em.persist(strefa1);
            em.persist(strefa2);
            em.persist(uzytkownik1);
            em.persist(uzytkownik2);
            em.persist(admin);

            em.getTransaction().commit();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
