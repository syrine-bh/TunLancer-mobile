/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunlancer.Models;

import java.util.Date;

public class Experiences {
    private int id;
    private String societe;
    private String poste;
    private String datedeb;
    private String date_fin;
    private int user_id;

    public Experiences() {
    }
    
    public Experiences(int id){
        this.id = id;
    }

    public Experiences(int user_id,int id, String poste, String societe, String datedeb, String date_fin) {
        this.user_id=user_id;
        this.id = id;
         this.poste = poste;
        this.societe = societe;
        this.datedeb = datedeb;
        this.date_fin = date_fin;
       
       
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
   public Experiences(int id,String poste ,String societe, String datedeb, String date_fin) {
        this.id = id;
        this.societe = societe;
        this.datedeb = datedeb;
        this.date_fin = date_fin;
        this.poste = poste;
    }
     public Experiences( String societe, String poste, String date_fin,String datedeb) {
        this.poste = poste;
        this.societe = societe;
        this.datedeb = datedeb;
        this.date_fin = date_fin;
        
    }
   

    @Override
    public String toString() {
        return "Experiences{" + "id=" + id + ",  poste=" + poste +", societe=" + societe + ", datedeb=" + datedeb +  ",date_fin=" + date_fin+  "}\n";
    }
    

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }
  public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }
    public String getSociete() {
        return societe;
    }

    public void setSociete(String Societe) {
        this.societe = Societe;
    }

    public String getDatefin() {
        return date_fin;
    }

    public void setDatefin(String Date_fin) {
        this.date_fin = Date_fin;
    }

  

    public String getDatedeb() {
        return datedeb;
    }

    public void setDatedeb(String datedeb) {
        this.datedeb = datedeb;
    }

    public void setDateDeb(Date date1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
}
