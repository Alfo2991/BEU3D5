package catalogo;

import java.io.BufferedWriter;
import java.io.IOException;

import javax.persistence.*;

@Entity
public class Rivista extends ElementoCatalogo {
    private String periodicita;

    public Rivista(String codiceISBN, String titolo, int annoPubblicazione, int numeroPagine, String periodicita) {
        super(codiceISBN, titolo, annoPubblicazione, numeroPagine);
        this.periodicita = periodicita;
    }

    public String getPeriodicita() {
        return periodicita;
    }

    public void setPeriodicita(String periodicita) {
        this.periodicita = periodicita;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", Periodicità: " + periodicita;
    }

    @Override
    public String getAutore() {
        return null;
    }

    @Override
    public String getIsbn() {
        return null;
    }

    @Override
    public void scriviSuFile(BufferedWriter writer) throws IOException {
        writer.write("Tipo: Rivista");
        writer.newLine();
        writer.write("Codice ISBN: " + getCodiceISBN());
        writer.newLine();
        writer.write("Titolo: " + getTitolo());
        writer.newLine();
        writer.write("Anno di pubblicazione: " + getAnnoPubblicazione());
        writer.newLine();
        writer.write("Numero di pagine: " + getNumeroPagine());
        writer.newLine();
        writer.write("Periodicità: " + periodicita);
        writer.newLine();
        writer.newLine();
    }
}
