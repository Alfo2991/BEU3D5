package catalogo;

import java.io.BufferedWriter;
import java.io.IOException;

import javax.persistence.*;

@Entity
public class Libro extends ElementoCatalogo {
    private String autore;
    private String genere;

    public Libro(String codiceISBN, String titolo, int annoPubblicazione, int numeroPagine, String autore, String genere) {
        super(codiceISBN, titolo, annoPubblicazione, numeroPagine);
        this.autore = autore;
        this.genere = genere;
    }

    public Libro() {
    }

    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    @Override
    public String getIsbn() {
        return getCodiceISBN();
    }

    @Override
    public void scriviSuFile(BufferedWriter writer) throws IOException {
        writer.write("Tipo: Libro");
        writer.newLine();
        writer.write("ISBN: " + getCodiceISBN());
        writer.newLine();
        writer.write("Titolo: " + getTitolo());
        writer.newLine();
        writer.write("Anno di pubblicazione: " + getAnnoPubblicazione());
        writer.newLine();
        writer.write("Numero di pagine: " + getNumeroPagine());
        writer.newLine();
        writer.write("Autore: " + autore);
        writer.newLine();
        writer.write("Genere: " + genere);
        writer.newLine();
    }

    @Override
    public String toString() {
        return super.toString() +
               ", Autore: " + autore +
               ", Genere: " + genere;
    }
}
