package fr.eseo.e3e.projet2.formulaires;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Duration;
import java.util.Objects;

/**
 * Représente une épreuve d'un examen
 *
 * Une épreuve est caractérisée par un code ECUE, une date de passage,
 * une heure de passage, une durée de passage et une modalité d'évaluation.
 */
public class Epreuve {
    private String codeECUE;
    private LocalDate datePassage;
    private LocalTime heurePassage;
    private Duration dureePassage;
    private Modalite modalite;

    // Constructeur par défaut
    public Epreuve(){}

    /**
     * Construit une épreuve avec toutes ses informations.
     *
     * @param codeECUE code de l'ECUE associé à l'épreuve
     * @param datePassage date à laquelle l'épreuve a lieu
     * @param heurePassage heure à laquelle l'épreuve commence
     * @param dureePassage durée de l'épreuve
     * @param modalite modalité d'évaluation de l'épreuve
     */
    public Epreuve(String codeECUE, LocalDate datePassage, LocalTime heurePassage,
                   Duration dureePassage, Modalite modalite){
        this.codeECUE = codeECUE;
        this.datePassage = datePassage;
        this.heurePassage = heurePassage;
        this.dureePassage = dureePassage;
        this.modalite = modalite;
    }

    /**
     * Retourne le code ECUE associé à l'épreuve.
     *
     * @return le code ECUE
     */
    public String getCodeECUE() {
        return codeECUE;
    }

    /**
     * Modifie le code ECUE associé à l'épreuve.
     *
     * @param codeECUE le nouveau code ECUE
     */
    public void setCodeECUE(String codeECUE) {
        this.codeECUE = codeECUE;
    }

    /**
     * Retourne la date à laquelle l'épreuve a lieu.
     *
     * @return la date de passage
     */
    public LocalDate getDatePassage() {
        return datePassage;
    }

    /**
     * Modifie la date à laquelle l'épreuve a lieu.
     *
     * @param datePassage la nouvelle date de passage
     */
    public void setDatePassage(LocalDate datePassage) {
        this.datePassage = datePassage;
    }

    /**
     * Retourne l'heure à laquelle l'épreuve commence.
     *
     * @return l'heure de passage
     */
    public LocalTime getHeurePassage() {
        return heurePassage;
    }

    /**
     * Modifie l'heure à laquelle l'épreuve commence.
     *
     * @param heurePassage la nouvelle heure de passage
     */
    public void setHeurePassage(LocalTime heurePassage) {
        this.heurePassage = heurePassage;
    }

    /**
     * Retourne la durée de l'épreuve.
     *
     * @return la durée de passage
     */
    public Duration getDureePassage() {
        return dureePassage;
    }

    /**
     * Modifie la durée de l'épreuve.
     *
     * @param dureePassage la nouvelle durée de passage
     */
    public void setDureePassage(Duration dureePassage) {
        this.dureePassage = dureePassage;
    }

    /**
     * Retourne la modalité d'évaluation de l'épreuve.
     *
     * @return la modalité
     */
    public Modalite getModalite() {
        return modalite;
    }

    /**
     * Modifie la modalité d'évaluation de l'épreuve.
     *
     * @param modalite la nouvelle modalité
     */
    public void setModalite(Modalite modalite) {
        this.modalite = modalite;
    }

    /**
     * Vérifie si deux objets Epreuve sont égaux en comparant leurs attributs.
     *
     * @param o objet à comparer
     * @return true si les objets sont égaux, sinon false
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Epreuve epreuve)) {return false;}
        return Objects.equals(codeECUE, epreuve.codeECUE) && Objects.equals(datePassage, epreuve.datePassage)
                && Objects.equals(heurePassage, epreuve.heurePassage)
                && Objects.equals(dureePassage, epreuve.dureePassage)
                && modalite == epreuve.modalite;
    }

    /**
     * @return hashCode basé sur les attributs de l'épreuve
     */
    @Override
    public int hashCode() {
        return Objects.hash(codeECUE, datePassage, heurePassage, dureePassage, modalite);
    }

    /**
     * @return chaîne de caractères représentant l'épreuve
     */
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
