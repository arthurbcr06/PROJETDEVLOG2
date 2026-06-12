package fr.eseo.e3e.projet2.interfaces;

import fr.eseo.e3e.projet2.app.GestionnaireFormulaires;
import fr.eseo.e3e.projet2.formulaires.Cursus;
import fr.eseo.e3e.projet2.formulaires.Etudiant;
import fr.eseo.e3e.projet2.formulaires.Epreuve;
import fr.eseo.e3e.projet2.formulaires.Formulaire;
import fr.eseo.e3e.projet2.formulaires.Modalite;
import fr.eseo.e3e.projet2.formulaires.Sanction;
import fr.eseo.e3e.projet2.formulaires.fraudes.FraudeCalculatrice;
import fr.eseo.e3e.projet2.formulaires.fraudes.FraudeIAG;
import fr.eseo.e3e.projet2.formulaires.fraudes.FraudeIAGConnectee;
import fr.eseo.e3e.projet2.formulaires.fraudes.FraudePapier;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Interface {

    private GestionnaireFormulaires gestionnaire;
    private Etudiant etudiant;
    private Scanner scanner;

    public Interface() {
        this.scanner = new Scanner(System.in);
    }

    public Interface(final GestionnaireFormulaires gestionnaire) {
        this.gestionnaire = gestionnaire;
        this.scanner = new Scanner(System.in);
    }

    private String lireChaine(String invite) {
        while (true) {
            System.out.print(invite);
            String valeur = this.scanner.nextLine().trim();
            if (!valeur.isEmpty()) {
                return valeur;
            }
            System.out.println("Erreur : la saisie ne peut pas être vide. Veuillez réessayer.");
        }
    }

    private int lireEntierPositif(String invite) {
        while (true) {
            System.out.print(invite);
            String saisie = this.scanner.nextLine().trim();
            try {
                int valeur = Integer.parseInt(saisie);
                if (valeur < 0) {
                    System.out.println("Erreur : la valeur doit être un entier positif ou nul.");
                    continue;
                }
                return valeur;
            } catch (NumberFormatException e) {
                System.out.println("Erreur : saisie invalide. Veuillez entrer un nombre entier (ex. : 42).");
            }
        }
    }

    private long lireLongPositif(String invite) {
        while (true) {
            System.out.print(invite);
            String saisie = this.scanner.nextLine().trim();
            try {
                long valeur = Long.parseLong(saisie);
                if (valeur <= 0) {
                    System.out.println("Erreur : la durée doit être strictement positive.");
                    continue;
                }
                return valeur;
            } catch (NumberFormatException e) {
                System.out.println("Erreur : saisie invalide. Veuillez entrer un nombre entier (ex. : 120).");
            }
        }
    }

    private LocalDate lireDate(String invite) {
        while (true) {
            System.out.print(invite);
            String saisie = this.scanner.nextLine().trim();
            try {
                return LocalDate.parse(saisie);
            } catch (DateTimeParseException e) {
                System.out.println("Erreur : format de date invalide. Utilisez le format AAAA-MM-JJ (ex. : 2026-06-03).");
            }
        }
    }

    private LocalTime lireHeure(String invite) {
        while (true) {
            System.out.print(invite);
            String saisie = this.scanner.nextLine().trim();
            try {
                return LocalTime.parse(saisie);
            } catch (DateTimeParseException e) {
                System.out.println("Erreur : format d'heure invalide. Utilisez le format HH:MM (ex. : 09:00).");
            }
        }
    }

    private Modalite lireModalite(String invite) {
        while (true) {
            System.out.print(invite);
            String saisie = this.scanner.nextLine().trim().toUpperCase();
            try {
                return Modalite.valueOf(saisie);
            } catch (IllegalArgumentException e) {
                System.out.println("Erreur : modalité inconnue. Valeurs autorisées : " + Arrays.toString(Modalite.values()));
            }
        }
    }

    private Cursus lireCursus(String invite) {
        while (true) {
            System.out.print(invite);
            String saisie = this.scanner.nextLine().trim();
            try {
                return Cursus.valueOf(saisie);
            } catch (IllegalArgumentException e) {
                System.out.println("Erreur : cursus inconnu. Valeurs autorisées : " + Arrays.toString(Cursus.values()));
            }
        }
    }

    private void attendreRetour() {
        System.out.println("Appuyer sur entrée pour revenir au menu principal");
        this.scanner.nextLine();
    }

    public void lancerMenu() {
        String choix;
        for (boolean continuer = true; continuer; continuer = this.traiterOption(choix)) {
            this.afficherMenu();
            choix = this.scanner.nextLine();
        }
        System.out.println("Fermeture de l'application");
    }

    private void afficherMenu() {
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

    private boolean traiterOption(final String choix) {
        if (choix == null || choix.trim().isEmpty()) {
            System.out.println("Erreur : aucun choix saisi.");
            return true;
        }

        final String upperCase = choix.trim().toUpperCase();
        switch (upperCase) {
            case "1": {
                this.menuAjoutFormulaire();
                this.attendreRetour();
                break;
            }
            case "2": {
                int id = lireEntierPositif("Identifiant du formulaire à supprimer : ");
                try {
                    this.gestionnaire.supprimerFormulaire(id);
                    System.out.println("Formulaire supprimé");
                } catch (Exception e) {
                    System.out.println("Erreur lors de la suppression : " + e.getMessage());
                }
                this.attendreRetour();
                break;
            }
            case "3": {
                this.menuRechercheFormulaires();
                this.attendreRetour();
                break;
            }
            case "4": {
                this.menuRechercheEtudiant();
                this.attendreRetour();
                break;
            }
            case "5": {
                try {
                    this.gestionnaire.calculerStatistiques();
                } catch (Exception e) {
                    System.out.println("Erreur lors du calcul des statistiques : " + e.getMessage());
                }
                this.attendreRetour();
                break;
            }
            case "6": {
                try {
                    this.gestionnaire.afficherGraphe();
                } catch (Exception e) {
                    System.out.println("Erreur lors de l'affichage du graphe : " + e.getMessage());
                }
                this.attendreRetour();
                break;
            }
            case "7": {
                try {
                    final List<Etudiant> recidivistes = this.gestionnaire.detecterRecidivistes();
                    if (recidivistes.isEmpty()) {
                        System.out.println("Aucun récidiviste détecté.");
                    } else {
                        System.out.println(recidivistes.size() + " récidiviste(s) détecté(s) :");
                        for (Etudiant e2 : recidivistes) {
                            System.out.println("  - " + e2.getNom() + " " + e2.getPrenom()
                                    + " | N° " + e2.getNumeroApprenant()
                                    + " | Cursus : " + e2.getCursus());
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Erreur lors de la détection des récidivistes : " + e.getMessage());
                }
                this.attendreRetour();
                break;
            }
            case "8": {
                return false;
            }
            default: {
                System.out.println("Erreur : option invalide. Veuillez choisir un numéro entre 1 et 8.");
                break;
            }
        }
        return true;
    }

    private void menuAjoutFormulaire() {
        try {
            System.out.println("=== Création d'un formulaire ===");
            final String code = lireChaine("Code ECUE [DEVLO, POO, SHL, TEST, DEEP, DS, TNI, TNS, ASI, ANG]: ");
            final LocalDate date = lireDate("Date passage (AAAA-MM-JJ) : ");
            final LocalTime heure = lireHeure("Heure passage (HH:MM) : ");
            final long minutes = lireLongPositif("Durée (minutes) : ");
            final Modalite modalite = lireModalite("Modalité " + Arrays.toString(Modalite.values()) + " : ");

            final Epreuve epreuve = new Epreuve(code, date, heure, Duration.ofMinutes(minutes), modalite);
            final Formulaire formulaire = new Formulaire();
            formulaire.setEpreuve(epreuve);

            int nbEtudiants;
            while (true) {
                nbEtudiants = lireEntierPositif("Nombre d'étudiants impliqués : ");
                if (nbEtudiants >= 1) {break;}
                System.out.println("Erreur : il doit y avoir au moins 1 étudiant impliqué.");
            }

            for (int i = 1; i <= nbEtudiants; i++) {
                System.out.println("Étudiant " + i + " :");

                final String nom    = lireChaine("  Nom : ");
                final String prenom = lireChaine("  Prénom : ");
                final int    numero = lireEntierPositif("  Numéro apprenant : ");
                final Cursus cursus = lireCursus("  Cursus " + Arrays.toString(Cursus.values()) + " : ");

                formulaire.ajouterEtudiant(new Etudiant(nom, prenom, numero, cursus));
            }

            this.gestionnaire.ajouterFormulaire(formulaire);
            this.gestionnaire.ajouterEpreuve(epreuve);
            System.out.println("Formulaire ajouté avec succès. ID: " + formulaire.getIdentifiantNumeriqueUnique());

        } catch (IllegalArgumentException e) {
            System.out.println("Erreur inattendue lors de la création du formulaire : " + e.getMessage());
        }
    }

    private void menuRechercheFormulaires() {
        System.out.println("Recherche de formulaires ");
        System.out.println("1. Par étudiant (numéro apprenant)");
        System.out.println("2. Par épreuve (code ECUE)");
        System.out.print("Votre choix : ");
        final String sousChoix = this.scanner.nextLine().trim();

        if (sousChoix.equals("1")) {
            final int num = lireEntierPositif("Saisir le numéro apprenant : ");
            try {
                final List<Formulaire> resultats = this.gestionnaire.getFormulairesParEtudiant(num);
                afficherResultatsFormulaires(resultats);
            } catch (IllegalArgumentException e) {
                System.out.println("Erreur lors de la recherche : " + e.getMessage());
            }

        } else if (sousChoix.equals("2")) {
            final String code = lireChaine("Saisir le code ECUE : ");
            try {
                final List<Formulaire> resultats = this.gestionnaire.getFormulairesParEpreuve(code);
                afficherResultatsFormulaires(resultats);
            } catch (IllegalArgumentException e) {
                System.out.println("Erreur lors de la recherche : " + e.getMessage());
            }

        } else {
            System.out.println("Erreur : option invalide. Choisissez 1 ou 2.");
        }
    }

    private void afficherResultatsFormulaires(List<Formulaire> resultats) {
        if (resultats == null || resultats.isEmpty()) {
            System.out.println("Aucun formulaire n'a été trouvé");
        } else {
            System.out.println(resultats.size() + " formulaire(s) trouvé(s) :");
            for (Formulaire f : resultats) {
                System.out.println("  - ID " + f.getIdentifiantNumeriqueUnique()
                        + " , épreuve : " + f.getEpreuve().getCodeECUE()
                        + " , créé le : " + f.getDateCreation()
                        + " , Fraudes : " + f.getFraudes().size());
            }
        }
    }

    private void menuRechercheEtudiant() {
        System.out.println("Recherche d'un étudiant ");
        System.out.println("1. Par nom");
        System.out.println("2. Par prénom");
        System.out.println("3. Par numéro apprenant");
        System.out.print("Votre choix : ");
        final String sousChoix = this.scanner.nextLine().trim();

        if (sousChoix.equals("1")) {
            final String nom = lireChaine("Saisir le nom : ");
            try {
                final List<Etudiant> resultats = this.gestionnaire.rechercherParNom(nom);
                afficherResultatsEtudiants(resultats);
            } catch (IllegalArgumentException e) {
                System.out.println("Erreur lors de la recherche : " + e.getMessage());
            }

        } else if (sousChoix.equals("2")) {
            final String prenom = lireChaine("Saisir le prénom : ");
            try {
                final List<Etudiant> resultats = this.gestionnaire.rechercherParPrenom(prenom);
                afficherResultatsEtudiants(resultats);
            } catch (IllegalArgumentException e) {
                System.out.println("Erreur lors de la recherche : " + e.getMessage());
            }

        } else if (sousChoix.equals("3")) {
            final int num = lireEntierPositif("Saisir le numéro apprenant : ");
            try {
                final Etudiant etudiant = this.gestionnaire.trouverParNumero(num);
                if (etudiant == null) {
                    System.out.println("Aucun étudiant trouvé");
                } else {
                    System.out.println("Etudiant trouvé : " + etudiant.getNom() + " " + etudiant.getPrenom());
                }
            } catch (Exception e) {
                System.out.println("Erreur lors de la recherche : " + e.getMessage());
            }

        } else {
            System.out.println("Erreur : option invalide. Choisissez 1, 2 ou 3.");
        }
    }

    private void afficherResultatsEtudiants(List<Etudiant> resultats) {
        if (resultats == null || resultats.isEmpty()) {
            System.out.println("Aucun étudiant n'a été trouvé");
        } else {
            System.out.println(resultats.size() + " étudiant(s) trouvé(s) :");
            for (Etudiant e : resultats) {
                System.out.println("  - " + e.getNom() + " " + e.getPrenom()
                        + " , numéro " + e.getNumeroApprenant()
                        + " , Cursus : " + e.getCursus());
            }
        }
    }

    public static void main(final String[] args) {
        final GestionnaireFormulaires gestionnaire = new GestionnaireFormulaires();

        final Epreuve ep1 = new Epreuve("POO",   LocalDate.of(2026, 6, 3), LocalTime.of(9,  0), Duration.ofMinutes(120L), Modalite.ECRIT);
        final Epreuve ep2 = new Epreuve("DEVLO", LocalDate.of(2026, 6, 4), LocalTime.of(14, 0), Duration.ofMinutes(60L),  Modalite.QCM);

        final Etudiant e1 = new Etudiant("Boussard", "Noah",   11111, Cursus.E3e);
        final Etudiant e2 = new Etudiant("Beucher",  "Arthur", 22222, Cursus.E2);
        final Etudiant e3 = new Etudiant("Bernard",  "Ugal",   33333, Cursus.E1);
        final Etudiant e4 = new Etudiant("Cluzeau",  "Thomas", 44444, Cursus.E4);

        final FraudeIAG          f1 = new FraudeIAG(LocalDate.now(),          "Usage ChatGPT",    "copie écran",       "ChatGPT");
        final FraudePapier       f2 = new FraudePapier(LocalDate.now(),       "Feuille cachée",   "formules",          "A4", true);
        final FraudeCalculatrice f3 = new FraudeCalculatrice(LocalDate.now(), "Calc programmée",  "programme stocké",  "Casio", "ALGO.prg");
        final FraudeIAGConnectee f4 = new FraudeIAGConnectee(LocalDate.now(), "IAG en ligne",     "via réseau",        "Gemini", "192.168.1.42");

        final Formulaire form1 = new Formulaire(ep1, List.of(e1, e2), List.of(f1),     (Sanction) null);
        final Formulaire form2 = new Formulaire(ep1, List.of(e3),     List.of(f2, f3), (Sanction) null);
        final Formulaire form3 = new Formulaire(ep2, List.of(e2, e4), List.of(f4),     (Sanction) null);

        gestionnaire.ajouterFormulaire(form1);
        gestionnaire.ajouterFormulaire(form2);
        gestionnaire.ajouterFormulaire(form3);
        gestionnaire.ajouterEpreuve(ep1);
        gestionnaire.ajouterEpreuve(ep2);

        final Interface ui = new Interface(gestionnaire);
        ui.lancerMenu();
    }
}
