import fr.eseo.e3e.projet2.formulaires.fraudes.FraudeIAG;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class TestFraudeIAG {

    @Test
    void testConstructeurParDefaut() {
        FraudeIAG fraude = new FraudeIAG();

        assertNull(fraude.getDateReleve());
        assertNull(fraude.getDescriptionTextuelle());
        assertNull(fraude.getContenu());
        assertNull(fraude.getNomService());
    }

    @Test
    void testConstructeurComplet() {
        LocalDate date = LocalDate.of(2026, 6, 10);
        FraudeIAG fraude = new FraudeIAG(date, "Usage ChatGPT", "copie écran", "ChatGPT");

        assertEquals(date, fraude.getDateReleve());
        assertEquals("Usage ChatGPT", fraude.getDescriptionTextuelle());
        assertEquals("copie écran", fraude.getContenu());
        assertEquals("ChatGPT", fraude.getNomService());
    }

    @Test
    void testSettersEtGetters() {
        FraudeIAG fraude = new FraudeIAG();

        LocalDate date = LocalDate.of(2026, 6, 11);
        fraude.setDateReleve(date);
        fraude.setDescriptionTextuelle("Test IAG");
        fraude.setContenu("Contenu IAG");
        fraude.setNomService("Gemini");

        assertEquals(date, fraude.getDateReleve());
        assertEquals("Test IAG", fraude.getDescriptionTextuelle());
        assertEquals("Contenu IAG", fraude.getContenu());
        assertEquals("Gemini", fraude.getNomService());
    }
}
