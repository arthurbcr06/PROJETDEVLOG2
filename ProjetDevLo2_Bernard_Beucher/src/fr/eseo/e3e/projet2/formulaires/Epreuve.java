package fr.eseo.e3e.projet2.formulaires;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Duration;

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
}
