/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunlancer.Models;

//import java.sql.Timestamp;

/**
 *
 * @author Hiba
 */
public class Score {
    

private int id;
private String pseudo;
private String score;
private Quiz quiz_id;
private Users user_id;
private String email;
private String image_profil;
//private Timestamp timestamp;  


    public Score() {
    }

    public Score(int id, String score) {
        this.id = id;
        this.score = score;
    }

    public Score(int id, String pseudo, String score) {
        this.id = id;
        this.pseudo = pseudo;
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public Quiz getQuiz_id() {
        return quiz_id;
    }

    public void setQuiz_id(Quiz quiz_id) {
        this.quiz_id = quiz_id;
    }

    public Users getUser_id() {
        return user_id;
    }

    public void setUser_id(Users user_id) {
        this.user_id = user_id;
    }

    public Score(String pseudo, String score, Quiz quiz_id, Users user_id) {
        this.pseudo = pseudo;
        this.score = score;
        this.quiz_id = quiz_id;
        this.user_id = user_id;
    }

    public Score(String pseudo, String score, Users user_id,String email, String image_profil) {
        this.pseudo = pseudo;
        this.score = score;
        this.user_id = user_id;
         this.email = email;
        this.image_profil = image_profil;

    }

    public Score(String pseudo, String score, Quiz quiz_id, Users user_id, String email, String image_profil) {
        this.pseudo = pseudo;
        this.score = score;
        this.quiz_id = quiz_id;
        this.user_id = user_id;
        this.email = email;
        this.image_profil = image_profil;
    }





public void save(){
    
}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage_profil() {
        return image_profil;
    }

    public void setImage_profil(String image_profil) {
        this.image_profil = image_profil;
    }



}
