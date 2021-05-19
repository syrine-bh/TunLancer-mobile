/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunlancer.Models;

import java.util.LinkedHashMap;

/**
 *
 * @author Cyrina
 */
public class Commentaire {
     private int idCommentaire;
     private Publication publication;
      private LinkedHashMap<Object, Object> idU;
      private String contenuCommentaire;

    public Commentaire() {
    }

    public Commentaire(int idCommentaire, Publication publication, LinkedHashMap<Object, Object> idU, String contenuCommentaire) {
        this.idCommentaire = idCommentaire;
        this.publication = publication;
        this.idU = idU;
        this.contenuCommentaire = contenuCommentaire;
    }

    public void setIdCommentaire(int idCommentaire) {
        this.idCommentaire = idCommentaire;
    }

    public void setPublication(Publication publication) {
        this.publication = publication;
    }

    public void setIdU(LinkedHashMap<Object, Object> idU) {
        this.idU = idU;
    }

    public void setContenuCommentaire(String contenuCommentaire) {
        this.contenuCommentaire = contenuCommentaire;
    }

    @Override
    public String toString() {
        return "Commentaire{" + "idCommentaire=" + idCommentaire + ", publication=" + publication + ", idU=" + idU + ", contenuCommentaire=" + contenuCommentaire + '}';
    }
      
}
