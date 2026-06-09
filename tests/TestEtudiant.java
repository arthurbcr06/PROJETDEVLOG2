import fr.eseo.e3e.projet2.formulaires.Cursus;
import fr.eseo.e3e.projet2.formulaires.Etudiant;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestEtudiant {

    @Test
    void testConstructeurComplet() {
        Etudiant e = new Etudiant("Beucher", "Arthur", 22222, Cursus.E2);

        assertEquals("Beucher", e.getNom());
        assertEquals("Arthur", e.getPrenom());
        assertEquals(22222, e.getNumeroApprenant());
        assertEquals(Cursus.E2, e.getCursus());
    }

    @Test
    void testConstructeurParDefaut() {
        Etudiant e = new Etudiant();

        assertNull(e.getNom());
        assertNull(e.getPrenom());
        assertNull(e.getCursus());
    }

    @Test
    void testSetNom() {
        Etudiant e = new Etudiant();
        e.setNom("Dupont");
        assertEquals("Dupont", e.getNom());
    }

    @Test
    void testSetPrenom() {
        Etudiant e = new Etudiant();
        e.setPrenom("Jean");
        assertEquals("Jean", e.getPrenom());
    }

    @Test
    void testSetNumeroApprenant() {
        Etudiant e = new Etudiant();
        e.setNumeroApprenant(12345);
        assertEquals(12345, e.getNumeroApprenant());
    }

    @Test
    void testSetCursus() {
        Etudiant e = new Etudiant();
        e.setCursus(Cursus.E3e);
        assertEquals(Cursus.E3e, e.getCursus());
    }

    @Test
    void testEqualsBaseesSurNumeroApprenant() {
        Etudiant e1 = new Etudiant("Dupont", "Jean", 100, Cursus.E1);
        Etudiant e2 = new Etudiant("Martin", "Marie", 100, Cursus.E4);
        Etudiant e3 = new Etudiant("Dupont", "Jean", 101, Cursus.E1);

        assertEquals(e1, e2);
        assertNotEquals(e1, e3);
    }

    @Test
    void testHashCodeBaseSurNumeroApprenant() {
        Etudiant e1 = new Etudiant("A", "A", 100, Cursus.E1);
        Etudiant e2 = new Etudiant("B", "B", 100, Cursus.E4);

        assertEquals(e1.hashCode(), e2.hashCode());
    }

    @Test
    void testEqualsAvecNull() {
        Etudiant e = new Etudiant("A", "A", 100, Cursus.E1);

        assertNotEquals(e, null);
    }

    @Test
    void testEqualsAvecAutreType() {
        Etudiant e = new Etudiant("A", "A", 100, Cursus.E1);
        String s = "Etudiant";

        assertNotEquals(e, s);
    }

    @Test
    void testEqualsAvecMemeObjet() {
        Etudiant e = new Etudiant("A", "A", 100, Cursus.E1);

        assertEquals(e, e);
    }
}
