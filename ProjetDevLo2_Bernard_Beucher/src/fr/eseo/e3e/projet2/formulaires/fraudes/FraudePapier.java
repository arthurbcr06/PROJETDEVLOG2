package fr.eseo.e3e.projet2.formulaires.fraudes;
import java.time.LocalDate;
public class FraudePapier extends Fraude {
    private String dimension;
    private boolean plieOuNon;


    public FraudePapier(LocalDate dateReleve, String descriptionTextuelle, String contenu, String dimension, boolean plieOuNon) {
        super(dateReleve, descriptionTextuelle, contenu);
        this.dimension = dimension;
        this.plieOuNon = plieOuNon;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public boolean isPlieOuNon() {
        return plieOuNon;
    }

    public void setPlieOuNon(boolean plieOuNon) {
        this.plieOuNon = plieOuNon;
    }
}
