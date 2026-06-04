package fr.eseo.e3e.projet2.interfaces;
import fr.eseo.e3e.projet2.app.GestionnaireFormulaires;
import fr.eseo.e3e.projet2.formulaires.Cursus;
import fr.eseo.e3e.projet2.formulaires.Epreuve;
import fr.eseo.e3e.projet2.formulaires.Etudiant;
import fr.eseo.e3e.projet2.formulaires.Formulaire;
import fr.eseo.e3e.projet2.formulaires.Modalite;
import fr.eseo.e3e.projet2.formulaires.fraudes.FraudeCalculatrice;
import fr.eseo.e3e.projet2.formulaires.fraudes.FraudeIAG;
import fr.eseo.e3e.projet2.formulaires.fraudes.FraudeIAGConnectee;
import fr.eseo.e3e.projet2.formulaires.fraudes.FraudePapier;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
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

    private void attendreRetour() {
        System.out.println("Appuyer sur entrée pour revenir au menu principal");
        scanner.nextLine();
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
                menuAjoutFormulaire();
                attendreRetour();
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
                attendreRetour();
                break;

            case "3":
                menuRechercheFormulaires();
                attendreRetour();
                break;

            case "4":
                menuRechercheEtudiant();
                attendreRetour();
                break;

            case "5":
                gestionnaire.calculerStatistiques();
                attendreRetour();
                break;

            case "6":
                gestionnaire.afficherGraphe();
                attendreRetour();
                break;

            case "7":
                var recidivistes = gestionnaire.detecterRecidivistes();
                if (recidivistes.isEmpty()) {
                    System.out.println("Aucun récidiviste détecté.");
                } else {
                    System.out.println(recidivistes.size() + " récidiviste(s) détecté(s) :");
                    for (Etudiant e : recidivistes) {
                        System.out.println("  - " + e.getNom() + " " + e.getPrenom() + " | N° " + e.getNumeroApprenant() + " | Cursus : " + e.getCursus());
                    }
                }
                attendreRetour();
                break;

            case "8":
                return false;

            default:
                System.out.println("Erreur : option invalide");
        }
        return true;
    }

    private void menuAjoutFormulaire() {
        try {
            System.out.println("=== Création d'un formulaire ===");

            System.out.print("Code ECUE [DEVLO, POO, SHL, TEST, DEEP, DS, TNI, TNS, ASI, ANG]: ");
            String code = scanner.nextLine().trim();

            System.out.print("Date passage (AAAA-MM-JJ) : ");
            java.time.LocalDate date = java.time.LocalDate.parse(scanner.nextLine().trim());

            System.out.print("Heure passage (HH:MM) : ");
            java.time.LocalTime heure = java.time.LocalTime.parse(scanner.nextLine().trim());

            System.out.print("Durée (minutes) : ");
            long minutes = Long.parseLong(scanner.nextLine().trim());

            System.out.print("Modalité " + java.util.Arrays.toString(Modalite.values()) + " : ");
            Modalite modalite =
                    Modalite.valueOf(scanner.nextLine().trim().toUpperCase());

            Epreuve epreuve =
                    new Epreuve(
                            code, date, heure, java.time.Duration.ofMinutes(minutes), modalite
                    );

            Formulaire formulaire = new Formulaire();
            formulaire.setEpreuve(epreuve);

            System.out.print("Nombre d'étudiants impliqués : ");
            int nbEtudiants = Integer.parseInt(scanner.nextLine().trim());

            for (int i = 1; i <= nbEtudiants; i++) {
                System.out.println("Étudiant " + i + " :");
                System.out.print("  Nom : ");
                String nom = scanner.nextLine().trim();
                System.out.print("  Prénom : ");
                String prenom = scanner.nextLine().trim();
                System.out.print("  Numéro apprenant : ");
                int numero = Integer.parseInt(scanner.nextLine().trim());
                System.out.print("  Cursus " + java.util.Arrays.toString(Cursus.values()) + " : ");
                Cursus cursus = Cursus.valueOf(scanner.nextLine().trim());

                formulaire.ajouterEtudiant(new Etudiant(nom, prenom, numero, cursus));
            }

            gestionnaire.ajouterFormulaire(formulaire);
            gestionnaire.ajouterEpreuve(epreuve);

            System.out.println("Formulaire ajouté avec succès. ID: " + formulaire.getIdentifiantNumeriqueUnique());

        } catch (Exception e) {
            System.out.println("Erreur lors de la création du formulaire : " + e.getMessage());
        }
    }

    private void menuRechercheFormulaires(){
        System.out.println("Recherche de formulaires ");
        System.out.println("1. Par étudiant (numéro apprenant)");
        System.out.println("2. Par épreuve (code ECUE)");
        System.out.print("Votre choix : ");
        String sousChoix = scanner.nextLine();

        if (sousChoix.equals("1")){
            System.out.print("Saisir le numéro apprenant : ");
            int num = Integer.parseInt(scanner.nextLine());
            var resultats = gestionnaire.getFormulairesParEtudiant(num);
            if (resultats.isEmpty()){
                System.out.println("Aucun formulaire n'a été trouvé");
            } else {
                System.out.println(resultats.size() + " formulaire(s) trouvé(s) :");
                for (Formulaire f : resultats) {
                    System.out.println("  - ID " + f.getIdentifiantNumeriqueUnique() + " , epreuve : " + f.getEpreuve().getCodeECUE() + " , créé le : " + f.getDateCreation() + " , Fraudes : " + f.getFraudes().size());
                }
            }
        }

        else if (sousChoix.equals("2")) {
            System.out.print("Saisir le code ECUE : ");
            String code = scanner.nextLine();
            var resultats = gestionnaire.getFormulairesParEpreuve(code);
            if (resultats.isEmpty()){
                System.out.println("Aucun formulaire n'a été trouvé");
            } else {
                System.out.println(resultats.size() + " formulaire(s) trouvé(s) :");
                for (Formulaire f : resultats) {
                    System.out.println("  - ID " + f.getIdentifiantNumeriqueUnique() + " , epreuve: " + f.getEpreuve().getCodeECUE() + " , créé le: " + f.getDateCreation() + " , fraudes: " + f.getFraudes().size());
                }
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
            System.out.print("Saisir le nom : ");
            String nom = scanner.nextLine();
            var resultats = gestionnaire.rechercherParNom(nom);
            if (resultats.isEmpty()){
                System.out.println("Aucun étudiant n'a été trouvé");
            } else {
                System.out.println(resultats.size() + " étudiant(s) trouvé(s) :");
                for (Etudiant e : resultats) {
                    System.out.println("  - " + e.getNom() + " " + e.getPrenom() + " , numéro " + e.getNumeroApprenant() + ",cursus : " + e.getCursus());
                }
            }
        }

        else if (sousChoix.equals("2")) {
            System.out.print("Saisir le prénom : ");
            String prenom = scanner.nextLine();
            var resultats = gestionnaire.rechercherParPrenom(prenom);
            if (resultats.isEmpty()){
                System.out.println("Aucun étudiant n'a été trouvé");
            } else {
                System.out.println(resultats.size() + " étudiant(s) trouvé(s) :");
                for (Etudiant e : resultats) {
                    System.out.println("  - " + e.getNom() + " " + e.getPrenom() + ", numero " + e.getNumeroApprenant() + ", Cursus : " + e.getCursus());
                }
            }
        }

        else if (sousChoix.equals("3")) {
            System.out.print("Saisir le numéro apprenant : ");
            int num = Integer.parseInt(scanner.nextLine());
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

    public static void main(String[] args) {
        GestionnaireFormulaires gestionnaire = new GestionnaireFormulaires();

        Epreuve ep1 = new Epreuve("POO", LocalDate.of(2026,6,03), LocalTime.of(9,0), Duration.ofMinutes(120), Modalite.ECRIT);
        Epreuve ep2 = new Epreuve("DEVLO", LocalDate.of(2026,6,04), LocalTime.of(14,0), Duration.ofMinutes(60), Modalite.QCM);

        Etudiant e1 = new Etudiant("Boussard", "Noah", 11111, Cursus.E3e);
        Etudiant e2 = new Etudiant("Beucher", "Arthur",   22222, Cursus.E2);
        Etudiant e3 = new Etudiant("Bernard",  "Ugal", 33333, Cursus.E1);
        Etudiant e4 = new Etudiant("Cluzeau",  "Thomas", 44444, Cursus.E4);

        FraudeIAG f1 = new FraudeIAG(LocalDate.now(), "Usage ChatGPT", "copie écran", "ChatGPT");
        FraudePapier f2 = new FraudePapier(LocalDate.now(), "Feuille cachée", "formules", "A4", true);
        FraudeCalculatrice f3 = new FraudeCalculatrice(LocalDate.now(), "Calc programmée", "programme stocké", "Casio", "ALGO.prg");
        FraudeIAGConnectee f4 = new FraudeIAGConnectee(LocalDate.now(), "IAG en ligne", "via réseau", "Gemini", "192.168.1.42");


        Formulaire form1 = new Formulaire(ep1, List.of(e1, e2), List.of(f1), null);
        Formulaire form2 = new Formulaire(ep1, List.of(e3), List.of(f2, f3), null);
        Formulaire form3 = new Formulaire(ep2, List.of(e2, e4), List.of(f4), null);

        gestionnaire.ajouterFormulaire(form1);
        gestionnaire.ajouterFormulaire(form2);
        gestionnaire.ajouterFormulaire(form3);
        gestionnaire.ajouterEpreuve(ep1);
        gestionnaire.ajouterEpreuve(ep2);

        Interface ui = new Interface(gestionnaire);
        ui.lancerMenu();
    }
}