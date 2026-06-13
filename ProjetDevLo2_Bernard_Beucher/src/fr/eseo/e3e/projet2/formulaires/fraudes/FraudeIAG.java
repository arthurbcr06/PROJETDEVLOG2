package fr.eseo.e3e.projet2.formulaires.fraudes;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Représente une fraude liée à l'utilisation d'une intelligence artificielle générative.
 *
 * La fraude est caractérisée par le nom du service utilisé.
 */
public class FraudeIAG extends Fraude {
    private String nomService;

    // Constructeur par défaut
    public FraudeIAG() {
        super();
    }

    /**
     * Construit une fraude IAG avec toutes ses informations.
     *
     * @param dateReleve date à laquelle la fraude a été relevée
     * @param descriptionTextuelle description de la fraude
     * @param contenu contenu associé à la fraude
     * @param nomService nom du service d'intelligence artificielle générative utilisé
     */
    public FraudeIAG(LocalDate dateReleve, String descriptionTextuelle, String contenu, String nomService){
        super(dateReleve, descriptionTextuelle, contenu);
        this.nomService = nomService;
    }

    /**
     * Retourne le nom du service d'intelligence artificielle générative utilisé pour cette fraude.
     *
     * @return le nom du service
     */
    public String getNomService() {
        return nomService;
    }

    /**
     * Modifie le nom du service d'intelligence artificielle générative utilisé pour cette fraude.
     *
     * @param nomService le nouveau nom du service
     */
    public void setNomService(String nomService) {
        this.nomService = nomService;
    }

    /**
     * Vérifie si deux objets FraudeIAGConnectee sont égaux en comparant leurs attributs.
     *
     * @param o objet à comparer
     * @return true si les objets sont égaux, sinon false
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof FraudeIAG fraudeIAG)) {return false;}
        if (!super.equals(o)) {return false;}
        return Objects.equals(nomService, fraudeIAG.nomService);
    }

    /**
     * @return hashCode basé sur les attributs de la fraude
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), nomService);
    }

    /**
     * @return  chaîne de caractères représentant la fraude
     */
    @Override
    public String toString() {
        return "FraudeIAG{" +
                "dateReleve=" + getDateReleve() +
                ", descriptionTextuelle='" + getDescriptionTextuelle() + '\'' +
                ", contenu='" + getContenu() + '\'' +
                ", nomService='" + nomService + '\'' +
                '}';
    }
}
