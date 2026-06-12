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

public class GestionnaireFormulaires {

    private List<Formulaire> formulaires;
    private List<Epreuve> epreuves;

    public GestionnaireFormulaires() {
        this.formulaires = new ArrayList<>();
        this.epreuves = new ArrayList<>();
    }

    public List<Formulaire> getFormulaires() {
        return formulaires;
    }

    public void setFormulaires(List<Formulaire> formulaires) {
        this.formulaires = formulaires;
    }

    public List<Epreuve> getEpreuves() {
        return epreuves;
    }

    public void setEpreuves(List<Epreuve> epreuves) {
        this.epreuves = epreuves;
    }

    public void ajouterFormulaire(Formulaire f) {
        this.formulaires.add(f);
    }

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

    public void ajouterEpreuve(Epreuve epreuve) {
        this.epreuves.add(epreuve);
    }

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

        System.out.println("=== STATISTIQUES ===");
        System.out.println("Total formulaires                : " + totalFormulaires);
        System.out.println("Étudiants distincts              : " + etudiantsDistincts.size());
        System.out.println("Total fraudes                    : " + totalFraudes);
        System.out.println("Moyenne fraudes / formulaire     : " + String.format("%.2f", moyenne));
        System.out.println("Écart‑type (population)          : " + String.format("%.2f", ecartType));
    }

    public void afficherGraphe() {
        if (formulaires == null || formulaires.isEmpty()) {
            System.out.println("Aucun formulaire disponible : le graphe est vide.");
            return;
        }
        GrapheFraudeurs graphe = new GrapheFraudeurs(this.formulaires);
        graphe.afficher();
    }

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
