package DAO;

import Other.Hash;
import POJO.StrefaPojo;
import POJO.UzytkownikPOJO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class Uzytkownik {
    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("Baza");;
    private static EntityManager em = factory.createEntityManager();

    public static void add(UzytkownikPOJO x){
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

    public static UzytkownikPOJO get(int id){
        Query q = em.createQuery("FROM Uzytkownik where id=:id", UzytkownikPOJO.class);
        q.setParameter("id",id);
        List uzytkownik = new ArrayList<UzytkownikPOJO>();
        try {
            uzytkownik = q.getResultList();
        }
        catch (Exception e){
            System.out.println("Nie mozna pobrac z bazy "+e);
        }
        if (uzytkownik.size()==1) return (UzytkownikPOJO) uzytkownik.get(0);
        else return null;
    }

    public static UzytkownikPOJO get(String nick){
        Query q = em.createQuery("FROM Uzytkownik where nick=:nick", UzytkownikPOJO.class);
        q.setParameter("nick",nick);
        List uzytkownik = new ArrayList<UzytkownikPOJO>();
        try {
            uzytkownik = q.getResultList();
        }
        catch (Exception e){
            System.out.println("Nie mozna pobrac z bazy "+e);
        }
        if (uzytkownik.size()==1) return (UzytkownikPOJO) uzytkownik.get(0);
        else return null;
    }

    public static void delete(int id) {
        try {
            UzytkownikPOJO uzytkownik = em.find(UzytkownikPOJO.class, id);
            em.getTransaction().begin();
            em.remove(uzytkownik);
            em.getTransaction().commit();
        }
        catch (Exception e){
            System.out.println("Nie mozna usunac z bazy "+e);
        }
    }

    public static boolean checkHaslo(String imie, String haslo) throws NoSuchAlgorithmException {
        UzytkownikPOJO uzytkownik = get(imie);
        haslo= Hash.hash(haslo);
        if (haslo.equals(uzytkownik.getPassword())) return true;
        else {
            System.out.println("Błędne hasło");
            return false;
        }
    }

    public static void ustawStrefa(int id, int strefaId) {
        try {
            UzytkownikPOJO uzytkownik = em.find(UzytkownikPOJO.class, id);
            StrefaPojo strefa = em.find(StrefaPojo.class, strefaId);
            em.getTransaction().begin();
            uzytkownik.setStrefa(strefa);
            em.getTransaction().commit();
        }
        catch(Exception e) {
            System.out.println("Nie mozna ustawic strefy " + e);
        }
    }

    public static boolean ustawHaslo(int id, String stareHaslo,String noweHaslo) throws NoSuchAlgorithmException {
        boolean out = false;
        try{
            String nowe = Hash.hash(noweHaslo);
            String stare = Hash.hash(stareHaslo);
            if (checkHaslo(get(id).getNick(),stare)) {
                em.getTransaction().begin();
                Query q = em.createQuery("UPDATE Uzytkownik e SET e.password=:password where id=:id");
                q.setParameter("password", nowe);
                int row = q.executeUpdate();
                em.getTransaction().commit();
                out=true;
            }
        }
        catch(Exception e) {
            System.out.println("Nie mozna zmienic hasla " + e);
        }
        return out;
    }

    public static List<UzytkownikPOJO> getAll(){
        List<UzytkownikPOJO> uzytkownicy = new ArrayList<UzytkownikPOJO>();
        try {
            Query q = em.createQuery("FROM Uzytkownik ", UzytkownikPOJO.class);
            uzytkownicy = q.getResultList();
        } catch (Exception e) {
            System.out.println("Nie mozna pobrac z bazy " + e);
        }
        return uzytkownicy;
    }

}
