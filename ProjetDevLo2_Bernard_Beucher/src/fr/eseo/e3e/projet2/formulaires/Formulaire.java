package fr.eseo.e3e.projet2.formulaires;
import fr.eseo.e3e.projet2.formulaires.fraudes.Fraude;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Formulaire {
    private static int compteur = 0;
    private int identifiantNumeriqueUnique;
    private LocalDate dateCreation;
    private LocalDate dateDerniereModification;
    private Epreuve epreuve;
    private Sanction sanction;
    private List<Etudiant> etudiants;
    private List<Fraude> fraudes;

    public Formulaire(){
        this.identifiantNumeriqueUnique = ++compteur;
        this.dateCreation = LocalDate.now();
        this.dateDerniereModification = LocalDate.now();
        this.etudiants = new ArrayList<>();
        this.fraudes = new ArrayList<>();
    }

    public Formulaire(Epreuve epreuve, List<Etudiant> etudiants, List<Fraude> fraudes, Sanction sanction) {
        this();
        this.epreuve = epreuve;
        if (etudiants != null) this.etudiants.addAll(etudiants);
        if (fraudes != null) this.fraudes.addAll(fraudes);
        this.sanction = sanction;
    }

    public void ajouterEtudiant(Etudiant e) {
        this.etudiants.add(e);
        this.dateDerniereModification = LocalDate.now();
    }

    public void ajouterFraude(Fraude f) {
        this.fraudes.add(f);
        this.dateDerniereModification = LocalDate.now();
    }

    public Sanction getSanction() {
        return sanction;
    }

    public List<Etudiant> getEtudiants() {
        return etudiants;
    }

    public List<Fraude> getFraudes() {
        return fraudes;
    }

    public Epreuve getEpreuve() {
        return epreuve;
    }

    public LocalDate getDateDerniereModification() {
        return dateDerniereModification;
    }

    public int getIdentifiantNumeriqueUnique() {
        return identifiantNumeriqueUnique;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public void setSanction(Sanction sanction) {
        this.sanction = sanction;
    }

    public void setEpreuve(Epreuve epreuve) {
        this.epreuve = epreuve;
        this.dateDerniereModification = LocalDate.now();
    }
}
