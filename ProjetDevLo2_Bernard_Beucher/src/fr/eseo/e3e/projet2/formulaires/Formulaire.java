package fr.eseo.e3e.projet2.formulaires;
import fr.eseo.e3e.projet2.formulaires.fraudes.Fraude;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represente un formulaire de signalement de fraude pour une épreuve donnée.
 *
 * Un formulaire contient un identifiant unique, des dates de création et de dernière modification,
 * une épreuve associée, une sanction éventuelle, ainsi que des listes d'étudiants et de fraudes.
 */
public class Formulaire {
    private static int compteur = 0;
    private int identifiantNumeriqueUnique;
    private LocalDate dateCreation;
    private LocalDate dateDerniereModification;
    private Epreuve epreuve;
    private Sanction sanction;
    private List<Etudiant> etudiants;
    private List<Fraude> fraudes;

    // Constructeur par défaut
    public Formulaire(){
        this.identifiantNumeriqueUnique = ++compteur;
        this.dateCreation = LocalDate.now();
        this.dateDerniereModification = LocalDate.now();
        this.etudiants = new ArrayList<>();
        this.fraudes = new ArrayList<>();
    }

    /**
     * Construit un formulaire avec toutes ses informations.
     *
     * @param epreuve l'épreuve associée au formulaire
     * @param etudiants la liste des étudiants impliqués dans le formulaire
     * @param fraudes la liste des fraudes associées au formulaire
     * @param sanction la sanction éventuelle associée au formulaire
     */
    public Formulaire(Epreuve epreuve, List<Etudiant> etudiants, List<Fraude> fraudes, Sanction sanction) {
        this();
        this.epreuve = epreuve;
        if (etudiants != null) {this.etudiants.addAll(etudiants);}
        if (fraudes != null) {this.fraudes.addAll(fraudes);}
        this.sanction = sanction;
    }

    /**
     * Ajoute un étudiant à la liste des étudiants impliqués dans le formulaire.
     *
     * @param e l'étudiant à ajouter
     */
    public void ajouterEtudiant(Etudiant e) {
        this.etudiants.add(e);
        this.dateDerniereModification = LocalDate.now();
    }

    /**
     * Ajoute une fraude à la liste des fraudes associées au formulaire.
     *
     * @param f la fraude à ajouter
     */
    public void ajouterFraude(Fraude f) {
        this.fraudes.add(f);
        this.dateDerniereModification = LocalDate.now();
    }

    /**
     * Retourne la sanction associée au formulaire.
     *
     * @return la sanction associée au formulaire
     */
    public Sanction getSanction() {
        return sanction;
    }

    /**
     * Retourne la liste des étudiants impliqués dans le formulaire.
     *
     * @return la liste des étudiants impliqués dans le formulaire
     */
    public List<Etudiant> getEtudiants() {
        return etudiants;
    }

    /**
     * Retourne la liste des fraudes associées au formulaire.
     *
     * @return la liste des fraudes associées au formulaire
     */
    public List<Fraude> getFraudes() {
        return fraudes;
    }

    /**
     * Retourne l'épreuve associée au formulaire.
     *
     * @return l'épreuve associée au formulaire
     */
    public Epreuve getEpreuve() {
        return epreuve;
    }

    /**
     * Retourne la date de dernière modification du formulaire.
     *
     * @return la date de dernière modification du formulaire
     */
    public LocalDate getDateDerniereModification() {
        return dateDerniereModification;
    }

    /**
     * Retourne l'identifiant numérique unique du formulaire.
     *
     * @return l'identifiant numérique unique du formulaire
     */
    public int getIdentifiantNumeriqueUnique() {
        return identifiantNumeriqueUnique;
    }

    /**
     * Retourne la date de création du formulaire.
     *
     * @return la date de création du formulaire
     */
    public LocalDate getDateCreation() {
        return dateCreation;
    }

    /**
     * Définit la sanction associée au formulaire.
     *
     * @param sanction la sanction à associer au formulaire
     */
    public void setSanction(Sanction sanction) {
        this.sanction = sanction;
    }

    /**
     * Définit l'épreuve associée au formulaire.
     *
     * @param epreuve l'épreuve à associer au formulaire
     */
    public void setEpreuve(Epreuve epreuve) {
        this.epreuve = epreuve;
        this.dateDerniereModification = LocalDate.now();
    }

    /**
     * Vérifie si ce formulaire est égal à un autre objet.
     *
     * @param o l'objet à comparer
     * @return true si les deux objets sont égaux, false sinon
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Formulaire that)) {return false;}
        return identifiantNumeriqueUnique == that.identifiantNumeriqueUnique
                && Objects.equals(dateCreation, that.dateCreation)
                && Objects.equals(dateDerniereModification, that.dateDerniereModification)
                && Objects.equals(epreuve, that.epreuve) && Objects.equals(sanction, that.sanction)
                && Objects.equals(etudiants, that.etudiants) && Objects.equals(fraudes, that.fraudes);
    }

    /**
     * @return hashCode du formulaire basé sur ses attributs
     */
    @Override
    public int hashCode() {
        return Objects.hash(identifiantNumeriqueUnique, dateCreation, dateDerniereModification,
                epreuve, sanction, etudiants, fraudes);
    }

    /**
     * @return chaîne de caractères représentant le formulaire
     */
    @Override
    public String toString() {
        return "Formulaire{" +
                "identifiantNumeriqueUnique=" + identifiantNumeriqueUnique +
                ", dateCreation=" + dateCreation +
                ", dateDerniereModification=" + dateDerniereModification +
                ", epreuve=" + (epreuve != null ? epreuve.getCodeECUE() : "null") +
                ", etudiants=" + etudiants.size() +
                ", fraudes=" + fraudes.size() +
                '}';
    }
}
