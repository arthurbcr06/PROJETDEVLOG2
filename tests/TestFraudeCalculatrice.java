import fr.eseo.e3e.projet2.formulaires.fraudes.FraudeCalculatrice;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class TestFraudeCalculatrice {

    @Test
    void testConstructeurParDefaut() {
        FraudeCalculatrice fraude = new FraudeCalculatrice();

        assertNull(fraude.getDateReleve());
        assertNull(fraude.getDescriptionTextuelle());
        assertNull(fraude.getContenu());
        assertNull(fraude.getMarqueAppareil());
        assertNull(fraude.getProgrammeStocke());
    }

    @Test
    void testConstructeurComplet() {
        LocalDate date = LocalDate.of(2026, 6, 10);
        FraudeCalculatrice fraude = new FraudeCalculatrice(
                date,
                "Calc programmée",
                "programme stocké",
                "Casio",
                "ALGO.prg"
        );

        assertEquals(date, fraude.getDateReleve());
        assertEquals("Calc programmée", fraude.getDescriptionTextuelle());
        assertEquals("programme stocké", fraude.getContenu());
        assertEquals("Casio", fraude.getMarqueAppareil());
        assertEquals("ALGO.prg", fraude.getProgrammeStocke());
    }

    @Test
    void testSettersEtGetters() {
        FraudeCalculatrice fraude = new FraudeCalculatrice();

        LocalDate date = LocalDate.of(2026, 6, 11);
        fraude.setDateReleve(date);
        fraude.setDescriptionTextuelle("Test");
        fraude.setContenu("Contenu");
        fraude.setMarqueAppareil("TI");
        fraude.setProgrammeStocke("prog.py");

        assertEquals(date, fraude.getDateReleve());
        assertEquals("Test", fraude.getDescriptionTextuelle());
        assertEquals("Contenu", fraude.getContenu());
        assertEquals("TI", fraude.getMarqueAppareil());
        assertEquals("prog.py", fraude.getProgrammeStocke());
    }

    @Test
    void testEqualsEtHashCode() {
        LocalDate date = LocalDate.of(2026, 6, 12);
        FraudeCalculatrice fraude1 = new FraudeCalculatrice(date,"Calculatrice programmée","programme stocké","Casio","programme.py");

        FraudeCalculatrice fraude2 = new FraudeCalculatrice(date,"Calculatrice programmée","programme stocké","Casio","programme.py");

        assertEquals(fraude1, fraude2);
        assertEquals(fraude1.hashCode(), fraude2.hashCode());
    }
}
