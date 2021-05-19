/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunlancer.Models;

/**
 *
 * @author asus
 */
public class Competence {
    private int id;
    private String Titre;
    private String Domaine;
    private int user_id;

    public Competence() {
    }
    
    public Competence(int id){
        this.id = id;
    }

    public Competence(int id, String Titre, String Domaine) {
       
        this.id = id;
        this.Titre = Titre;
        this.Domaine = Domaine;
        this.user_id= user_id;
        
    }

   
   public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public String getTitre() {
        return Titre;
    }

    public void setTitre(String Titre) {
        this.Titre = Titre;
    }

    public String getDomaine() {
        return Domaine;
    }

    public void setDomaine(String Domaine) {
        this.Domaine = Domaine;
    
 }
    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    
   public Competence(int id, String Domaine, String Titre,int user_id) {
        this.id = id;
        this.Titre = Titre;
        this.Domaine = Domaine;
        this.user_id= user_id;
    }
  

    @Override
    public String toString() {
        return "Competence{" + "id=" + id + ", Titre=" + Titre + ", Domaine=" + Domaine + ", user_id=" + user_id +  "}\n";
    }
}

 
