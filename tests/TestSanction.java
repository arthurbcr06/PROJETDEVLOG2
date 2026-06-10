import fr.eseo.e3e.projet2.formulaires.Sanction;
import fr.eseo.e3e.projet2.formulaires.TypeSanction;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class TestSanction {

    @Test
    void testConstructeurParDefaut() {
        Sanction s = new Sanction();

        assertNull(s.getType());
        assertNotNull(s.getDateDecision());
        assertNull(s.getCommentaire());
    }

    @Test
    void testConstructeurComplet() {
        LocalDate date = LocalDate.of(2026, 6, 10);
        Sanction s = new Sanction(TypeSanction.AVERTISSEMENT, date, "Copie partielle");

        assertEquals(TypeSanction.AVERTISSEMENT, s.getType());
        assertEquals(date, s.getDateDecision());
        assertEquals("Copie partielle", s.getCommentaire());
    }

    @Test
    void testSettersEtGetters() {
        Sanction s = new Sanction();

        LocalDate date = LocalDate.of(2026, 6, 11);
        s.setType(TypeSanction.EXCLUSION_SESSION);
        s.setDateDecision(date);
        s.setCommentaire("Fraude avérée");

        assertEquals(TypeSanction.EXCLUSION_SESSION, s.getType());
        assertEquals(date, s.getDateDecision());
        assertEquals("Fraude avérée", s.getCommentaire());
    }
}
