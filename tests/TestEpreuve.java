import fr.eseo.e3e.projet2.formulaires.Epreuve;
import fr.eseo.e3e.projet2.formulaires.Modalite;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

public class TestEpreuve {

    @Test
    void testConstructeurParDefaut() {
        Epreuve ep = new Epreuve();

        assertNull(ep.getCodeECUE());
        assertNull(ep.getDatePassage());
        assertNull(ep.getHeurePassage());
        assertNull(ep.getDureePassage());
        assertNull(ep.getModalite());
    }

    @Test
    void testConstructeurComplet() {
        LocalDate date = LocalDate.of(2026, 6, 3);
        LocalTime heure = LocalTime.of(9, 0);
        Duration duree = Duration.ofMinutes(120);

        Epreuve ep = new Epreuve("POO", date, heure, duree, Modalite.ECRIT);

        assertEquals("POO", ep.getCodeECUE());
        assertEquals(date, ep.getDatePassage());
        assertEquals(heure, ep.getHeurePassage());
        assertEquals(duree, ep.getDureePassage());
        assertEquals(Modalite.ECRIT, ep.getModalite());
    }

    @Test
    void testSetCodeECUE() {
        Epreuve ep = new Epreuve();
        ep.setCodeECUE("DEVLO");
        assertEquals("DEVLO", ep.getCodeECUE());
    }

    @Test
    void testSetDatePassage() {
        Epreuve ep = new Epreuve();
        LocalDate date = LocalDate.of(2026, 6, 4);
        ep.setDatePassage(date);
        assertEquals(date, ep.getDatePassage());
    }

    @Test
    void testSetHeurePassage() {
        Epreuve ep = new Epreuve();
        LocalTime heure = LocalTime.of(14, 0);
        ep.setHeurePassage(heure);
        assertEquals(heure, ep.getHeurePassage());
    }

    @Test
    void testSetDureePassage() {
        Epreuve ep = new Epreuve();
        Duration duree = Duration.ofMinutes(60);
        ep.setDureePassage(duree);
        assertEquals(duree, ep.getDureePassage());
    }

    @Test
    void testSetModalite() {
        Epreuve ep = new Epreuve();
        ep.setModalite(Modalite.QCM);
        assertEquals(Modalite.QCM, ep.getModalite());
    }

    @Test
    void testTousLesSetters() {
        Epreuve ep = new Epreuve();
        LocalDate date = LocalDate.of(2026, 7, 1);
        LocalTime heure = LocalTime.of(8, 30);
        Duration duree = Duration.ofMinutes(90);

        ep.setCodeECUE("TEST");
        ep.setDatePassage(date);
        ep.setHeurePassage(heure);
        ep.setDureePassage(duree);
        ep.setModalite(Modalite.ORAL);

        assertEquals("TEST", ep.getCodeECUE());
        assertEquals(date, ep.getDatePassage());
        assertEquals(heure, ep.getHeurePassage());
        assertEquals(duree, ep.getDureePassage());
        assertEquals(Modalite.ORAL, ep.getModalite());
    }

    @Test
    void testMultiplesEpreuvesDifferentes() {
        Epreuve ep1 = new Epreuve("POO", LocalDate.of(2026, 6, 3), LocalTime.of(9, 0), Duration.ofMinutes(120), Modalite.ECRIT);
        Epreuve ep2 = new Epreuve("DEVLO", LocalDate.of(2026, 6, 4), LocalTime.of(14, 0), Duration.ofMinutes(60), Modalite.QCM);

        assertNotEquals(ep1.getCodeECUE(), ep2.getCodeECUE());
        assertNotEquals(ep1.getDatePassage(), ep2.getDatePassage());
    }
}