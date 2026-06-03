package fr.eseo.e3e.projet2.interfaces;


import fr.eseo.e3e.projet2.app.GestionnaireFormulaires;
import fr.eseo.e3e.projet2.formulaires.Etudiant;
import fr.eseo.e3e.projet2.formulaires.Formulaire;

import java.util.Scanner;

public class Interface {
    private GestionnaireFormulaires gestionnaire;
    private Etudiant etudiant;
    private Scanner scanner;

    public Interface(){
        this.scanner = new Scanner(System.in);
    }

    public Interface(GestionnaireFormulaires gestionnaire){
        this.gestionnaire = gestionnaire;
        this.scanner = new Scanner(System.in);
    }

    public void lancerMenu(){
        boolean continuer = true;
        while (continuer){
            afficherMenu();
            String choix = scanner.nextLine();
            continuer = traiterOption(choix);
        }
        System.out.println("Fermeture de l'application");
    }

    private void afficherMenu(){
        System.out.println("Menu principal");
        System.out.println("1. Ajouter un formulaire");
        System.out.println("2. Supprimer un formulaire");
        System.out.println("3. Rechercher des formulaires");
        System.out.println("4. Rechercher un étudiant");
        System.out.println("5. Calculer les statistiques");
        System.out.println("6. Afficher le graphe des fraudeurs ");
        System.out.println("7. Détecter les récidivistes");
        System.out.println("8. Quitter");
        System.out.print("Votre choix :");
    }

    private boolean traiterOption(String choix) {
        switch (choix.toUpperCase()) {
            case "1":
                System.out.println("Lancement procédure d'ajout...");
                break;

            case "2":
                System.out.print("Identifiant du formulaire à supprimer : ");
                try {
                    int id = Integer.parseInt(scanner.nextLine());
                    gestionnaire.supprimerFormulaire(id);
                    System.out.println("Formulaire supprimé");
                } catch (NumberFormatException e) {
                    System.out.println("Erreur : saisie invalide");
                }
                break;

            case "3":
                menuRechercheFormulaires();
                break;

            case "4":
                menuRechercheEtudiant();
                break;

            case "5":
                gestionnaire.calculerStatistiques();
                break;

            case "6":
                gestionnaire.afficherGraphe();
                break;

            case "7":
                System.out.println("Nombre de récidivistes détectés : " + gestionnaire.detecterRecidivistes().size());
                break;

            case "8":
                return false;

            default:
                System.out.println("Erreur : option invalide");
        }
        return true;
    }

        private void menuRechercheFormulaires(){
            System.out.println("Recherche de formulaires ");
            System.out.println("1. Par étudiant (numéro apprenant)");
            System.out.println("2. Par épreuve (code ECUE)");
            System.out.print("Votre choix : ");
            String sousChoix = scanner.nextLine();

            if (sousChoix.equals("1")){
                System.out.print("Saisir le numéro apprenant : ");
                String num = scanner.nextLine();
                var resultats = gestionnaire.getFormulairesParEtudiant(num);
                if (resultats.isEmpty()){
                    System.out.println("Aucun formulaire n'a été trouvé");
                }
                else {
                    System.out.println(resultats.size() + " formulaire/s trouvé/s");
                }
            }
            else if (sousChoix.equals("2")) {
                System.out.print("Saisir le code ECUE : ");
                String code = scanner.nextLine();
                var resultats = gestionnaire.getFormulairesParEpreuve(code);
                if (resultats.isEmpty()){
                    System.out.println("Aucun formulaire n'a été trouvé");
                }
                else {
                    System.out.println(resultats.size() + " formulaire/s trouvé/s");
                }
            }
            else {
                System.out.println("Erreur : option invalide");
            }
        }

        private void menuRechercheEtudiant(){
            System.out.println("Recherche d'un étudiant ");
            System.out.println("1. Par nom");
            System.out.println("2. Par prénom");
            System.out.println("3. Par numéro apprenant");
            System.out.print("Votre choix : ");
            String sousChoix = scanner.nextLine();

            if (sousChoix.equals("1")){
                System.out.print("Saisir le nom  : ");
                String nom = scanner.nextLine();
                var resultats = gestionnaire.rechercherParNom(nom);
                if (resultats.isEmpty()){
                    System.out.println("Aucun étudiant n'a été trouvé");
                }
                else {
                    System.out.println(resultats.size() + " étudiant/s trouvé/s");
                }
            }
            else if (sousChoix.equals("2")) {
                System.out.print("Saisir le prénom : ");
                String prenom = scanner.nextLine();
                var resultats = gestionnaire.rechercherParPrenom(prenom);
                if (resultats.isEmpty()){
                    System.out.println("Aucun étudiant n'a été trouvé");
                }
                else {
                    System.out.println(resultats.size() + " étudiant/s trouvé/s");
                }
            }
            else if (sousChoix.equals("3")) {
                System.out.print("Saisir le numéro apprenant : ");
                String num = scanner.nextLine();
                Etudiant etudiant = gestionnaire.trouverParNumero(num);
                if (etudiant == null){
                    System.out.println("Aucun étudiant trouvé");
                }
                else{
                    System.out.println("Etudiant trouvé : "+ etudiant.getNom() + " "+ etudiant.getPrenom());
                }
            }
            else {
                System.out.println("Erreur : option invalide");
            }
        }
    }


