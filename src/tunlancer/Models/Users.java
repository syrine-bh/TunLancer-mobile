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
public class Users {
    private int Id ;
    private String Nom;
    private String Prenom;
    private int Tel;
    private String Email;
    private String Password;
    private String Pays;
    private String Role;
    private String Photo;
    private String Bibliography;
    private int is_Enabled;
    private int super_admin;
    private Boolean Sexe;
    private int Age;


      
    public Users() {

    }
 
 
public Users(int Id, String Nom, String Prenom, int Tel ,String Password, String Email, String Pays,Boolean Sexe,String Role,int Age) {
        this.Id = Id;
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
     
    }

  /*  public Users(int i, String text, String text0, int parseInt, String text1, String text2, String text3, boolean selected, String text4, Integer valueOf) {
          this.Id = Id;
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.Tel = Tel;
        this.Email= Email;
        this.Password= Password;
        this.Pays =Pays;
        this.Role = Role;
       
        this.Sexe =Sexe;
        this.Age =Age;
        this.is_Enabled =is_Enabled;    }*/

    

    
        public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
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
       public String getPhoto() {
        return Photo;
    }

    public void setPhoto(String Photo) {
        this.Photo = Photo;
    } 
         public String getBibliography() {
        return Bibliography;
    }

    public void setBibliography(String Bibliography) {
        this.Bibliography= Bibliography;
    } 
    public Boolean getSexe() {
        return Sexe;
    }

    public void setSexe(Boolean Sexe) {
        this.Sexe = Sexe;
    }
 public int getAge() {
        return Age;
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
    public String toString() {
        return "Users{" + "Id=" + Id + ", Nom=" + Nom + ", Prenom=" + Prenom + ", Tel=" + Tel + ", Email=" + Email + ", is_Enabled=" + is_Enabled +", Password=" + Password + ", super_admin=" + super_admin + ", Pays=" + Pays + ", =" + Role + ", Role="+ Photo + ", Photo=" + Bibliography + ", Bibliography =" + Sexe + ", Sexe=" + Age + ", Age= "+'}';
    }

   
}
  