package fr.eseo.e3e.projet2.formulaires;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Duration;
import java.util.Objects;

public class Epreuve {
    private String codeECUE;
    private LocalDate datePassage;
    private LocalTime heurePassage;
    private Duration dureePassage;
    private Modalite modalite;

    public Epreuve(){

    }

    public Epreuve(String codeECUE, LocalDate datePassage, LocalTime heurePassage, Duration dureePassage, Modalite modalite){
        this.codeECUE = codeECUE;
        this.datePassage = datePassage;
        this.heurePassage = heurePassage;
        this.dureePassage = dureePassage;
        this.modalite = modalite;
    }

    public String getCodeECUE() {
        return codeECUE;
    }

    public void setCodeECUE(String codeECUE) {
        this.codeECUE = codeECUE;
    }

    public LocalDate getDatePassage() {
        return datePassage;
    }

    public void setDatePassage(LocalDate datePassage) {
        this.datePassage = datePassage;
    }

    public LocalTime getHeurePassage() {
        return heurePassage;
    }

    public void setHeurePassage(LocalTime heurePassage) {
        this.heurePassage = heurePassage;
    }

    public Duration getDureePassage() {
        return dureePassage;
    }

    public void setDureePassage(Duration dureePassage) {
        this.dureePassage = dureePassage;
    }

    public Modalite getModalite() {
        return modalite;
    }

    public void setModalite(Modalite modalite) {
        this.modalite = modalite;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Epreuve epreuve)) {return false;}
        return Objects.equals(codeECUE, epreuve.codeECUE) && Objects.equals(datePassage, epreuve.datePassage) && Objects.equals(heurePassage, epreuve.heurePassage) && Objects.equals(dureePassage, epreuve.dureePassage) && modalite == epreuve.modalite;
    }

    @Override
    public int hashCode() {
        return Objects.hash(codeECUE, datePassage, heurePassage, dureePassage, modalite);
    }

    @Override
    public String toString() {
        return "Epreuve{" +
                "codeECUE='" + codeECUE + '\'' +
                ", datePassage=" + datePassage +
                ", heurePassage=" + heurePassage +
                ", dureePassage=" + dureePassage +
                ", modalite=" + modalite +
                '}';
    }
}
