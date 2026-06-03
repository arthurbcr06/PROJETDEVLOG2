package fr.eseo.e3e.projet2.formulaires.fraudes;

import java.time.LocalDate;

public class FraudeCalculatrice extends Fraude {
    private String marqueAppareil;
    private String programmeStocke;

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
}
