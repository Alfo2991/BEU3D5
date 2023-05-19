package catalogo;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class Catalogo {
    private EntityManager entityManager;

    public Catalogo(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void aggiungiLibro(Libro libro) {
        entityManager.getTransaction().begin();
        entityManager.persist(libro);
        entityManager.getTransaction().commit();
    }

    public void aggiungiRivista(Rivista rivista) {
        entityManager.getTransaction().begin();
        entityManager.persist(rivista);
        entityManager.getTransaction().commit();
    }

    public void rimuoviElemento(String isbn) {
        entityManager.getTransaction().begin();
        Libro libro = entityManager.find(Libro.class, isbn);
        if (libro != null) {
            entityManager.remove(libro);
        } else {
            Rivista rivista = entityManager.find(Rivista.class, isbn);
            if (rivista != null) {
                entityManager.remove(rivista);
            }
        }
        entityManager.getTransaction().commit();
    }

    public List<Libro> cercaLibro(Predicate<Libro> p) {
        TypedQuery<Libro> query = entityManager.createQuery("SELECT l FROM Libro l", Libro.class);
        List<Libro> risultati = query.getResultList();
        return risultati.stream().filter(p).collect(Collectors.toList());
    }

    public List<Rivista> cercaRivista(Predicate<Rivista> p) {
        TypedQuery<Rivista> query = entityManager.createQuery("SELECT r FROM Rivista r", Rivista.class);
        List<Rivista> risultati = query.getResultList();
        return risultati.stream().filter(p).collect(Collectors.toList());
    }

    public void aggiungiPrestito(Prestito prestito) {
        entityManager.getTransaction().begin();
        entityManager.persist(prestito);
        entityManager.getTransaction().commit();
    }

    public void salvaCatalogoSuDatabase() {
        entityManager.getTransaction().begin();
        List<Libro> libri = cercaLibro(libro -> true);
        for (Libro libro : libri) {
            entityManager.merge(libro); //         }

        List<Rivista> riviste = cercaRivista(rivista -> true);
        for (Rivista rivista : riviste) {
            entityManager.merge(rivista);
        }

        entityManager.getTransaction().commit();
    }

    public void chiudi() {
        entityManager.close();
    }
}
