package fr.eseo.e3e.projet2.formulaires.fraudes;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Représente une fraude réalisée avec une calculatrice programmable.
 *
 * Cette fraude précise le modèle de l'appareil et le programme stocké.
 */

public class FraudeCalculatrice extends Fraude {
    private String marqueAppareil;
    private String programmeStocke;

    // Constructeur par défaut
    public FraudeCalculatrice() {
        super();
    }

    /**
     * Construit une fraude calculatrice avec toutes ses informations.
     *
     * @param dateReleve date à laquelle la fraude a été relevée
     * @param descriptionTextuelle description de la fraude
     * @param contenu contenu associé à la fraude
     * @param marqueAppareil marque de la calculatrice
     * @param programmeStocke programme stocké dans la calculatrice
     */
    public FraudeCalculatrice(LocalDate dateReleve, String descriptionTextuelle, String contenu,
                              String marqueAppareil, String programmeStocke) {
        super(dateReleve, descriptionTextuelle, contenu);
        this.marqueAppareil = marqueAppareil;
        this.programmeStocke = programmeStocke;
    }

    /**
     * Retourne la marque de l'appareil utilisé.
     *
     * @return marque de la calculatrice
     */
    public String getMarqueAppareil() {
        return marqueAppareil;
    }

    /**
     * Modifie la marque de l'appareil utilisé.
     *
     * @param marqueAppareil nouvelle marque
     */
    public void setMarqueAppareil(String marqueAppareil) {
        this.marqueAppareil = marqueAppareil;
    }

    /**
     * Retourne le programme stocké dans la calculatrice.
     *
     * @return programme stocké
     */
    public String getProgrammeStocke() {
        return programmeStocke;
    }

    /**
     * Modifie le programme stocké dans la calculatrice.
     *
     * @param programmeStocke nouveau programme stocké
     */
    public void setProgrammeStocke(String programmeStocke) {
        this.programmeStocke = programmeStocke;
    }

    /**
     * Vérifie si deux objets FraudeCalculatrice sont égaux en comparant leurs attributs.
     *
     * @param o objet à comparer
     * @return true si les objets sont égaux, sinon false
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof FraudeCalculatrice that)) {return false;}
        if (!super.equals(o)) return false;
        return Objects.equals(marqueAppareil, that.marqueAppareil)
                && Objects.equals(programmeStocke, that.programmeStocke);
    }

    /**
     * @return hashCode basé sur les attributs de la fraude
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), marqueAppareil, programmeStocke);
    }

    /**
     * @return  chaîne de caractères représentant la fraude
     */
    @Override
    public String toString() {
        return "FraudeCalculatrice{" +
                "dateReleve=" + getDateReleve() +
                ", descriptionTextuelle='" + getDescriptionTextuelle() + '\'' +
                ", contenu='" + getContenu() + '\'' +
                ", marqueAppareil='" + marqueAppareil + '\'' +
                ", programmeStocke='" + programmeStocke + '\'' +
                '}';
    }

}
