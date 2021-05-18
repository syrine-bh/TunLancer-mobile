/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunlancer.Models;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;

/**
 *
 * @author cyrinaa belguith
 */
public class Topics {
    
    private int id;
    private String titre;
    private String contenu;
    private String date;
    //private LinkedHashMap<Object,Object> user_id;
    ArrayList<Replies> replies;
    private int user_id;
    private int note;

    public Topics() {
    }

    public Topics(int id, String titre, String contenu, String date, ArrayList<Replies> replies, int user_id) {
        this.id = id;
        this.titre = titre;
        this.contenu = contenu;
        this.date = date;
        this.replies = replies;
        this.user_id = user_id;
    }

    public Topics(String titre, String contenu, int note) {
        this.titre = titre;
        this.contenu = contenu;
        this.note = note;
    }

    public Topics(int id, String titre, String contenu, int note) {
        this.id = id;
        this.titre = titre;
        this.contenu = contenu;
        this.note = note;
    }

    
    
    public Topics(int id, String titre, String contenu, String date, int user_id) {
        this.id = id;
        this.titre = titre;
        this.contenu = contenu;
        this.date = date;
        this.user_id = user_id;
    }

    public Topics(String titre, String contenu, String date, int user_id) {
        this.titre = titre;
        this.contenu = contenu;
        this.date = date;
        this.user_id = user_id;
    }

    public Topics(String titre, String contenu, String date) {
        this.titre = titre;
        this.contenu = contenu;
        this.date = date;
    }

    public Topics(String titre, String contenu) {
        this.titre = titre;
        this.contenu = contenu;
    }

    

    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public ArrayList<Replies> getReplies() {
        return replies;
    }

    public void setRep(ArrayList<Replies> replies) {
        this.replies = replies;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }
    
    
    
    

    @Override
    public String toString() {
        return "Topics{" + "id=" + id + ", titre=" + titre + ", contenu=" + contenu + ", date=" + date + ", user_id=" + user_id + '}';
    }

    

    
    
    
}
