/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunlancer.Models;

import java.util.LinkedHashMap;

/**
 *
 * @author cyrinaa belguith
 */
public class Replies {
    
    private int id;
    private String contenu;
    private String created_at;
    private int topic_id;
    private int user_id;

    public Replies() {
    }

    public Replies(String contenu) {
        this.contenu = contenu;
    }

    
    public Replies(int id, String contenu, String created_at, int topic_id, int user_id) {
        this.id = id;
        this.contenu = contenu;
        this.created_at = created_at;
        this.topic_id = topic_id;
        this.user_id = user_id;
    }

    public Replies(int id, String contenu) {
        this.id = id;
        this.contenu = contenu;
    }

    public Replies(String contenu, int id) {
        this.contenu = contenu;
        this.id = id;

    }

    

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public int getTopic_id() {
        return topic_id;
    }

    public void setTopic_id(int topic_id) {
        this.topic_id = topic_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "Replies{" + "id=" + id + ", contenu=" + contenu + ", created_at=" + created_at + ", topic_id=" + topic_id + ", user_id=" + user_id + '}';
    }
    
    


    
}
