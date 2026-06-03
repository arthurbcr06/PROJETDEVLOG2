package fr.eseo.e3e.projet2.formulaires;

public class Etudiant {

    private String nom;
    private String prenom;
    private int numeroApprenant;

    public Etudiant(String nom, String prenom, int numeroApprenant) {
        this.nom = nom;
        this.prenom = prenom;
        this.numeroApprenant = numeroApprenant;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getNumeroApprenant() {
        return numeroApprenant;
    }

    public void setNumeroApprenant(int numeroApprenant) {
        this.numeroApprenant = numeroApprenant;
    }
}

