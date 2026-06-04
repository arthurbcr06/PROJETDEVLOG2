package fr.eseo.e3e.projet2.formulaires.fraudes;

import java.time.LocalDate;

public class FraudeIAG extends Fraude {
    private String nomService;

    public FraudeIAG() {
        super();
    }

    public FraudeIAG(LocalDate dateReleve, String descriptionTextuelle, String contenu, String nomService){
        super(dateReleve, descriptionTextuelle, contenu);
        this.nomService = nomService;
    }

    public String getNomService() {
        return nomService;
    }



    public void setNomService(String nomService) {
        this.nomService = nomService;
    }
}
