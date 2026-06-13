package fr.eseo.e3e.projet2.formulaires;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Représente une sanction appliquée à un étudiant.
 *
 * Une sanction est caractérisée par un type, une date de décision et un commentaire.
 */
public class Sanction {
    private TypeSanction type;
    private LocalDate dateDecision;
    private String commentaire;

    // Constructeur par défaut
    public Sanction(){
        this.dateDecision = LocalDate.now();
    }

    /**
     * Construit une sanction avec toutes ses informations.
     *
     * @param type le type de la sanction
     * @param dateDecision la date de décision de la sanction
     * @param commentaire le commentaire associé à la sanction
     */
    public Sanction(TypeSanction type, LocalDate dateDecision, String commentaire){
        this.type = type;
        this.dateDecision = dateDecision;
        this.commentaire = commentaire;
    }

    /**
     * Retourne le type de la sanction.
     *
     * @return le type de la sanction
     */
    public TypeSanction getType() {
        return type;
    }

    /**
     * Définit le type de la sanction.
     *
     * @param type le type de la sanction à définir
     */
    public void setType(TypeSanction type) {
        this.type = type;
    }

    /**
     * Retourne la date de décision de la sanction.
     *
     * @return la date de décision de la sanction
     */
    public LocalDate getDateDecision() {
        return dateDecision;
    }

    /**
     * Définit la date de décision de la sanction.
     *
     * @param dateDecision la date de décision de la sanction à définir
     */
    public void setDateDecision(LocalDate dateDecision) {
        this.dateDecision = dateDecision;
    }

    /**
     * Retourne le commentaire associé à la sanction.
     *
     * @return le commentaire associé à la sanction
     */
    public String getCommentaire() {
        return commentaire;
    }

    /**
     * Définit le commentaire associé à la sanction.
     *
     * @param commentaire le commentaire à définir
     */
    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    /**
     * Vérifie si deux sanctions sont égales en comparant leur type, date de décision et commentaire.
     *
     * @param o l'objet à comparer avec cette sanction
     * @return true si les sanctions sont égales, false sinon
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Sanction sanction)) {return false;}
        return type == sanction.type && Objects.equals(dateDecision, sanction.dateDecision) && Objects.equals(commentaire, sanction.commentaire);
    }

    /**
     * @return hashCode de la sanction basé sur ses attributs
     */
    @Override
    public int hashCode() {
        return Objects.hash(type, dateDecision, commentaire);
    }

    /**
     * @return chaîne de caractères représentant la sanction
     */
    @Override
    public String toString() {
        return "Sanction{" +
                "type=" + type +
                ", dateDecision=" + dateDecision +
                ", commentaire='" + commentaire + '\'' +
                '}';
    }
}
