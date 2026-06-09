import fr.eseo.e3e.projet2.app.GestionnaireFormulaires;
import fr.eseo.e3e.projet2.formulaires.Formulaire;
import fr.eseo.e3e.projet2.formulaires.Etudiant;
import fr.eseo.e3e.projet2.formulaires.Epreuve;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TestGestionnaireFormulaires {

    private GestionnaireFormulaires gestionnaire;
    private Formulaire formulaire1;
    private Formulaire formulaire2;
    private Etudiant etudiant1;
    private Etudiant etudiant2;
    private Epreuve epreuve;

    @BeforeEach
    public void setUp() {
        gestionnaire = new GestionnaireFormulaires();

        // Créer des étudiants de test
        etudiant1 = new Etudiant(1, "Dupont", "Jean", "jean@example.com");
        etudiant2 = new Etudiant(2, "Martin", "Marie", "marie@example.com");

        // Créer une épreuve de test
        epreuve = new Epreuve("INFO101", "Programmation Java", 3);

        // Créer des formulaires de test
        formulaire1 = new Formulaire();
        formulaire1.setIdentifiantNumeriqueUnique(100);
        formulaire1.setEpreuve(epreuve);
        formulaire1.getEtudiants().add(etudiant1);

        formulaire2 = new Formulaire();
        formulaire2.setIdentifiantNumeriqueUnique(101);
        formulaire2.setEpreuve(epreuve);
        formulaire2.getEtudiants().add(etudiant2);
    }

    // Tests pour ajouterFormulaire()
    @Test
    public void testAjouterFormulaire() {
        assertTrue(gestionnaire.getFormulaires().isEmpty());
        gestionnaire.ajouterFormulaire(formulaire1);
        assertEquals(1, gestionnaire.getFormulaires().size());
        assertTrue(gestionnaire.getFormulaires().contains(formulaire1));
    }

    @Test
    public void testAjouterMultiplesFormulaires() {
        gestionnaire.ajouterFormulaire(formulaire1);
        gestionnaire.ajouterFormulaire(formulaire2);
        assertEquals(2, gestionnaire.getFormulaires().size());
    }

    // Tests pour supprimerFormulaire()
    @Test
    public void testSupprimerFormulaire() {
        gestionnaire.ajouterFormulaire(formulaire1);
        gestionnaire.ajouterFormulaire(formulaire2);
        assertEquals(2, gestionnaire.getFormulaires().size());

        gestionnaire.supprimerFormulaire(100);
        assertEquals(1, gestionnaire.getFormulaires().size());
        assertFalse(gestionnaire.getFormulaires().contains(formulaire1));
    }

    @Test
    public void testSupprimerFormulairePasExiste() {
        gestionnaire.ajouterFormulaire(formulaire1);
        gestionnaire.supprimerFormulaire(999);
        assertEquals(1, gestionnaire.getFormulaires().size());
    }

    @Test
    public void testSupprimerFormulaireListe Vide() {
        gestionnaire.supprimerFormulaire(100);
        assertTrue(gestionnaire.getFormulaires().isEmpty());
    }

    // Tests pour getFormulairesParEtudiant()
    @Test
    public void testGetFormulairesParEtudiant() {
        gestionnaire.ajouterFormulaire(formulaire1);
        gestionnaire.ajouterFormulaire(formulaire2);

        List<Formulaire> result = gestionnaire.getFormulairesParEtudiant(1);
        assertEquals(1, result.size());
        assertTrue(result.contains(formulaire1));
    }

    @Test
    public void testGetFormulairesParEtudiantNonExiste() {
        gestionnaire.ajouterFormulaire(formulaire1);
        List<Formulaire> result = gestionnaire.getFormulairesParEtudiant(999);
        assertTrue(result.isEmpty());
    }

    // Tests pour getFormulairesParEpreuve()
    @Test
    public void testGetFormulairesParEpreuve() {
        gestionnaire.ajouterFormulaire(formulaire1);
        gestionnaire.ajouterFormulaire(formulaire2);

        List<Formulaire> result = gestionnaire.getFormulairesParEpreuve("INFO101");
        assertEquals(2, result.size());
    }

    @Test
    public void testGetFormulairesParEpreuveNonExiste() {
        gestionnaire.ajouterFormulaire(formulaire1);
        List<Formulaire> result = gestionnaire.getFormulairesParEpreuve("UNKNOWN");
        assertTrue(result.isEmpty());
    }

    // Tests pour rechercherParNom()
    @Test
    public void testRechercherParNom() {
        gestionnaire.ajouterFormulaire(formulaire1);
        gestionnaire.ajouterFormulaire(formulaire2);

        List<Etudiant> result = gestionnaire.rechercherParNom("Dupont");
        assertEquals(1, result.size());
        assertEquals("Dupont", result.get(0).getNom());
    }

    @Test
    public void testRechercherParNomCaseSensibilite() {
        gestionnaire.ajouterFormulaire(formulaire1);
        List<Etudiant> result = gestionnaire.rechercherParNom("dupont");
        assertEquals(1, result.size());
    }

    @Test
    public void testRechercherParNomNonExiste() {
        gestionnaire.ajouterFormulaire(formulaire1);
        List<Etudiant> result = gestionnaire.rechercherParNom("Inconnu");
        assertTrue(result.isEmpty());
    }

    // Tests pour rechercherParPrenom()
    @Test
    public void testRechercherParPrenom() {
        gestionnaire.ajouterFormulaire(formulaire1);
        gestionnaire.ajouterFormulaire(formulaire2);

        List<Etudiant> result = gestionnaire.rechercherParPrenom("Jean");
        assertEquals(1, result.size());
        assertEquals("Jean", result.get(0).getPrenom());
    }

    @Test
    public void testRechercherParPrenomNonExiste() {
        gestionnaire.ajouterFormulaire(formulaire1);
        List<Etudiant> result = gestionnaire.rechercherParPrenom("Pierre");
        assertTrue(result.isEmpty());
    }

    // Tests pour trouverParNumero()
    @Test
    public void testTrouverParNumero() {
        gestionnaire.ajouterFormulaire(formulaire1);
        Etudiant result = gestionnaire.trouverParNumero(1);
        assertNotNull(result);
        assertEquals("Jean", result.getPrenom());
    }

    @Test
    public void testTrouverParNumeroNonExiste() {
        gestionnaire.ajouterFormulaire(formulaire1);
        Etudiant result = gestionnaire.trouverParNumero(999);
        assertNull(result);
    }

    // Tests pour detecterRecidivistes()
    @Test
    public void testDetecterRecidivistes() {
        // Créer un formulaire avec le même étudiant
        Formulaire formulaire3 = new Formulaire();
        formulaire3.setIdentifiantNumeriqueUnique(102);
        formulaire3.setEpreuve(epreuve);
        formulaire3.getEtudiants().add(etudiant1); // Apparaît 2 fois

        gestionnaire.ajouterFormulaire(formulaire1);
        gestionnaire.ajouterFormulaire(formulaire2);
        gestionnaire.ajouterFormulaire(formulaire3);

        List<Etudiant> recidivistes = gestionnaire.detecterRecidivistes();
        assertEquals(1, recidivistes.size());
        assertTrue(recidivistes.contains(etudiant1));
    }

    @Test
    public void testDetecterRecidivistesAucun() {
        gestionnaire.ajouterFormulaire(formulaire1);
        gestionnaire.ajouterFormulaire(formulaire2);

        List<Etudiant> recidivistes = gestionnaire.detecterRecidivistes();
        assertTrue(recidivistes.isEmpty());
    }

    @Test
    public void testCalculerStatistiques() {
        gestionnaire.ajouterFormulaire(formulaire1);
        gestionnaire.ajouterFormulaire(formulaire2);
        assertDoesNotThrow(() -> gestionnaire.calculerStatistiques());
    }

    @Test
    public void testCalculerStatistiquesListeVide() {
        assertDoesNotThrow(() -> gestionnaire.calculerStatistiques());
    }
}
