/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunlancer.Models;

import java.util.Date;

/**
 *
 * @author Hiba
 */
public class Concour {

    int id;
    String nom;
    String categorie;
    String sujet;
    String description;
    Date dateDebut;
   Date dateFin;
    String imageName;
    String couleur;
    Boolean isVideo;
    Date created_at;
      


   

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

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public Boolean getIsVideo() {
        return isVideo;
    }

    public void setIsVideo(Boolean isVideo) {
        this.isVideo = isVideo;
    }
//       public ImageView getImg() {
//        return img;
//    }
//
//    public void setImg(ImageView img) {
//        this.img = img;
//    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

 

    public Concour() {
    }

    public Concour(int id,String description, String nom, Boolean isVideo) {
        this.id = id;
        this.description=description;
        this.nom = nom;
        this.isVideo = isVideo;
    }

    public Concour(int id, String nom, Date dateDebut, Date dateFin) {
        this.id = id;
        this.nom = nom;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    public Concour(String nom, String categorie, String sujet, String description, Date dateDebut, Date dateFin, String imageName, String couleur, Boolean isVideo) {
        this.nom = nom;
        this.categorie = categorie;
        this.sujet = sujet;
        this.description = description;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.imageName = imageName;
        this.couleur = couleur;
        this.isVideo = isVideo;
    }

    public Concour(int id, String nom, String categorie, String sujet, Date dateDebut, Date dateFin) {
        this.id = id;
        this.nom = nom;
        this.categorie = categorie;
        this.sujet = sujet;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }
    

    @Override
    public String toString() {
        return "Concour{" + "id=" + id + ", nom=" + nom + ", categorie=" + categorie + ", sujet=" + sujet + ", description=" + description + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", imageName=" + imageName + ", couleur=" + couleur + ", isVideo=" + isVideo + '}';
    }
    
    
    
    

}
