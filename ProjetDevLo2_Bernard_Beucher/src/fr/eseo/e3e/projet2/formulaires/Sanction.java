package fr.eseo.e3e.projet2.formulaires;
import java.time.LocalDate;

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
}
