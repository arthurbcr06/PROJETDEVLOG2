package fr.eseo.e3e.projet2.formulaires.fraudes;
import java.time.LocalDate;
import java.util.Objects;

public class FraudePapier extends Fraude {
    private String dimension;
    private boolean plieOuNon;

    public FraudePapier() {
        super();
    }

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

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof FraudePapier that)) {return false;}
        if (!super.equals(o)) {return false;}
        return plieOuNon == that.plieOuNon && Objects.equals(dimension, that.dimension);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), dimension, plieOuNon);
    }

    @Override
    public String toString() {
        return "FraudePapier{" +
                "dimension='" + dimension + '\'' +
                ", plieOuNon=" + plieOuNon +
                '}';
    }
}
