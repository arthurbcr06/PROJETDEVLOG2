package fr.eseo.e3e.projet2.formulaires;
import java.time.LocalDate;
import java.util.Objects;

public class Sanction {
    private TypeSanction type;
    private LocalDate dateDecision;
    private String commentaire;

    public Sanction(){
        this.dateDecision = LocalDate.now();
    }

    public Sanction(TypeSanction type, LocalDate dateDecision, String commentaire){
        this.type = type;
        this.dateDecision = dateDecision;
        this.commentaire = commentaire;
    }

    public TypeSanction getType() {
        return type;
    }

    public void setType(TypeSanction type) {
        this.type = type;
    }

    public LocalDate getDateDecision() {
        return dateDecision;
    }

    public void setDateDecision(LocalDate dateDecision) {
        this.dateDecision = dateDecision;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Sanction sanction)) {return false;}
        return type == sanction.type && Objects.equals(dateDecision, sanction.dateDecision) && Objects.equals(commentaire, sanction.commentaire);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, dateDecision, commentaire);
    }

    @Override
    public String toString() {
        return "Sanction{" +
                "type=" + type +
                ", dateDecision=" + dateDecision +
                ", commentaire='" + commentaire + '\'' +
                '}';
    }
}
