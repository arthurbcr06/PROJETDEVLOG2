package fr.eseo.e3e.projet2.formulaires.fraudes;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Représente une fraude liée à une intelligence artificielle générative connectée.
 *
 * Cette fraude hérite de FraudeIAG et ajoute une adresse IP.
 */
public class FraudeIAGConnectee extends FraudeIAG {
    private String adresseIP;

    // Constructeur par défaut
    public FraudeIAGConnectee() {
        super();
    }

    /**
     * Construit une fraude IAG connectée avec toutes ses informations.
     *
     * @param dateReleve date à laquelle la fraude a été relevée
     * @param descriptionTextuelle description de la fraude
     * @param contenu contenu associé à la fraude
     * @param nomService nom du service d'intelligence artificielle générative utilisé
     * @param adresseIP adresse IP de l'utilisateur
     */
    public FraudeIAGConnectee(LocalDate dateReleve, String descriptionTextuelle, String contenu, String nomService, String adresseIP){
        super(dateReleve, descriptionTextuelle, contenu, nomService);
        this.adresseIP = adresseIP;
    }

    /**
     * Retourne l'adresse IP associée à cette fraude.
     *
     * @return l'adresse IP
     */
    public String getAdresseIP() {
        return adresseIP;
    }

    /**
     * Modifie l'adresse IP associée à cette fraude.
     *
     * @param adresseIP nouvelle adresse IP
     */
    public void setAdresseIP(String adresseIP) {
        this.adresseIP = adresseIP;
    }

    /**
     * Vérifie si deux objets FraudeIAGConnectee sont égaux en comparant leurs attributs.
     *
     * @param o objet à comparer
     * @return true si les objets sont égaux, sinon false
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof FraudeIAGConnectee that)) {return false;}
        if (!super.equals(o)) {return false;}
        return Objects.equals(adresseIP, that.adresseIP);
    }

    /**
     * @return hashCode basé sur les attributs de la fraude
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), adresseIP);
    }

    /**
     * @return  chaîne de caractères représentant la fraude
     */
    @Override
    public String toString() {
        return "FraudeIAGConnectee{" +
                "dateReleve=" + getDateReleve() +
                ", descriptionTextuelle='" + getDescriptionTextuelle() + '\'' +
                ", contenu='" + getContenu() + '\'' +
                ", nomService='" + getNomService() + '\'' +
                ", adresseIP='" + adresseIP + '\'' +
                '}';
    }
}
