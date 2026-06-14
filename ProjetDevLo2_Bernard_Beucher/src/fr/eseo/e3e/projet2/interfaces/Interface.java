package fr.eseo.e3e.projet2.interfaces;
import fr.eseo.e3e.projet2.app.GestionnaireFormulaires;
import fr.eseo.e3e.projet2.formulaires.Cursus;
import fr.eseo.e3e.projet2.formulaires.Etudiant;
import fr.eseo.e3e.projet2.formulaires.Epreuve;
import fr.eseo.e3e.projet2.formulaires.Formulaire;
import fr.eseo.e3e.projet2.formulaires.Modalite;
import fr.eseo.e3e.projet2.formulaires.Sanction;
import fr.eseo.e3e.projet2.formulaires.fraudes.Fraude;
import fr.eseo.e3e.projet2.formulaires.fraudes.FraudeCalculatrice;
import fr.eseo.e3e.projet2.formulaires.fraudes.FraudeIAG;
import fr.eseo.e3e.projet2.formulaires.fraudes.FraudeIAGConnectee;
import fr.eseo.e3e.projet2.formulaires.fraudes.FraudePapier;
import fr.eseo.e3e.projet2.formulaires.TypeSanction;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Interface utilisateur pour gerer les formulaires de fraude.
 * Permet d'ajouter, supprimer, rechercher des formulaires,
 * calculer des stats, afficher le graphe et detecter les recidivistes.
 */
public class Interface {

    private GestionnaireFormulaires gestionnaire;
    private Etudiant etudiant;
    private Scanner scanner;

    public Interface() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * constructeur avec le gestionnaire en param
     * @param gestionnaire le gestionnaire a utiliser
     */
    public Interface(final GestionnaireFormulaires gestionnaire) {
        this.gestionnaire = gestionnaire;
        this.scanner = new Scanner(System.in);
    }

    // lit une chaine non vide
    private String lireChaine(String invite) {
        while (true) {
            System.out.print(invite);
            try {
                String valeur = this.scanner.nextLine().trim();
                if (!valeur.isEmpty()) {
                    return valeur;
                }
                System.out.println("la saisie peut pas etre vide, reessayez");
            } catch (IllegalStateException e) {
                throw new IllegalStateException("scanner ferme : " + e.getMessage(), e);
            }
        }
    }

    // lit un entier >= 0
    private int lireEntierPositif(String invite) {
        while (true) {
            System.out.print(invite);
            try {
                String saisie = this.scanner.nextLine().trim();
                try {
                    int valeur = Integer.parseInt(saisie);
                    if (valeur < 0) {
                        System.out.println("la valeur doit etre positive ou nulle");
                        continue;
                    }
                    return valeur;
                } catch (NumberFormatException e) {
                    System.out.println("entier invalide, ex: 42");
                }
            } catch (IllegalStateException e) {
                throw new IllegalStateException("scanner ferme : " + e.getMessage(), e);
            }
        }
    }

    // lit une date format AAAA-MM-JJ
    private LocalDate lireDate(String invite) {
        while (true) {
            System.out.print(invite);
            try {
                String saisie = this.scanner.nextLine().trim();
                try {
                    return LocalDate.parse(saisie);
                } catch (DateTimeParseException e) {
                    System.out.println("format invalide, utilisez AAAA-MM-JJ (ex: 2026-06-03)");
                }
            } catch (IllegalStateException e) {
                throw new IllegalStateException("scanner ferme : " + e.getMessage(), e);
            }
        }
    }

    // lit une heure format HH:MM
    private LocalTime lireHeure(String invite) {
        while (true) {
            System.out.print(invite);
            try {
                String saisie = this.scanner.nextLine().trim();
                try {
                    return LocalTime.parse(saisie);
                } catch (DateTimeParseException e) {
                    System.out.println("format invalide, utilisez HH:MM (ex: 09:00)");
                }
            } catch (IllegalStateException e) {
                throw new IllegalStateException("scanner ferme : " + e.getMessage(), e);
            }
        }
    }

    // lit une modalite valide
    private Modalite lireModalite(String invite) {
        while (true) {
            System.out.print(invite);
            try {
                String saisie = this.scanner.nextLine().trim().toUpperCase();
                try {
                    return Modalite.valueOf(saisie);
                } catch (IllegalArgumentException e) {
                    System.out.println("modalite inconnue, valeurs possibles : " + Arrays.toString(Modalite.values()));
                }
            } catch (IllegalStateException e) {
                throw new IllegalStateException("scanner ferme : " + e.getMessage(), e);
            }
        }
    }

    // lit un cursus valide
    private Cursus lireCursus(String invite) {
        while (true) {
            System.out.print(invite);
            try {
                String saisie = this.scanner.nextLine().trim();
                try {
                    return Cursus.valueOf(saisie);
                } catch (IllegalArgumentException e) {
                    System.out.println("cursus inconnu, valeurs possibles : " + Arrays.toString(Cursus.values()));
                }
            } catch (IllegalStateException e) {
                throw new IllegalStateException("scanner ferme : " + e.getMessage(), e);
            }
        }
    }

    private static final List<String> CODES_ECUE_AUTORISES = List.of(
            "DEVLO", "POO", "SHL", "TEST", "DEEP", "DS", "TNI", "TNS", "ASI", "ANG"
    );

    // lit un code ecue valide
    private String lireCodeECUE(String invite) {
        while (true) {
            System.out.print(invite);
            try {
                String saisie = this.scanner.nextLine().trim().toUpperCase();
                if (CODES_ECUE_AUTORISES.contains(saisie)) {
                    return saisie;
                }
                System.out.println("code ecue inconnu, valeurs possibles : " + CODES_ECUE_AUTORISES);
            } catch (IllegalStateException e) {
                throw new IllegalStateException("scanner ferme : " + e.getMessage(), e);
            }
        }
    }

    // lit oui ou non
    private boolean lireBoolean(String invite) {
        while (true) {
            System.out.print(invite);
            try {
                String saisie = this.scanner.nextLine().trim().toLowerCase();
                if (saisie.equals("oui") || saisie.equals("o") || saisie.equals("yes") || saisie.equals("y")) {
                    return true;
                } else if (saisie.equals("non") || saisie.equals("n") || saisie.equals("no")) {
                    return false;
                }
                System.out.println("repondez par oui ou non");
            } catch (IllegalStateException e) {
                throw new IllegalStateException("scanner ferme : " + e.getMessage(), e);
            }
        }
    }

    private TypeSanction lireTypeSanction(String invite) {
        while (true) {
            System.out.print(invite);
            try {
                String saisie = this.scanner.nextLine().trim().toUpperCase();
                try {
                    return TypeSanction.valueOf(saisie);
                } catch (IllegalArgumentException e) {
                    System.out.println("type inconnu, valeurs possibles : " + Arrays.toString(TypeSanction.values()));
                }
            } catch (IllegalStateException e) {
                throw new IllegalStateException("scanner ferme : " + e.getMessage(), e);
            }
        }
    }

    /**
     * saisie d'une fraude selon son type
     * retourne null si type invalide
     */
    private Fraude saisirFraude() {
        System.out.println("  type de fraude :");
        System.out.println("    1. IAG (chatgpt etc)");
        System.out.println("    2. IAG connectee");
        System.out.println("    3. calculatrice");
        System.out.println("    4. papier");
        System.out.print("  votre choix : ");

        try {
            String choix = this.scanner.nextLine().trim();

            final LocalDate dateReleve = lireDate("  date du releve (AAAA-MM-JJ) : ");
            final String description = lireChaine("  description : ");
            final String contenu = lireChaine("  contenu / piece a conviction : ");

            switch (choix) {
                case "1": {
                    final String nomService = lireChaine("  nom du service (ex: ChatGPT, Gemini) : ");
                    return new FraudeIAG(dateReleve, description, contenu, nomService);
                }
                case "2": {
                    final String nomService = lireChaine("  nom du service (ex: ChatGPT, Gemini) : ");
                    final String adresseIP = lireChaine("  adresse IP (ex: 192.168.1.42) : ");
                    return new FraudeIAGConnectee(dateReleve, description, contenu, nomService, adresseIP);
                }
                case "3": {
                    final String marque = lireChaine("  marque calculatrice (ex: Casio) : ");
                    final String programme = lireChaine("  nom du programme stocke (ex: ALGO.prg) : ");
                    return new FraudeCalculatrice(dateReleve, description, contenu, marque, programme);
                }
                case "4": {
                    final String dimension = lireChaine("  dimension du papier (ex: A4) : ");
                    final boolean plie = lireBoolean("  plie ? (oui/non) : ");
                    return new FraudePapier(dateReleve, description, contenu, dimension, plie);
                }
                default: {
                    System.out.println("  type invalide, fraude ignoree");
                    return null;
                }
            }
        } catch (IllegalStateException e) {
            throw new IllegalStateException("erreur lecture fraude : " + e.getMessage(), e);
        }
    }

    private void attendreRetour() {
        System.out.println("appuyer sur entree pour revenir au menu");
        try {
            this.scanner.nextLine();
        } catch (IllegalStateException e) {
            System.out.println("erreur de lecture");
        }
    }

    /**
     * lance le menu principal
     * @throws IllegalStateException si le gestionnaire est null
     */
    public void lancerMenu() {
        if (this.gestionnaire == null) {
            throw new IllegalStateException("gestionnaire non initialise");
        }
        String choix;
        for (boolean continuer = true; continuer; continuer = this.traiterOption(choix)) {
            this.afficherMenu();
            try {
                choix = this.scanner.nextLine();
            } catch (IllegalStateException e) {
                System.out.println("erreur critique de lecture : " + e.getMessage());
                break;
            }
        }
        System.out.println("fermeture de l'application");
    }

    private void afficherMenu() {
        System.out.println("\nmenu principal");
        System.out.println("1. ajouter un formulaire");
        System.out.println("2. supprimer un formulaire");
        System.out.println("3. rechercher des formulaires");
        System.out.println("4. rechercher un etudiant");
        System.out.println("5. calculer les statistiques");
        System.out.println("6. afficher le graphe des fraudeurs");
        System.out.println("7. detecter les recidivistes");
        System.out.println("8. quitter");
        System.out.print("votre choix : ");
    }

    /**
     * traite le choix de l'utilisateur
     * @param choix choix saisi
     * @return false si on quitte, true sinon
     */
    private boolean traiterOption(final String choix) {
        if (choix == null || choix.trim().isEmpty()) {
            System.out.println("aucun choix saisi");
            return true;
        }

        final String upperCase = choix.trim().toUpperCase();
        switch (upperCase) {
            case "1": {
                try {
                    this.menuAjoutFormulaire();
                } catch (IllegalStateException e) {
                    System.out.println("erreur critique creation formulaire : " + e.getMessage());
                } catch (Exception e) {
                    System.out.println("erreur inattendue : " + e.getMessage());
                }
                this.attendreRetour();
                break;
            }
            case "2": {
                try {
                    List<Formulaire> tousLesFormulaires = this.gestionnaire.getFormulaires();
                    if (tousLesFormulaires == null || tousLesFormulaires.isEmpty()) {
                        System.out.println("aucun formulaire a supprimer");
                    } else {
                        System.out.println("formulaires existants :");
                        for (Formulaire f : tousLesFormulaires) {
                            if (f == null) { continue; }
                            System.out.println("  id " + f.getIdentifiantNumeriqueUnique()
                                    + " | ecue : " + (f.getEpreuve() != null ? f.getEpreuve().getCodeECUE() : "N/A")
                                    + " | date : " + f.getDateCreation()
                                    + " | etudiants : " + f.getEtudiants().size()
                                    + " | fraudes : " + f.getFraudes().size());
                        }
                        int id = lireEntierPositif("id du formulaire a supprimer : ");
                        this.gestionnaire.supprimerFormulaire(id);
                        System.out.println("formulaire supprime");
                    }
                } catch (IllegalStateException e) {
                    System.out.println("erreur de lecture : " + e.getMessage());
                } catch (NullPointerException e) {
                    System.out.println("gestionnaire non initialise");
                } catch (Exception e) {
                    System.out.println("erreur suppression : " + e.getMessage());
                }
                this.attendreRetour();
                break;
            }
            case "3": {
                try {
                    this.menuRechercheFormulaires();
                } catch (IllegalStateException e) {
                    System.out.println("erreur de lecture : " + e.getMessage());
                } catch (Exception e) {
                    System.out.println("erreur inattendue : " + e.getMessage());
                }
                this.attendreRetour();
                break;
            }
            case "4": {
                try {
                    this.menuRechercheEtudiant();
                } catch (IllegalStateException e) {
                    System.out.println("erreur de lecture : " + e.getMessage());
                } catch (Exception e) {
                    System.out.println("erreur inattendue : " + e.getMessage());
                }
                this.attendreRetour();
                break;
            }
            case "5": {
                try {
                    this.gestionnaire.calculerStatistiques();
                } catch (NullPointerException e) {
                    System.out.println("donnees manquantes pour les stats");
                } catch (ArithmeticException e) {
                    System.out.println("erreur arithmetique : " + e.getMessage());
                } catch (Exception e) {
                    System.out.println("erreur stats : " + e.getMessage());
                }
                this.attendreRetour();
                break;
            }
            case "6": {
                try {
                    this.gestionnaire.afficherGraphe();
                } catch (NullPointerException e) {
                    System.out.println("donnees manquantes pour le graphe");
                } catch (Exception e) {
                    System.out.println("erreur graphe : " + e.getMessage());
                }
                this.attendreRetour();
                break;
            }
            case "7": {
                try {
                    final List<Etudiant> recidivistes = this.gestionnaire.detecterRecidivistes();
                    if (recidivistes == null) {
                        System.out.println("erreur : resultat null");
                    } else if (recidivistes.isEmpty()) {
                        System.out.println("aucun recidiviste detecte");
                    } else {
                        System.out.println(recidivistes.size() + " recidiviste(s) :");
                        for (Etudiant e2 : recidivistes) {
                            if (e2 == null) { System.out.println("  - [etudiant invalide]"); continue; }
                            System.out.println("  - " + e2.getNom() + " " + e2.getPrenom()
                                    + " | n° " + e2.getNumeroApprenant()
                                    + " | cursus : " + e2.getCursus());
                        }
                    }
                } catch (NullPointerException e) {
                    System.out.println("donnees manquantes");
                } catch (Exception e) {
                    System.out.println("erreur recidivistes : " + e.getMessage());
                }
                this.attendreRetour();
                break;
            }
            case "8": {
                return false;
            }
            default: {
                System.out.println("option invalide, choisissez entre 1 et 8");
                break;
            }
        }
        return true;
    }

    /**
     * menu pour creer un nouveau formulaire
     * demande l'epreuve, les etudiants et leurs fraudes
     */
    private void menuAjoutFormulaire() {
        System.out.println("=== nouveau formulaire ===");
        final String code = lireCodeECUE("code ecue " + CODES_ECUE_AUTORISES + " : ");
        final LocalDate date = lireDate("date de l'epreuve (AAAA-MM-JJ) : ");
        final LocalTime heure = lireHeure("heure (HH:MM) : ");
        final int minutes = lireEntierPositif("duree en minutes : ");
        final Modalite modalite = lireModalite("modalite " + Arrays.toString(Modalite.values()) + " : ");

        final Epreuve epreuve = new Epreuve(code, date, heure, Duration.ofMinutes(minutes), modalite);
        final Formulaire formulaire = new Formulaire();
        formulaire.setEpreuve(epreuve);

        int nbEtudiants;
        while (true) {
            nbEtudiants = lireEntierPositif("combien d'etudiants impliques : ");
            if (nbEtudiants >= 1) { break; }
            System.out.println("il faut au moins 1 etudiant");
        }

        for (int i = 1; i <= nbEtudiants; i++) {
            System.out.println("--- etudiant " + i + " ---");

            final String nom = lireChaine("  nom : ");
            final String prenom = lireChaine("  prenom : ");

            // on cherche si l'etudiant existe deja
            Etudiant etudiantTrouve = null;
            try {
                List<Etudiant> correspondances = this.gestionnaire.rechercherParNom(nom);
                for (Etudiant e : correspondances) {
                    if (e.getPrenom().equalsIgnoreCase(prenom)) {
                        etudiantTrouve = e;
                        break;
                    }
                }
            } catch (Exception e) {
                System.out.println("  avertissement : recherche impossible (" + e.getMessage() + ")");
            }

            final Etudiant etudiant;
            if (etudiantTrouve != null) {
                System.out.println("  etudiant deja connu : " + etudiantTrouve.getNom() + " " + etudiantTrouve.getPrenom()
                        + " | n° " + etudiantTrouve.getNumeroApprenant()
                        + " | cursus : " + etudiantTrouve.getCursus());
                etudiant = etudiantTrouve;
            } else {
                System.out.println("  etudiant inconnu, infos complementaires :");
                final int numero = lireEntierPositif("  numero apprenant : ");
                final Cursus cursus = lireCursus("  cursus " + Arrays.toString(Cursus.values()) + " : ");
                etudiant = new Etudiant(nom, prenom, numero, cursus);
            }

            formulaire.ajouterEtudiant(etudiant);

            // fraudes pour cet etudiant
            System.out.println("  fraudes pour " + prenom + " " + nom + " :");
            int nbFraudes;
            while (true) {
                nbFraudes = lireEntierPositif("  nombre de fraudes (0 si aucune) : ");
                if (nbFraudes >= 0) { break; }
                System.out.println("  nombre negatif pas possible");
            }

            for (int j = 1; j <= nbFraudes; j++) {
                System.out.println("  fraude " + j + "/" + nbFraudes + " :");
                try {
                    Fraude fraude = saisirFraude();
                    if (fraude != null) {
                        formulaire.ajouterFraude(fraude);
                        System.out.println("  fraude ajoutee");
                    } else {
                        System.out.println("  fraude ignoree");
                    }
                } catch (IllegalStateException e) {
                    System.out.println("  erreur saisie fraude : " + e.getMessage());
                } catch (Exception e) {
                    System.out.println("  erreur inattendue : " + e.getMessage());
                }
            }
        }

        Sanction sanction = null;
        boolean ajouterSanction = lireBoolean("une sanction a associer ? (oui/non) : ");
        if (ajouterSanction) {
            try {
                final TypeSanction type = lireTypeSanction("type de sanction " + Arrays.toString(TypeSanction.values()) + " : ");
                final LocalDate dateDecision = lireDate("date de decision (AAAA-MM-JJ) : ");
                final String commentaire = lireChaine("commentaire : ");
                sanction = new Sanction(type, dateDecision, commentaire);
                System.out.println("sanction enregistree");
            } catch (IllegalStateException e) {
                System.out.println("erreur saisie sanction : " + e.getMessage());
            } catch (Exception e) {
                System.out.println("erreur inattendue sanction : " + e.getMessage());
            }
        }
        formulaire.setSanction(sanction);

        Sanction nouvelleSanction = null;
        boolean avecSanction = lireBoolean("une sanction a associer ? (oui/non) : ");
        if (avecSanction) {
            try {
                final TypeSanction type = lireTypeSanction("type de sanction " + Arrays.toString(TypeSanction.values()) + " : ");
                final LocalDate dateDecision = lireDate("date de decision (AAAA-MM-JJ) : ");
                final String commentaire = lireChaine("commentaire : ");
                nouvelleSanction = new Sanction(type, dateDecision, commentaire);
                System.out.println("sanction enregistree");
            } catch (IllegalStateException e) {
                System.out.println("erreur saisie sanction : " + e.getMessage());
            } catch (Exception e) {
                System.out.println("erreur inattendue sanction : " + e.getMessage());
            }
        }
        formulaire.setSanction(sanction);

        if (this.gestionnaire == null) {
            throw new NullPointerException("gestionnaire non initialise");
        }
        this.gestionnaire.ajouterFormulaire(formulaire);
        this.gestionnaire.ajouterEpreuve(epreuve);
        System.out.println("formulaire ajoute, id : " + formulaire.getIdentifiantNumeriqueUnique());
    }

    // recherche de formulaires par etudiant ou par ecue
    private void menuRechercheFormulaires() {
        System.out.println("recherche de formulaires");
        System.out.println("1. par etudiant (numero apprenant)");
        System.out.println("2. par epreuve (code ecue)");
        System.out.print("votre choix : ");

        final String sousChoix;
        try {
            sousChoix = this.scanner.nextLine().trim();
        } catch (IllegalStateException e) {
            throw new IllegalStateException("erreur de lecture : " + e.getMessage(), e);
        }

        if (sousChoix.equals("1")) {
            try {
                final int num = lireEntierPositif("numero apprenant : ");
                final List<Formulaire> resultats = this.gestionnaire.getFormulairesParEtudiant(num);
                afficherResultatsFormulaires(resultats);
            } catch (IllegalArgumentException e) {
                System.out.println("erreur recherche : " + e.getMessage());
            } catch (NullPointerException e) {
                System.out.println("donnees manquantes");
            }

        } else if (sousChoix.equals("2")) {
            try {
                final String code = lireChaine("code ecue : ");
                final List<Formulaire> resultats = this.gestionnaire.getFormulairesParEpreuve(code);
                afficherResultatsFormulaires(resultats);
            } catch (IllegalArgumentException e) {
                System.out.println("erreur recherche : " + e.getMessage());
            } catch (NullPointerException e) {
                System.out.println("donnees manquantes");
            }

        } else {
            System.out.println("choix invalide, 1 ou 2 seulement");
        }
    }

    private void afficherResultatsFormulaires(List<Formulaire> resultats) {
        if (resultats == null || resultats.isEmpty()) {
            System.out.println("aucun formulaire trouve");
        } else {
            System.out.println(resultats.size() + " formulaire(s) trouve(s) :");
            for (Formulaire f : resultats) {
                try {
                    if (f == null) { System.out.println("  - [formulaire invalide]"); continue; }
                    System.out.println("  - id " + f.getIdentifiantNumeriqueUnique()
                            + " , ecue : " + (f.getEpreuve() != null ? f.getEpreuve().getCodeECUE() : "N/A")
                            + " , cree le : " + f.getDateCreation()
                            + " , fraudes : " + f.getFraudes().size());
                } catch (NullPointerException e) {
                    System.out.println("  - [donnees incompletes]");
                }
            }
        }
    }

    // recherche d'un etudiant par nom, prenom ou numero
    private void menuRechercheEtudiant() {
        System.out.println("recherche d'un etudiant");
        System.out.println("1. par nom");
        System.out.println("2. par prenom");
        System.out.println("3. par numero apprenant");
        System.out.print("votre choix : ");

        final String sousChoix;
        try {
            sousChoix = this.scanner.nextLine().trim();
        } catch (IllegalStateException e) {
            throw new IllegalStateException("erreur de lecture : " + e.getMessage(), e);
        }

        if (sousChoix.equals("1")) {
            try {
                final String nom = lireChaine("nom : ");
                final List<Etudiant> resultats = this.gestionnaire.rechercherParNom(nom);
                afficherResultatsEtudiants(resultats);
            } catch (IllegalArgumentException e) {
                System.out.println("erreur recherche : " + e.getMessage());
            } catch (NullPointerException e) {
                System.out.println("donnees manquantes");
            }

        } else if (sousChoix.equals("2")) {
            try {
                final String prenom = lireChaine("prenom : ");
                final List<Etudiant> resultats = this.gestionnaire.rechercherParPrenom(prenom);
                afficherResultatsEtudiants(resultats);
            } catch (IllegalArgumentException e) {
                System.out.println("erreur recherche : " + e.getMessage());
            } catch (NullPointerException e) {
                System.out.println("donnees manquantes");
            }

        } else if (sousChoix.equals("3")) {
            try {
                final int num = lireEntierPositif("numero apprenant : ");
                final Etudiant etudiant = this.gestionnaire.trouverParNumero(num);
                if (etudiant == null) {
                    System.out.println("aucun etudiant trouve");
                } else {
                    System.out.println("etudiant trouve : " + etudiant.getNom() + " " + etudiant.getPrenom());
                }
            } catch (NullPointerException e) {
                System.out.println("donnees manquantes");
            } catch (Exception e) {
                System.out.println("erreur recherche : " + e.getMessage());
            }

        } else {
            System.out.println("choix invalide, 1 2 ou 3");
        }
    }

    private void afficherResultatsEtudiants(List<Etudiant> resultats) {
        if (resultats == null || resultats.isEmpty()) {
            System.out.println("aucun etudiant trouve");
        } else {
            System.out.println(resultats.size() + " etudiant(s) trouve(s) :");
            for (Etudiant e : resultats) {
                try {
                    if (e == null) { System.out.println("  - [etudiant invalide]"); continue; }
                    System.out.println("  - " + e.getNom() + " " + e.getPrenom()
                            + " , n° " + e.getNumeroApprenant()
                            + " , cursus : " + e.getCursus());
                } catch (NullPointerException ex) {
                    System.out.println("  - [donnees incompletes]");
                }
            }
        }
    }

    public static void main(final String[] args) {
        final GestionnaireFormulaires gestionnaire = new GestionnaireFormulaires();
        final Epreuve ep1 = new Epreuve("POO", LocalDate.of(2026, 6, 3), LocalTime.of(9, 0), Duration.ofMinutes(120L), Modalite.ECRIT);
        final Epreuve ep2 = new Epreuve("DEVLO", LocalDate.of(2026, 6, 4), LocalTime.of(14, 0), Duration.ofMinutes(60L), Modalite.QCM);
        final Etudiant e1 = new Etudiant("Boussard", "Noah", 11111, Cursus.E3e);
        final Etudiant e2 = new Etudiant("Beucher", "Arthur", 22222, Cursus.E2);
        final Etudiant e3 = new Etudiant("Bernard", "Ugal", 33333, Cursus.E1);
        final Etudiant e4 = new Etudiant("Cluzeau", "Thomas", 44444, Cursus.E4);
        final FraudeIAG f1 = new FraudeIAG(LocalDate.now(), "Usage ChatGPT", "copie ecran", "ChatGPT");
        final FraudePapier f2 = new FraudePapier(LocalDate.now(), "Feuille cachee", "formules", "A4", true);
        final FraudeCalculatrice f3 = new FraudeCalculatrice(LocalDate.now(), "Calc programmee", "programme stocke", "Casio", "ALGO.prg");
        final FraudeIAGConnectee f4 = new FraudeIAGConnectee(LocalDate.now(), "IAG en ligne", "via reseau", "Gemini", "192.168.1.42");
        final Formulaire form1 = new Formulaire(ep1, List.of(e1, e2), List.of(f1), (Sanction) null);
        final Formulaire form2 = new Formulaire(ep1, List.of(e3), List.of(f2, f3), (Sanction) null);
        final Formulaire form3 = new Formulaire(ep2, List.of(e2, e4), List.of(f4), (Sanction) null);
        gestionnaire.ajouterFormulaire(form1);
        gestionnaire.ajouterFormulaire(form2);
        gestionnaire.ajouterFormulaire(form3);
        gestionnaire.ajouterEpreuve(ep1);
        gestionnaire.ajouterEpreuve(ep2);
        final Interface ui = new Interface(gestionnaire);
        ui.lancerMenu();
    }
}