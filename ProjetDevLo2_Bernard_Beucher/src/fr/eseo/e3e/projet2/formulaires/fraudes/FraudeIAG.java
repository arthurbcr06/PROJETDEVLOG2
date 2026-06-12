package fr.eseo.e3e.projet2.formulaires.fraudes;

import java.time.LocalDate;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof FraudeIAG fraudeIAG)) {return false;}
        if (!super.equals(o)) {return false;}
        return Objects.equals(nomService, fraudeIAG.nomService);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), nomService);
    }

    @Override
    public String toString() {
        return "FraudeIAG{" +
                "nomService='" + nomService + '\'' +
                '}';
    }
}
