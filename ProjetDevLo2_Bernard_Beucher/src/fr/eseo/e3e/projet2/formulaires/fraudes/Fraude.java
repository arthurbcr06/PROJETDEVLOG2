package fr.eseo.e3e.projet2.formulaires.fraudes;
import java.time.LocalDate;

public abstract class Fraude {
    private LocalDate dateReleve;
    private String descriptionTextuelle;
    private String contenu;

    public Fraude() {}

    public Fraude(LocalDate dateReleve, String descriptionTextuelle, String contenu) {
        this.dateReleve = dateReleve;
        this.descriptionTextuelle = descriptionTextuelle;
        this.contenu = contenu;
    }

    public LocalDate getDateReleve() {
        return dateReleve;
    }

    public void setDateReleve(LocalDate dateReleve) {
        this.dateReleve = dateReleve;
    }

    public String getDescriptionTextuelle() {
        return descriptionTextuelle;
    }

    public void setDescriptionTextuelle(String descriptionTextuelle) {
        this.descriptionTextuelle = descriptionTextuelle;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }
}
