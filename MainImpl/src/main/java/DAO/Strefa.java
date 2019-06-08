package DAO;


import POJO.StrefaPojo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class Strefa {
    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("Baza");;
    private static EntityManager em = factory.createEntityManager();

    public static void add(StrefaPojo x){
        try {
            em.getTransaction().begin();
            em.persist(x);
            em.getTransaction().commit();
            System.out.println("Dodano do bazy " + x.getId());
        }
        catch(Exception e) {
            System.out.println("Nie mozna dodac do bazy " + e);
        }
    }

    public static StrefaPojo get(int id){
        Query q = em.createQuery("FROM Strefa where id=:id", StrefaPojo.class);
        q.setParameter("id",id);
        List uzytkownik = new ArrayList<StrefaPojo>();
        try {
            uzytkownik = q.getResultList();
        }
        catch (Exception e){
            System.out.println("Nie mozna pobrac z bazy "+e);
        }
        if (uzytkownik.size()==1) return (StrefaPojo) uzytkownik.get(0);
        else return null;
    }

    public static void delete(int id) {
        try {
            StrefaPojo uzytkownik = em.find(StrefaPojo.class, id);
            em.getTransaction().begin();
            em.remove(uzytkownik);
            em.getTransaction().commit();
        }
        catch (Exception e){
            System.out.println("Nie mozna usunac z bazy "+e);
        }
    }


}
