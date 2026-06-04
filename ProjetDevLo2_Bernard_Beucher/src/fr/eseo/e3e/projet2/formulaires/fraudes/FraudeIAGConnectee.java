package fr.eseo.e3e.projet2.formulaires.fraudes;

import java.time.LocalDate;

public class FraudeIAGConnectee extends FraudeIAG {
    private String adresseIP;

    public FraudeIAGConnectee() {
        super();
    }

    public FraudeIAGConnectee(LocalDate dateReleve, String descriptionTextuelle, String contenu, String nomService, String adresseIP){
        super(dateReleve, descriptionTextuelle, contenu, nomService);
        this.adresseIP = adresseIP;
    }

    public String getAdresseIP() {
        return adresseIP;
    }

    public void setAdresseIP(String adresseIP) {
        this.adresseIP = adresseIP;
    }
}
