/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunlancer.Models;

import java.util.Objects;

/**
 *
 * @author asus
 */

   


public class Users {
     int id;

    

    public int getId() {
        return id;
    }
    private String Nom;
    private String Prenom;
    private int Tel;
    private String Email;
    private String Password;
    private String Pays;
    private String Role;
    private int is_Enabled;
    private int super_admin;
    private String Sexe;
    private int Age;
private String photo;
      
    public Users() {

    }
 
     public Users(int id, String Nom, String Prenom, int Tel ,String Password, String Email, String Pays,String Role,String Sexe,int Age,int is_Enabled,int super_admin) {
        this.id = id;
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.Tel = Tel;
        this.Email= Email;
        this.Password= Password;
        this.Pays =Pays;
        this.Role = Role;
        this.Sexe =Sexe;
        this.Age =Age;
        this.is_Enabled =is_Enabled;
        this.super_admin=super_admin;
    }
//
//    public Users(int aInt, String string, String string0, String string1, String string2, int aInt0, String string3, int aInt1, int aInt2, String string4, int aInt3) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    /**
     *
     * @param id
     * @param Nom
     * @param Role
     * @param Prenom
     * @param Password
     * @param Tel
     * @param Pays
     * @param is_Enabled
     * @param Age
     * @param Sexe
     * @param super_admin
     */
    public Users(int id, String Nom, String Role, String Prenom, String Password, int Tel, String Pays, int is_Enabled,int Age, String Sexe, int super_admin,String Email) {
        this.id = id;
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.Tel = Tel;
        this.Password = Password;
        this.Pays = Pays;
        this.Role = Role;
        this.is_Enabled = is_Enabled;
        this.super_admin = super_admin;
        this.Sexe = Sexe;
        this.Age = Age;
        this.Email=Email;
    }

  
 
       

    public void setId(int id) {
        this.id = id;
    }
   public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }
   public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String Prenom) {
        this.Prenom = Prenom;
    }
   public int getTel() {
        return Tel;
    }

    public void setTel(int Tel) {
        this.Tel = Tel;
    }
   public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }
   public String getPassword() {
        return Password;
    }

    public void setpassword(String Password) {
        this.Password= Password;
    }
     
     public int getis_Enabled() {
        return is_Enabled;
    }

    public void setis_Enabled(int is_Enabled) {
        this.is_Enabled = is_Enabled;
    }

   public String getPays() {
        return Pays;
    }

    public void setPays(String Pays) {
        this.Pays = Pays;
    }
  public String getRole() {
        return Role;
    }

    public void setRole(String Role) {
        this.Role = Role;
    }
       
    public String getSexe() {
        return Sexe;
    }

    public void setSexe(String Sexe) {
        this.Sexe = Sexe;
    }
 public int getAge() {
        return Age;
    }

    public int getIs_Enabled() {
        return is_Enabled;
    }

    public void setIs_Enabled(int is_Enabled) {
        this.is_Enabled = is_Enabled;
    }

    public int getSuper_admin() {
        return super_admin;
    }

    public void setSuper_admin(int super_admin) {
        this.super_admin = super_admin;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }


    public void setAge(int Age) {
        this.Age = Age;
    }
     public int getsuper_admin() {
        return super_admin;
    }

    public void setsuper_admin(int super_admin) {
        this.super_admin = super_admin;
    }
    
  @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Users other = (Users) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.is_Enabled != other.is_Enabled) {
            return false;
        }
        if (this.Nom != other.Nom) {
            return false;
        }
        if (!Objects.equals(this.Prenom, other.Prenom)) {
            return false;
        }
        if (!Objects.equals(this.Tel, other.Tel)) {
            return false;
        }
        if (!Objects.equals(this.Email, other.Email)) {
            return false;
        }
        if (!Objects.equals(this.Password, other.Password)) {
            return false;
        }
        if (!Objects.equals(this.super_admin, other.super_admin)) {
            return false;
        }
        if (!Objects.equals(this.Pays, other.Pays)) {
            return false;
        }
        if (!Objects.equals(this.Role, other.Role)) {
            return false;
        }
        if (!Objects.equals(this.Sexe, other.Sexe)) {
            return false;
        }
        if (!Objects.equals(this.Age, other.Age)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.id;
        hash = 71 * hash + Objects.hashCode(this.Nom);
        hash = 71 * hash + Objects.hashCode(this.Prenom);
        hash = 71 * hash + this.Tel;
        hash = 71 * hash + Objects.hashCode(this.Email);
        hash = 71 * hash + Objects.hashCode(this.Password);
        hash = 71 * hash + Objects.hashCode(this.super_admin);
        hash = 71 * hash + this.is_Enabled;
        hash = 71 * hash + Objects.hashCode(this.Pays);
        hash = 71 * hash + Objects.hashCode(this.Role);
        hash = 71 * hash + Objects.hashCode(this.Sexe);
        hash = 71 * hash + this.Age;
        return hash;
    }


 @Override
    public String toString() {
        return "Users{" + "Id=" + id + ", Nom=" + Nom + ", Prenom=" + Prenom + ", Tel=" + Tel + ", Email=" + Email + ", is_Enabled=" + is_Enabled +", Password=" + Password + ", super_admin=" + super_admin + ", Pays=" + Pays + ", =" + Role + ", Role=" + Sexe + ", Sexe=" + Age + ", Age= "+'}';
    }

    public String Nom() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
  

  
