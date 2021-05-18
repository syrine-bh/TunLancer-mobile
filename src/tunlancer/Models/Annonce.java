/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunlancer.Models;

import java.util.Date;

/**
 *
 * @author siwar
 */
public class Annonce {
      private  int id;
     private String nom;
    private  String description;
     private Date date;
     private String lieux;
     private String renumeration;

    public int getCategorie_id() {
        return categorie_id;
    }

    public void setCategorie_id(int categorie_id) {
        this.categorie_id = categorie_id;
    }
      private int categorie_id;
     
      public Annonce() {
    }

    public Annonce(int id, String nom, String description, Date date, String lieux, String renumeration, int categorie_id) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.date = date;
        this.lieux = lieux;
        this.renumeration = renumeration;
        this.categorie_id = categorie_id;
    }

   

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }

    public Date getDate() {
        return date;
    }

    public String getLieux() {
        return lieux;
    }

    public String getRenumeration() {
        return renumeration;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setLieux(String lieux) {
        this.lieux = lieux;
    }

    public void setRenumeration(String renumeration) {
        this.renumeration = renumeration;
    }

    @Override
    public String toString() {
        return "Annonce{" + "id=" + id + ", nom=" + nom + ", description=" + description + ", date=" + date + ", lieux=" + lieux + ", renumeration=" + renumeration + '}';
    }
      
    
}
