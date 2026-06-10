import fr.eseo.e3e.projet2.formulaires.fraudes.FraudePapier;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class TestFraudePapier {

    @Test
    void testConstructeurParDefaut() {
        FraudePapier fraude = new FraudePapier();

        assertNull(fraude.getDateReleve());
        assertNull(fraude.getDescriptionTextuelle());
        assertNull(fraude.getContenu());
        assertNull(fraude.getDimension());
        assertFalse(fraude.isPlieOuNon());
    }

    @Test
    void testConstructeurComplet() {
        LocalDate date = LocalDate.of(2026, 6, 10);
        FraudePapier fraude = new FraudePapier(date, "Feuille cachée", "formules", "A4", true);

        assertEquals(date, fraude.getDateReleve());
        assertEquals("Feuille cachée", fraude.getDescriptionTextuelle());
        assertEquals("formules", fraude.getContenu());
        assertEquals("A4", fraude.getDimension());
        assertTrue(fraude.isPlieOuNon());
    }

    @Test
    void testSettersEtGetters() {
        FraudePapier fraude = new FraudePapier();

        LocalDate date = LocalDate.of(2026, 6, 11);
        fraude.setDateReleve(date);
        fraude.setDescriptionTextuelle("Test papier");
        fraude.setContenu("Contenu papier");
        fraude.setDimension("A3");
        fraude.setPlieOuNon(false);

        assertEquals(date, fraude.getDateReleve());
        assertEquals("Test papier", fraude.getDescriptionTextuelle());
        assertEquals("Contenu papier", fraude.getContenu());
        assertEquals("A3", fraude.getDimension());
        assertFalse(fraude.isPlieOuNon());
    }
}
