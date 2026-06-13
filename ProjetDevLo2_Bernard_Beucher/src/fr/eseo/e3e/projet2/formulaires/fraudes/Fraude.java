package fr.eseo.e3e.projet2.formulaires.fraudes;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Classe abstraite représentant une fraude relevée dans un formulaire.
 *
 * Une fraude contient une date de relevé, une description textuelle et un contenu associé.
 * Cette classe sert de base aux différents types de fraudes.
 */

public abstract class Fraude {
    private LocalDate dateReleve;
    private String descriptionTextuelle;
    private String contenu;

    // Constructeur par défaut
    public Fraude() {}

    /**
     * Construit une fraude avec ses informations principales.
     *
     * @param dateReleve date à laquelle la fraude a été relevée
     * @param descriptionTextuelle description de la fraude
     * @param contenu contenu associé à la fraude
     */
    public Fraude(LocalDate dateReleve, String descriptionTextuelle, String contenu) {
        this.dateReleve = dateReleve;
        this.descriptionTextuelle = descriptionTextuelle;
        this.contenu = contenu;
    }

     /**
      * Retourne la date de relevé de la fraude.
      *
      * @return la date de relevé
      */
    public LocalDate getDateReleve() {
        return dateReleve;
    }

    /**
     * Modifie la date de relevé de la fraude.
     *
     * @param dateReleve nouvelle date de relevé
     */
    public void setDateReleve(LocalDate dateReleve) {
        this.dateReleve = dateReleve;
    }

    /**
     * Retourne la description textuelle de la fraude.
     *
     * @return la description de la fraude
     */
    public String getDescriptionTextuelle() {
        return descriptionTextuelle;
    }

    /**
     * Modifie la description textuelle de la fraude.
     *
     * @param descriptionTextuelle nouvelle description
     */
    public void setDescriptionTextuelle(String descriptionTextuelle) {
        this.descriptionTextuelle = descriptionTextuelle;
    }

    /**
     * Retourne le contenu associé à la fraude.
     *
     * @return le contenu de la fraude
     */
    public String getContenu() {
        return contenu;
    }

    /**
     * Modifie le contenu associé à la fraude.
     *
     * @param contenu nouveau contenu
     */
    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    /**
     * Vérifie si deux objets Fraude sont égaux en comparant leurs attributs.
     *
     * @param o objet à comparer
     * @return true si les objets sont égaux, sinon false
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Fraude)) {return false;}
        Fraude fraude = (Fraude) o;
        return Objects.equals(dateReleve, fraude.dateReleve) && Objects.equals(descriptionTextuelle,
                fraude.descriptionTextuelle) && Objects.equals(contenu, fraude.contenu);
    }

    /**
     * @return hashCode basé sur les attributs de la fraude
     */
    @Override
    public int hashCode() {
        return Objects.hash(dateReleve, descriptionTextuelle, contenu);
    }

    /**
     * @return  chaîne de caractères représentant la fraude
     */
    @Override
    public String toString() {
        return "Fraude{" +
                "dateReleve=" + dateReleve +
                ", descriptionTextuelle='" + descriptionTextuelle + '\'' +
                ", contenu='" + contenu + '\'' +
                '}';
    }
}
