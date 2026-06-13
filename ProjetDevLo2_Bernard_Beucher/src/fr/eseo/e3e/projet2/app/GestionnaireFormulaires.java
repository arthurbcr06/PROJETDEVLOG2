package fr.eseo.e3e.projet2.app;

import fr.eseo.e3e.projet2.formulaires.Epreuve;
import fr.eseo.e3e.projet2.formulaires.Etudiant;
import fr.eseo.e3e.projet2.formulaires.Formulaire;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Classe représentant un gestionnaire de formulaires.
 * Elle permet de gérer une collection de formulaires et d'épreuves.
 *
 * Le gestionnaire offre des méthodes pour ajouter, supprimer et rechercher des formulaires et des étudiants,
 * ainsi que pour calculer des statistiques sur les formulaires, afficher un graphe des relations de fraudeurs
 * et détecter les récidivistes.
 */
public class GestionnaireFormulaires {

    private List<Formulaire> formulaires;
    private List<Epreuve> epreuves;

    // Constructeur par défaut, initialise les listes de formulaires et d'épreuves.
    public GestionnaireFormulaires() {
        this.formulaires = new ArrayList<>();
        this.epreuves = new ArrayList<>();
    }

    /**
     * Retourne la liste des formulaires gérés par le gestionnaire.
     *
     * @return la liste des formulaires
     */
    public List<Formulaire> getFormulaires() {
        return formulaires;
    }

    /**
     * Définit la liste des formulaires gérés par le gestionnaire.
     *
     * @param formulaires la nouvelle liste de formulaires
     */
    public void setFormulaires(List<Formulaire> formulaires) {
        this.formulaires = formulaires;
    }

    /**
     * Retourne la liste des épreuves gérées par le gestionnaire.
     *
     * @return la liste des épreuves
     */
    public List<Epreuve> getEpreuves() {
        return epreuves;
    }

    /**
     * Définit la liste des épreuves gérées par le gestionnaire.
     *
     * @param epreuves la nouvelle liste d'épreuves
     */
    public void setEpreuves(List<Epreuve> epreuves) {
        this.epreuves = epreuves;
    }

    /**
     * Ajoute un formulaire à la liste des formulaires gérés par le gestionnaire.
     *
     * @param f le formulaire à ajouter
     */
    public void ajouterFormulaire(Formulaire f) {
        this.formulaires.add(f);
    }

    /**
     * Supprime un formulaire de la liste des formulaires gérés par le gestionnaire en fonction de son identifiant unique.
     *
     * @param id l'identifiant unique du formulaire à supprimer
     */
    public void supprimerFormulaire(int id) {
        if (formulaires == null || formulaires.isEmpty()) {
            System.out.println("Aucun formulaire disponible.");
            return;
        }
        boolean supprime = false;
        for (int i = 0; i < formulaires.size(); i++) {
            Formulaire f = formulaires.get(i);
            if (f != null && f.getIdentifiantNumeriqueUnique() == id) {
                formulaires.remove(i);
                supprime = true;
                break;
            }
        }
        if (!supprime) {
            System.out.println("Formulaire avec l'identifiant " + id + " non trouvé.");
        }
    }

    /**
     * Ajoute une épreuve à la liste des épreuves gérées par le gestionnaire.
     *
     * @param epreuve l'épreuve à ajouter
     */
    public void ajouterEpreuve(Epreuve epreuve) {
        this.epreuves.add(epreuve);
    }

    /**
     * Retourne la liste des formulaires associés à un étudiant donné en fonction de son numéro d'apprenant.
     *
     * @param numeroApprenant le numéro d'apprenant de l'étudiant
     * @return la liste des formulaires associés à l'étudiant
     */
    public List<Formulaire> getFormulairesParEtudiant(int numeroApprenant){
        List<Formulaire> result = new ArrayList<>();
        if (formulaires == null) {
            System.out.println("Aucun formulaire trouvé.");
            return result;
        }
        for (Formulaire f : formulaires) {
            for (Etudiant e : f.getEtudiants()) {
                if (e.getNumeroApprenant() == numeroApprenant) {
                    result.add(f);
                    break;
                }
            }
        }
        return result;
    }

    /**
     * Retourne la liste des formulaires associés à une épreuve donnée en fonction de son code.
     *
     * @param code le code de l'épreuve
     * @return la liste des formulaires associés à l'épreuve
     */
    public List<Formulaire> getFormulairesParEpreuve(String code){
        List<Formulaire> result = new ArrayList<>();
        if (code == null || code.trim().isEmpty()) {
            throw new IllegalArgumentException("Le code d'épreuve ne peut pas être null ou vide");
        }
        if (formulaires == null) {
            System.out.println("Aucun formulaire trouvé.");
            return result;
        }
        for (Formulaire f : formulaires) {
            if (f.getEpreuve() != null && f.getEpreuve().getCodeECUE().equals(code)) {
                result.add(f);
            }
        }
        return result;
    }

    /**
     * Recherche les étudiants par leur nom.
     *
     * @param nom le nom de l'étudiant à rechercher
     * @return la liste des étudiants correspondant au nom donné
     */
    public List<Etudiant> rechercherParNom(String nom) {
        List<Etudiant> result = new ArrayList<>();
        if (nom == null || nom.trim().isEmpty()) {
            throw new IllegalArgumentException("Le nom ne peut pas être null ou vide");
        }
        if (formulaires == null) {
            System.out.println("Aucun formulaire trouvé.");
            return result;
        }
        for (Formulaire f : formulaires) {
            for (Etudiant e : f.getEtudiants()) {
                if (e.getNom().equalsIgnoreCase(nom)) {
                    result.add(e);
                }
            }
        }
        return result;
    }

    /**
     * Recherche les étudiants par leur prénom.
     *
     * @param prenom le prénom de l'étudiant à rechercher
     * @return la liste des étudiants correspondant au prénom donné
     */
    public List<Etudiant> rechercherParPrenom(String prenom) {
        List<Etudiant> result = new ArrayList<>();
        if (formulaires == null) {
            System.out.println("Aucun formulaire trouvé.");
            return result;
        }
        for (Formulaire f : formulaires) {
            for (Etudiant e : f.getEtudiants()) {
                if (e.getPrenom().equalsIgnoreCase(prenom)) {
                    result.add(e);
                }
            }
        }
        return result;
    }

    /**
     * Recherche un étudiant par son numéro d'apprenant.
     *
     * @param numeroApprenant le numéro d'apprenant de l'étudiant à rechercher
     * @return l'étudiant correspondant au numéro d'apprenant donné, ou null si non trouvé
     */
    public Etudiant trouverParNumero(int numeroApprenant) {
        if (formulaires == null) {
            System.out.println("Aucun formulaire trouvé.");
            return null;
        }
        for (Formulaire f : formulaires) {
            for (Etudiant e : f.getEtudiants()) {
                if (e.getNumeroApprenant() == numeroApprenant) {
                    return e;
                }
            }
        }
        return null;
    }

    /**
     * Calcule et affiche les statistiques des formulaires gérés par le gestionnaire.
     * Les statistiques incluent le nombre total de formulaires, le nombre d'étudiants distincts,
     * le nombre total de fraudes, la moyenne de fraudes par formulaire et l'écart-type des fraudes.
     */
    public void calculerStatistiques() {
        if (formulaires == null || formulaires.isEmpty()) {
            System.out.println("Aucun formulaire disponible.");
            return;
        }

        int totalFormulaires = 0;
        Set<Integer> etudiantsDistincts = new HashSet<>();
        int totalFraudes = 0;
        double sumSquares = 0.0;

        for (Formulaire f : formulaires) {
            if (f == null) {continue;}
            totalFormulaires++;

            List<Etudiant> etudiants = f.getEtudiants();
            if (etudiants != null) {
                for (Etudiant e : etudiants) {
                    if (e != null) {etudiantsDistincts.add(e.getNumeroApprenant());}
                }
            }

            List<?> fraudes = f.getFraudes();
            if (fraudes == null) {continue;}
            int nbFraudes = fraudes.size();
            totalFraudes += nbFraudes;
            sumSquares += (double) nbFraudes * nbFraudes;
        }

        if (totalFormulaires == 0) {
            System.out.println("Impossible de calculer les statistiques : aucun formulaire valide.");
            return;
        }

        double moyenne = (double) totalFraudes / totalFormulaires;
        double variance = (sumSquares / totalFormulaires) - (moyenne * moyenne);
        double ecartType = Math.sqrt(Math.max(0.0, variance));

        System.out.println("Statistiques des formulaires :\n");
        System.out.println("Total formulaires : " + totalFormulaires);
        System.out.println("Étudiants distincts : " + etudiantsDistincts.size());
        System.out.println("Total fraudes : " + totalFraudes);
        System.out.println("Moyenne fraudes / formulaire : " + String.format("%.2f", moyenne));
        System.out.println("Écart‑type (population) : " + String.format("%.2f", ecartType));
    }

    /**
     * Affiche le graphe des relations de fraudeurs basé sur les formulaires gérés par le gestionnaire.
     * Si aucun formulaire n'est disponible, un message est affiché.
     */
    public void afficherGraphe() {
        if (formulaires == null || formulaires.isEmpty()) {
            System.out.println("Aucun formulaire disponible : le graphe est vide.");
            return;
        }
        GrapheFraudeurs graphe = new GrapheFraudeurs(this.formulaires);
        graphe.afficher();
    }

    /**
     * Détecte les étudiants récidivistes, c'est-à-dire ceux qui apparaissent dans au moins deux formulaires.
     *
     * @return la liste des étudiants récidivistes
     */
    public List<Etudiant> detecterRecidivistes() {
        Map<Etudiant, Integer> comptage = new HashMap<>();
        if (formulaires != null) {
            for (Formulaire f : formulaires) {
                if (f != null && f.getEtudiants() != null) {
                    for (Etudiant e : f.getEtudiants()) {
                        if (e != null) {
                            comptage.put(e, comptage.getOrDefault(e, 0) + 1);
                        }
                    }
                }
            }
        }

        List<Etudiant> recidivistes = new ArrayList<>();
        for (Map.Entry<Etudiant, Integer> entry : comptage.entrySet()) {
            if (entry.getValue() >= 2) {
                recidivistes.add(entry.getKey());
            }
        }
        return recidivistes;
    }

}
