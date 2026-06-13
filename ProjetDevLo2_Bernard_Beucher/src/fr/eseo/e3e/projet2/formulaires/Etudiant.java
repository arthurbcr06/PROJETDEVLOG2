package fr.eseo.e3e.projet2.formulaires;

/**
 * Représente un étudiant identifié par son numéro apprenant.
 *
 * Un étudiant est caractérisé par son nom, prénom, numéro apprenant unique et cursus.
 */
public class Etudiant {

    private String nom;
    private String prenom;
    private int numeroApprenant;
    private Cursus cursus;

    // Constructeur par défaut
    public Etudiant(){}

    /**
     * Construit un étudiant avec toutes ses informations.
     *
     * @param nom le nom de l'étudiant
     * @param prenom le prénom de l'étudiant
     * @param numeroApprenant le numéro apprenant unique de l'étudiant
     * @param cursus le cursus de l'étudiant
     */
    public Etudiant(String nom, String prenom, int numeroApprenant, Cursus cursus) {
        this.nom = nom;
        this.prenom = prenom;
        this.numeroApprenant = numeroApprenant;
        this.cursus = cursus;
    }

    /**
     * Retourne le nom de l'étudiant.
     *
     * @return le nom de l'étudiant
     */
    public String getNom() {
        return nom;
    }

    /**
     * Définit le nom de l'étudiant.
     *
     * @param nom le nom de l'étudiant
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Retourne le prénom de l'étudiant.
     *
     * @return le prénom de l'étudiant
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Définit le prénom de l'étudiant.
     *
     * @param prenom le prénom de l'étudiant
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * Retourne le numéro apprenant unique de l'étudiant.
     *
     * @return le numéro apprenant unique de l'étudiant
     */
    public int getNumeroApprenant() {
        return numeroApprenant;
    }

    /**
     * Définit le numéro apprenant unique de l'étudiant.
     *
     * @param numeroApprenant le numéro apprenant unique de l'étudiant
     */
    public void setNumeroApprenant(int numeroApprenant) {
        this.numeroApprenant = numeroApprenant;
    }

    /**
     * Retourne le cursus de l'étudiant.
     *
     * @return le cursus de l'étudiant
     */
    public Cursus getCursus() {
        return cursus;
    }

    /**
     * Définit le cursus de l'étudiant.
     *
     * @param cursus le cursus de l'étudiant
     */
    public void setCursus(Cursus cursus) {
        this.cursus = cursus;
    }

    /**
     * Vérifie si deux étudiants sont égaux en comparant leur numéro apprenant.
     *
     * @param o l'objet à comparer
     * @return true si les deux étudiants ont le même numéro apprenant, false sinon
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (!(o instanceof Etudiant)) {return false;}
        Etudiant e = (Etudiant) o;
        return this.numeroApprenant == e.numeroApprenant;
    }

    /**
     * @return hashCode basé sur le numéro apprenant de l'étudiant
     */
    @Override
    public int hashCode() {
        return Integer.hashCode(numeroApprenant);
    }

    /**
     * @return chaîne de caractères représentant l'étudiant
     */
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

