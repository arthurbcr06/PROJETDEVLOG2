import fr.eseo.e3e.projet2.app.GrapheFraudeurs;
import fr.eseo.e3e.projet2.formulaires.Cursus;
import fr.eseo.e3e.projet2.formulaires.Etudiant;
import fr.eseo.e3e.projet2.formulaires.Formulaire;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class TestGrapheFraudeurs {

    private Etudiant creerEtudiant(String nom, String prenom, int numero) {
        return new Etudiant(nom, prenom, numero, Cursus.E2);
    }

    private Formulaire creerFormulaireAvec(Etudiant... etudiants) {
        Formulaire f = new Formulaire();
        for (Etudiant e : etudiants) {
            f.ajouterEtudiant(e);
        }
        return f;
    }

    @Test
    void testConstructeurParDefautEtVoisinsInconnu() {
        GrapheFraudeurs graphe = new GrapheFraudeurs();
        Etudiant inconnu = creerEtudiant("Inconnu", "I", 999);

        Set<Etudiant> voisins = graphe.getVoisins(inconnu);

        assertNotNull(voisins);
        assertTrue(voisins.isEmpty());
    }

    @Test
    void testConstructeurAvecListeNull() {
        GrapheFraudeurs graphe = new GrapheFraudeurs(null);
        Etudiant e = creerEtudiant("A", "A", 1);

        assertTrue(graphe.getVoisins(e).isEmpty());
    }

    @Test
    void testConstructeurAvecFormulaireCreeToutesLesAretes() {
        Etudiant e1 = creerEtudiant("Dupont", "Jean", 1);
        Etudiant e2 = creerEtudiant("Martin", "Lea", 2);
        Etudiant e3 = creerEtudiant("Bernard", "Noe", 3);

        Formulaire f = creerFormulaireAvec(e1, e2, e3);
        GrapheFraudeurs graphe = new GrapheFraudeurs(List.of(f));

        assertEquals(2, graphe.getVoisins(e1).size());
        assertTrue(graphe.getVoisins(e1).contains(e2));
        assertTrue(graphe.getVoisins(e1).contains(e3));

        assertEquals(2, graphe.getVoisins(e2).size());
        assertTrue(graphe.getVoisins(e2).contains(e1));
        assertTrue(graphe.getVoisins(e2).contains(e3));

        assertEquals(2, graphe.getVoisins(e3).size());
        assertTrue(graphe.getVoisins(e3).contains(e1));
        assertTrue(graphe.getVoisins(e3).contains(e2));
    }

    @Test
    void testAjouterAreteUniquementSiSommetsExistants() {
        Etudiant e1 = creerEtudiant("A", "A", 10);
        Etudiant e2 = creerEtudiant("B", "B", 20);
        Etudiant e3 = creerEtudiant("C", "C", 30);

        GrapheFraudeurs graphe = new GrapheFraudeurs();
        graphe.ajouterSommet(e1);
        graphe.ajouterSommet(e2);

        // Branche "true" : les deux sommets existent
        graphe.ajouterArete(e1, e2);
        assertTrue(graphe.getVoisins(e1).contains(e2));
        assertTrue(graphe.getVoisins(e2).contains(e1));

        // Branche "false" : e3 absent du graphe
        graphe.ajouterArete(e1, e3);
        assertFalse(graphe.getVoisins(e1).contains(e3));
        assertTrue(graphe.getVoisins(e3).isEmpty());
    }
}

