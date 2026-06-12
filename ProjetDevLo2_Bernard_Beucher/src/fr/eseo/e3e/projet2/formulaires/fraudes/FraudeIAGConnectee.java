package fr.eseo.e3e.projet2.formulaires.fraudes;

import java.time.LocalDate;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof FraudeIAGConnectee that)) {return false;}
        if (!super.equals(o)) {return false;}
        return Objects.equals(adresseIP, that.adresseIP);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), adresseIP);
    }

    @Override
    public String toString() {
        return "FraudeIAGConnectee{" +
                "adresseIP='" + adresseIP + '\'' +
                '}';
    }
}
