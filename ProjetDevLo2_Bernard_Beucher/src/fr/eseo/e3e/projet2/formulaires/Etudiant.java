package fr.eseo.e3e.projet2.formulaires;

public class Etudiant {

    private String nom;
    private String prenom;
    private int numeroApprenant;
    private Cursus cursus;

    public Etudiant(){
    }

    public Etudiant(String nom, String prenom, int numeroApprenant, Cursus cursus) {
        this.nom = nom;
        this.prenom = prenom;
        this.numeroApprenant = numeroApprenant;
        this.cursus = cursus;
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

    public Cursus getCursus() {
        return cursus;
    }

    public void setCursus(Cursus cursus) {
        this.cursus = cursus;
    }

    public void setNumeroApprenant(int numeroApprenant) {
        this.numeroApprenant = numeroApprenant;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (!(o instanceof Etudiant)) {return false;}
        Etudiant e = (Etudiant) o;
        return this.numeroApprenant == e.numeroApprenant;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(numeroApprenant);
    }

    @Override
    public String toString() {
        return "Etudiant{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", numeroApprenant=" + numeroApprenant +
                ", cursus=" + cursus +
                '}';
    }
}

