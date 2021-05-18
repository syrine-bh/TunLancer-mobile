/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunlancer.Models;

/**
 *
 * @author siwar
 */
public class postuler {
     private  int id;
     private String nom;
    private  String prenom;
     private String telephone;

  
     private String email;
     private String message;
     private String cv;
     // private  Annonce annonce_id;
    private  int annonce_id;
        public postuler() {
    }

    public postuler(int id, String nom, String prenom, String telephone, String email, String message, String cv, int annonce_id ) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.email = email;
        this.message = message;
        this.cv = cv;
        this.annonce_id = annonce_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }


    public int getAnnonce_id() {
        return annonce_id;
    }

    /* public Annonce getAnnonce_id() {
    return annonce_id;
    }
    public void setAnnonce_id(Annonce annonce_id) {
    this.annonce_id = annonce_id;
    }*/
    public void setAnnonce_id(int annonce_id) {
        this.annonce_id = annonce_id;
    }

    @Override
    public String toString() {
        return "postuler{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", telephone=" + telephone + ", email=" + email + ", message=" + message + ", cv=" + cv + ", annonce_id=" + annonce_id + '}';
    }
        
     
     
    
}
