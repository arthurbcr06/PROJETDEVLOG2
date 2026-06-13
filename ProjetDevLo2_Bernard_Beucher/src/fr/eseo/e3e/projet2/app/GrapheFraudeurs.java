package fr.eseo.e3e.projet2.app;

import fr.eseo.e3e.projet2.formulaires.Etudiant;
import fr.eseo.e3e.projet2.formulaires.Formulaire;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Classe représentant un graphe des relations de plagiat entre étudiants.
 *
 * Chaque étudiant est représenté comme un sommet du graphe, et une arête est créée
 * entre deux étudiants s'ils sont impliqués dans le même formulaire.
 * Un graphe est représenté par une map où chaque étudiant est associé à un ensemble d'étudiants voisins.
 */
public class GrapheFraudeurs {
    private Map<Etudiant, Set<Etudiant>> adjacence;

    // Constructeur par défaut
    public GrapheFraudeurs() {
        this.adjacence = new HashMap<>();
    }

    /**
     * Constructeur qui initialise le graphe à partir d'une liste de formulaires.
     *
     * @param formulaires La liste des formulaires à partir desquels construire le graphe.
     */
    public GrapheFraudeurs(List<Formulaire> formulaires) {
        this();
        if (formulaires != null) {
            for (Formulaire f : formulaires) {
                List<Etudiant> impliques = f.getEtudiants();

                for (Etudiant e : impliques) {
                    ajouterSommet(e);
                }

                for (int i = 0; i < impliques.size(); i++) {
                    for (int j = i + 1; j < impliques.size(); j++) {
                        ajouterArete(impliques.get(i), impliques.get(j));
                    }
                }
            }
        }
    }

    /**
     * Ajoute un sommet (étudiant) au graphe.
     *
     * @param e l'étudiant à ajouter comme sommet.
     */
    public void ajouterSommet(Etudiant e) {
        adjacence.putIfAbsent(e, new HashSet<>());
    }

    /**
     * Ajoute une arête entre deux étudiants dans le graphe.
     *
     * @param e1 Le premier étudiant.
     * @param e2 Le deuxième étudiant.
     */
    public void ajouterArete(Etudiant e1, Etudiant e2) {
        // en gros on veut vérifier si les deux étudiants existent bien comme sommets dans le graphe
        if (adjacence.containsKey(e1) && adjacence.containsKey(e2)) {
            adjacence.get(e1).add(e2);
            adjacence.get(e2).add(e1);
        }
    }

    /**
     * Affiche le graphe des relations de plagiat.
     * Chaque étudiant est affiché avec ses voisins (étudiants avec lesquels il partage un formulaire).
     */
    public void afficher() {
        System.out.println(" Graphe des relations de plagiat");

        if (adjacence.isEmpty()) {
            System.out.println("Le graphe est vide donc il y a aucun étudiant impliqué dans les formulaires");
            return;
        }
        for (Map.Entry<Etudiant, Set<Etudiant>> entry : adjacence.entrySet()) {
            Etudiant etudiant = entry.getKey();
            Set<Etudiant> voisins = entry.getValue();

            System.out.print(etudiant.getNom() + " " + etudiant.getPrenom() + " -- ");

            if (voisins.isEmpty()) {
                System.out.print("Aucune relation");
            } else {
                for (Etudiant voisin : voisins) {
                    System.out.print("[" + voisin.getNom() + " " + voisin.getPrenom() + "] ");
                }
            }
            System.out.println();
        }
    }

    /**
     * Retourne l'ensemble des voisins (étudiants liés par une arête) d'un étudiant donné.
     *
     * @param e L'étudiant dont on veut obtenir les voisins.
     * @return L'ensemble d'étudiants voisins.
     */
    public Set<Etudiant> getVoisins(Etudiant e) {
        return adjacence.getOrDefault(e, new HashSet<>());
    }
}
