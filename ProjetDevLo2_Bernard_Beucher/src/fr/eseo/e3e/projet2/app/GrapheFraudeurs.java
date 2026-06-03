package fr.eseo.e3e.projet2.app;

import fr.eseo.e3e.projet2.formulaires.Etudiant;
import fr.eseo.e3e.projet2.formulaires.Formulaire;

import java.util.*;

public class GrapheFraudeurs {
    private Map<Etudiant, Set<Etudiant>> adjacence;

    public GrapheFraudeurs() {
        this.adjacence = new HashMap<>();
    }

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

    public void ajouterSommet(Etudiant e) {
        adjacence.putIfAbsent(e, new HashSet<>());
    }

    public void ajouterArete(Etudiant e1, Etudiant e2) {
        // en gros on veut check si les deux étudiants existent bien comme sommets dans le graphe
        if (adjacence.containsKey(e1) && adjacence.containsKey(e2)) {
            adjacence.get(e1).add(e2);
            adjacence.get(e2).add(e1);
        }
    }

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

    public Set<Etudiant> getVoisins(Etudiant e) {
        return adjacence.getOrDefault(e, new HashSet<>());
    }
}
