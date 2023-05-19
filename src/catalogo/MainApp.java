package catalogo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;
import java.util.function.Predicate;

public class MainApp {
    public static void main(String[] args) {
        Libro libro1 = new Libro("5225525255252", "Il nome della rosa", 1900, 300, "Umberto Eco", "Romanzo");
        Libro libro2 = new Libro("4114141441414", "Harry Potter e la pietra filosofale", 2000, 500, "J.K. Rowling", "Fantasy");
        Libro libro3 = new Libro("6363636363663", "1984", 1904, 500, "George Orwell", "Romanzo distopico");

        Rivista rivista1 = new Rivista("123456789", "National Geographic", 2021, 100, "Scienza");
        Rivista rivista2 = new Rivista("987654321", "Vanity Fair", 2022, 50, "Moda");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EsercizioU3D5");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        Catalogo catalogo = new Catalogo(em);

        catalogo.aggiungiLibro(libro1);
        catalogo.aggiungiLibro(libro2);
        catalogo.aggiungiLibro(libro3);

        catalogo.aggiungiRivista(rivista1);
        catalogo.aggiungiRivista(rivista2);

        String autoreRicerca = "Umberto Eco";
        System.out.println("Risultati ricerca per autore: " + autoreRicerca);
        List<Libro> risultatiLibri = catalogo.cercaLibro(libro -> libro.getAutore().equals(autoreRicerca));
        risultatiLibri.forEach(System.out::println);

        int annoRicerca = 2022;
        System.out.println("Risultati ricerca per anno di pubblicazione: " + annoRicerca);
        List<Rivista> risultatiRiviste = catalogo.cercaRivista(rivista -> rivista.getAnnoPubblicazione() == annoRicerca);
        risultatiRiviste.forEach(System.out::println);

        Catalogo catalogo1 = new Catalogo(em);

        Libro libro = new Libro("ISBN456", "Titolo Libro", 2023, 200, "Autore Libro", "Genere Libro");
        catalogo1.aggiungiLibro(libro);

        try {
            tx.begin();
            catalogo.salvaCatalogoSuDatabase();
            tx.commit();
            System.out.println("Catalogo salvato correttamente nel database.");
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            System.out.println("Si è verificato un errore durante il salvataggio del catalogo nel database.");
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}
