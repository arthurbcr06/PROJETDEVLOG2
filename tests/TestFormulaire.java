import fr.eseo.e3e.projet2.formulaires.*;
import fr.eseo.e3e.projet2.formulaires.fraudes.FraudeCalculatrice;
import fr.eseo.e3e.projet2.formulaires.fraudes.FraudeIAG;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestFormulaire {

    @Test
    void testConstructeurParDefaut() {
        Formulaire f = new Formulaire();

        assertTrue(f.getIdentifiantNumeriqueUnique() > 0);
        assertNotNull(f.getDateCreation());
        assertNotNull(f.getDateDerniereModification());
        assertNotNull(f.getEtudiants());
        assertNotNull(f.getFraudes());
        assertTrue(f.getEtudiants().isEmpty());
        assertTrue(f.getFraudes().isEmpty());
    }

    @Test
    void testConstructeurParDefautIncrementeId() {
        Formulaire f1 = new Formulaire();
        Formulaire f2 = new Formulaire();

        assertTrue(f2.getIdentifiantNumeriqueUnique() > f1.getIdentifiantNumeriqueUnique());
    }

    @Test
    void testConstructeurComplet() {
        Epreuve ep = new Epreuve("POO", LocalDate.of(2026, 6, 3), LocalTime.of(9, 0), Duration.ofMinutes(120), Modalite.ECRIT);
        Etudiant e = new Etudiant("Beucher", "Arthur", 22222, Cursus.E2);
        FraudeIAG fraude = new FraudeIAG(LocalDate.now(), "Usage ChatGPT", "copie écran", "ChatGPT");

        Formulaire f = new Formulaire(ep, List.of(e), List.of(fraude), null);

        assertEquals(ep, f.getEpreuve());
        assertEquals(1, f.getEtudiants().size());
        assertEquals(e, f.getEtudiants().get(0));
        assertEquals(1, f.getFraudes().size());
        assertEquals(fraude, f.getFraudes().get(0));
        assertNull(f.getSanction());
    }

    @Test
    void testConstructeurAvecListesNull() {
        Epreuve ep = new Epreuve("DEVLO", LocalDate.of(2026, 6, 4), LocalTime.of(14, 0), Duration.ofMinutes(60), Modalite.QCM);

        Formulaire f = new Formulaire(ep, null, null, null);

        assertEquals(ep, f.getEpreuve());
        assertTrue(f.getEtudiants().isEmpty());
        assertTrue(f.getFraudes().isEmpty());
    }

    @Test
    void testAjouterEtudiant() {
        Formulaire f = new Formulaire();
        Etudiant e1 = new Etudiant("A", "A", 1, Cursus.E1);
        Etudiant e2 = new Etudiant("B", "B", 2, Cursus.E2);

        f.ajouterEtudiant(e1);
        assertEquals(1, f.getEtudiants().size());

        f.ajouterEtudiant(e2);
        assertEquals(2, f.getEtudiants().size());
        assertTrue(f.getEtudiants().contains(e1));
        assertTrue(f.getEtudiants().contains(e2));
    }

    @Test
    void testAjouterFraude() {
        Formulaire f = new Formulaire();
        FraudeIAG f1 = new FraudeIAG(LocalDate.now(), "desc1", "cont1", "ChatGPT");
        FraudeCalculatrice f2 = new FraudeCalculatrice(LocalDate.now(), "desc2", "cont2", "Casio", "PROG");

        f.ajouterFraude(f1);
        assertEquals(1, f.getFraudes().size());

        f.ajouterFraude(f2);
        assertEquals(2, f.getFraudes().size());
        assertTrue(f.getFraudes().contains(f1));
        assertTrue(f.getFraudes().contains(f2));
    }

    @Test
    void testSetEpreuve() {
        Formulaire f = new Formulaire();
        Epreuve ep = new Epreuve("POO", LocalDate.of(2026, 6, 3), LocalTime.of(9, 0), Duration.ofMinutes(120), Modalite.ECRIT);

        f.setEpreuve(ep);
        assertEquals(ep, f.getEpreuve());
    }

    @Test
    void testSetSanction() {
        Formulaire f = new Formulaire();
        Sanction s = new Sanction(TypeSanction.AVERTISSEMENT, LocalDate.now(), "Test");

        f.setSanction(s);
        assertEquals(s, f.getSanction());
    }

    @Test
    void testGetDateCreation() {
        Formulaire f1 = new Formulaire();
        LocalDate dateCreation = f1.getDateCreation();

        assertTrue(dateCreation.isBefore(LocalDate.now()) || dateCreation.equals(LocalDate.now()));
    }

    @Test
    void testDateDerniereModificationMisAJourQuandAjout() {
        Formulaire f = new Formulaire();
        LocalDate dateInit = f.getDateDerniereModification();

        f.ajouterEtudiant(new Etudiant("A", "A", 1, Cursus.E1));

        LocalDate dateModif = f.getDateDerniereModification();
        assertTrue(dateModif.isEqual(dateInit) || dateModif.isAfter(dateInit));
    }
}
