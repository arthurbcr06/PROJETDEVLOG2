import fr.eseo.e3e.projet2.formulaires.fraudes.FraudeIAGConnectee;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class TestFraudeIAGConnectee {

    @Test
    void testConstructeurParDefaut() {
        FraudeIAGConnectee fraude = new FraudeIAGConnectee();

        assertNull(fraude.getDateReleve());
        assertNull(fraude.getDescriptionTextuelle());
        assertNull(fraude.getContenu());
        assertNull(fraude.getNomService());
        assertNull(fraude.getAdresseIP());
    }

    @Test
    void testConstructeurComplet() {
        LocalDate date = LocalDate.of(2026, 6, 10);
        FraudeIAGConnectee fraude = new FraudeIAGConnectee(
                date,
                "IAG en ligne",
                "via réseau",
                "Gemini",
                "192.168.1.42"
        );

        assertEquals(date, fraude.getDateReleve());
        assertEquals("IAG en ligne", fraude.getDescriptionTextuelle());
        assertEquals("via réseau", fraude.getContenu());
        assertEquals("Gemini", fraude.getNomService());
        assertEquals("192.168.1.42", fraude.getAdresseIP());
    }

    @Test
    void testSettersEtGetters() {
        FraudeIAGConnectee fraude = new FraudeIAGConnectee();

        LocalDate date = LocalDate.of(2026, 6, 11);
        fraude.setDateReleve(date);
        fraude.setDescriptionTextuelle("Test IAG connecté");
        fraude.setContenu("Contenu connecté");
        fraude.setNomService("ChatGPT");
        fraude.setAdresseIP("10.0.0.1");

        assertEquals(date, fraude.getDateReleve());
        assertEquals("Test IAG connecté", fraude.getDescriptionTextuelle());
        assertEquals("Contenu connecté", fraude.getContenu());
        assertEquals("ChatGPT", fraude.getNomService());
        assertEquals("10.0.0.1", fraude.getAdresseIP());
    }
}
