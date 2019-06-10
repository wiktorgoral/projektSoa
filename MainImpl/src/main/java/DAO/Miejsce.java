package DAO;


import POJO.BiletPOJO;
import POJO.MiejscePOJO;
import POJO.StrefaPojo;
import POJO.UzytkownikPOJO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Miejsce {
    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("Baza");;
    private static EntityManager em = factory.createEntityManager();

    public static void add(MiejscePOJO x) {
        try {
            em.getTransaction().begin();
            em.persist(x);
            StrefaPojo strefa = em.find(StrefaPojo.class, x.getStrefa().getId());
            List<MiejscePOJO> miejsca = strefa.getMiejsca();
            miejsca.add(x);
            strefa.setMiejsca(miejsca);
            em.persist(strefa);
            em.getTransaction().commit();
            System.out.println("Dodano do bazy " + x.getId());
        }
        catch(Exception e) {
            System.out.println("Nie mozna dodac do bazy " + e);
        }
    }

    public static List<MiejscePOJO> getAll() {
        List<MiejscePOJO> spots = new ArrayList<MiejscePOJO>();
        try {
            Query q = em.createQuery("FROM Miejsce", MiejscePOJO.class);
            spots = q.getResultList();
        } catch (Exception e) {
            System.out.println("Nie mozna pobrac z bazy " + e);
        }
        return spots;
    }

    public static MiejscePOJO get(int id) {
        Query q = em.createQuery("FROM Bilet where id=:id", MiejscePOJO.class);
        q.setParameter("id",id);
        List miejsce = new ArrayList<MiejscePOJO>();
        try {
            miejsce = q.getResultList();
        }
        catch (Exception e){
            System.out.println("Nie mozna pobrac z bazy "+e);
        }
        if (miejsce.size()==1) return (MiejscePOJO) miejsce.get(0);
        else return null;
    }

    public static void delete(int id){
        try {
            MiejscePOJO miejsce = em.find(MiejscePOJO.class, id);
            em.getTransaction().begin();
            em.remove(miejsce);
            em.getTransaction().commit();
        }
        catch (Exception e){
            System.out.println("Nie mozna usunac z bazy "+e);
        }
    }

    public static void ustawStan(int id, boolean stan){
        try {
            MiejscePOJO miejsce = em.find(MiejscePOJO.class, id);
            Date data = new Date();
            long czas = data.getTime();
            Timestamp stamp = new Timestamp(czas);

            if (stan == false) {
                em.getTransaction().begin();
                miejsce.setWolne(stan);
                miejsce.setPrzyjazd(stamp);
                em.getTransaction().commit();
            }
            else {
                em.getTransaction().begin();
                miejsce.setWolne(stan);
                miejsce.setPrzyjazd(null);
                em.getTransaction().commit();
            }
        }
        catch (Exception e){
            System.out.println("Nie mozna ustawic w bazie "+e);
        }
    }

    public static List<MiejscePOJO> getByUzytkownik(int id) {
        UzytkownikPOJO uzytkownik = Uzytkownik.get(id);
        StrefaPojo strefa = uzytkownik.getStrefa();
        List<MiejscePOJO> miejsca = new ArrayList<MiejscePOJO>();
        Query q = em.createQuery("FROM Miejsce where strefa=:strefa", MiejscePOJO.class);
        q.setParameter("strefa",strefa);
        try {
            miejsca = q.getResultList();
        }
        catch (Exception e) {
            System.out.println("Nie mozna pobrac z bazy " + e);
        }
        return miejsca;
    }

    public static boolean oplacone(MiejscePOJO x){
        List<BiletPOJO> bilety = x.getBilety();
        Date now = new Date();
        for (BiletPOJO bilet : bilety) {
            if (bilet.getKoniec().after(now))
                return true;
        }
        return false;
    }

    public static BiletPOJO getOstatniBilet(int miejsceId){
        MiejscePOJO miejsce = em.find(MiejscePOJO.class, miejsceId);
        List<BiletPOJO> bilety = miejsce.getBilety();
        BiletPOJO ostatni = new BiletPOJO();
        ostatni.setPoczatek( new Timestamp(System.currentTimeMillis()));
        for (int i=0;i<bilety.size();i++){
            if(bilety.get(i).getPoczatek().before(ostatni.getPoczatek()))
                ostatni=bilety.get(i);
        }
        return ostatni;
    }

    public static boolean warningOK(MiejscePOJO miejsce) {
        if (miejsce.getWolne())
            return false;

        if (Miejsce.oplacone(miejsce))
            return false;


        if (getOstatniBilet(miejsce.getId()).getKoniec().before(new Timestamp(System.currentTimeMillis())))
            return true;

        return false;
    }
}
