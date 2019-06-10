package DAO;


import POJO.BiletPOJO;
import POJO.MiejscePOJO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class Bilet {
    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("Baza");
    private static EntityManager em = factory.createEntityManager();

    public static void add(BiletPOJO x){
        try {
            em.getTransaction().begin();
            em.persist(x);
            MiejscePOJO miejsce = em.find(MiejscePOJO.class, x.getMiejsce().getId());
            List<BiletPOJO> bilety = miejsce.getBilety();
            bilety.add(x);
            miejsce.setBilety(bilety);
            em.persist(miejsce);
            em.getTransaction().commit();
            System.out.println("Dodano do bazy " + x.getId());
        }
        catch(Exception e) {
            System.out.println("Nie mozna dodac do bazy " + e);
        }
    }

    public static BiletPOJO get(int id){
        Query q = em.createQuery("FROM Bilet where id=:id", BiletPOJO.class);
        q.setParameter("id",id);
        List bilet = new ArrayList<BiletPOJO>();
        try {
            bilet = q.getResultList();
        }
        catch (Exception e){
            System.out.println("Nie mozna pobrac z bazy "+e);
        }
        if (bilet.size()==1) return (BiletPOJO) bilet.get(0);
        else return null;
    }

    public static void delete(int id){
        try {
            BiletPOJO bilet = em.find(BiletPOJO.class, id);
            em.getTransaction().begin();
            em.remove(bilet);
            em.getTransaction().commit();
        }
        catch (Exception e){
            System.out.println("Nie mozna usunac z bazy "+e);
        }
    }

    public static List<BiletPOJO> getAll(){
        List<BiletPOJO> uzytkownicy = new ArrayList<BiletPOJO>();
        try {
            Query q = em.createQuery("FROM Bilet ", BiletPOJO.class);
            uzytkownicy = q.getResultList();
        } catch (Exception e) {
            System.out.println("Nie mozna pobrac z bazy " + e);
        }
        return uzytkownicy;
    }

}
