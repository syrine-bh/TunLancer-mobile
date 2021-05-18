package tunlancer.Models ;
//import java.sql.* ;



public class Quiz {

    private int id ;
    private Concour concour_id ;
    private String nom ;
    private int nb_questions ;
    private String couleur ;

    public Quiz(int id, Concour concour_id, String nom, int nb_questions, String couleur) {
        this.id = id;
        this.concour_id = concour_id;
        this.nom = nom;
        this.nb_questions = nb_questions;
        this.couleur = couleur;
    }

    public Quiz(int id, Concour concour_id, String nom, int nb_questions) {
        this.id = id;
        this.concour_id = concour_id;
        this.nom = nom;
        this.nb_questions = nb_questions;
       
    }

       public Quiz(Concour concour_id) {
     
        this.concour_id = concour_id;
        
       
    }

    public Quiz() {
    }

    public Quiz(Quiz q) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
  

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Concour getConcour_id() {
        return concour_id;
    }

    public void setConcour_id(Concour concour_id) {
        this.concour_id = concour_id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNb_questions() {
        return nb_questions;
    }

    public void setNb_questions(int nb_questions) {
        this.nb_questions = nb_questions;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }
    
//    public static createTable(){
//        
//    }

    @Override
    public String toString() {
        return "Quiz{" + "id=" + id + ", concour_id=" + concour_id + ", nom=" + nom + ", nb_questions=" + nb_questions + ", couleur=" + couleur + '}';
    }
    
    
}