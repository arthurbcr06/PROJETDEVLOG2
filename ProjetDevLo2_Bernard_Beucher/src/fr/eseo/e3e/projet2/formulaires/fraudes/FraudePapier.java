package fr.eseo.e3e.projet2.formulaires.fraudes;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Représente une fraude commise sur support papier.
 *
 * Cette fraude précise la dimension du document et s'il est plié ou non.
 */
public class FraudePapier extends Fraude {
    private String dimension;
    private boolean plieOuNon;

    // Constructeur par défaut
    public FraudePapier() {
        super();
    }

    /**
     * Construit une fraude sur support papier avec toutes ses informations.
     *
     * @param dateReleve date à laquelle la fraude a été relevée
     * @param descriptionTextuelle description de la fraude
     * @param contenu contenu associé à la fraude
     * @param dimension dimension du document papier
     * @param plieOuNon indique si le document est plié ou non
     */
    public FraudePapier(LocalDate dateReleve, String descriptionTextuelle, String contenu,
                        String dimension, boolean plieOuNon) {
        super(dateReleve, descriptionTextuelle, contenu);
        this.dimension = dimension;
        this.plieOuNon = plieOuNon;
    }

    /**
     * Retourne la dimension du document papier associé à cette fraude.
     *
     * @return la dimension du document
     */
    public String getDimension() {
        return dimension;
    }

    /**
     * Modifie la dimension du document papier associé à cette fraude.
     *
     * @param dimension nouvelle dimension du document
     */
    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    /**
     * Indique si le document papier est plié ou non.
     *
     * @return true si le document est plié, sinon false
     */
    public boolean isPlieOuNon() {
        return plieOuNon;
    }

    /**
     * Modifie l'état de pliage du document papier associé à cette fraude.
     *
     * @param plieOuNon true si le document est plié, sinon false
     */
    public void setPlieOuNon(boolean plieOuNon) {
        this.plieOuNon = plieOuNon;
    }

    /**
     * Vérifie si deux objets FraudePapier sont égaux en comparant leurs attributs.
     *
     * @param o objet à comparer
     * @return true si les objets sont égaux, sinon false
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof FraudePapier that)) {return false;}
        if (!super.equals(o)) {return false;}
        return plieOuNon == that.plieOuNon && Objects.equals(dimension, that.dimension);
    }

    /**
     * @return hashCode basé sur les attributs de la fraude
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), dimension, plieOuNon);
    }

    /**
     * @return  chaîne de caractères représentant la fraude
     */
    @Override
    public String toString() {
        return "FraudePapier{" +
                "dateReleve=" + getDateReleve() +
                ", descriptionTextuelle='" + getDescriptionTextuelle() + '\'' +
                ", contenu='" + getContenu() + '\'' +
                ", dimension='" + dimension + '\'' +
                ", plieOuNon=" + plieOuNon +
                '}';
    }
}
