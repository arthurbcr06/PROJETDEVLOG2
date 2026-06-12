package fr.eseo.e3e.projet2.formulaires.fraudes;

import java.time.LocalDate;
import java.util.Objects;

public class FraudeCalculatrice extends Fraude {
    private String marqueAppareil;
    private String programmeStocke;

    public FraudeCalculatrice() {
        super();
    }
    public FraudeCalculatrice(LocalDate dateReleve, String descriptionTextuelle, String contenu, String marqueAppareil, String programmeStocke) {
        super(dateReleve, descriptionTextuelle, contenu);
        this.marqueAppareil = marqueAppareil;
        this.programmeStocke = programmeStocke;
    }

    public String getMarqueAppareil() {
        return marqueAppareil;
    }

    public void setMarqueAppareil(String marqueAppareil) {
        this.marqueAppareil = marqueAppareil;
    }

    public String getProgrammeStocke() {
        return programmeStocke;
    }

    public void setProgrammeStocke(String programmeStocke) {
        this.programmeStocke = programmeStocke;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof FraudeCalculatrice that)) {return false;}
        if (!super.equals(o)) return false;
        return Objects.equals(marqueAppareil, that.marqueAppareil) && Objects.equals(programmeStocke, that.programmeStocke);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), marqueAppareil, programmeStocke);
    }

    @Override
    public String toString() {
        return "FraudeCalculatrice{" +
                "marqueAppareil='" + marqueAppareil + '\'' +
                ", programmeStocke='" + programmeStocke + '\'' +
                '}';
    }
}
