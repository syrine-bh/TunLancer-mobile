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
public class Reaction {

    private int idReaction;
    private Publication publication;
    private int $typeReaction;
    private LinkedHashMap<Object, Object> idU;

    public Reaction() {
    }

    public Reaction(int idReaction, Publication publication, int $typeReaction, LinkedHashMap<Object, Object> idU) {
        this.idReaction = idReaction;
        this.publication = publication;
        this.$typeReaction = $typeReaction;
        this.idU = idU;
    }

    public void setIdReaction(int idReaction) {
        this.idReaction = idReaction;
    }

    public void setPublication(Publication publication) {
        this.publication = publication;
    }

    public void set$typeReaction(int $typeReaction) {
        this.$typeReaction = $typeReaction;
    }

    public void setIdU(LinkedHashMap<Object, Object> idU) {
        this.idU = idU;
    }

    @Override
    public String toString() {
        return "Reaction{" + "idReaction=" + idReaction + ", publication=" + publication + ", $typeReaction=" + $typeReaction + ", idU=" + idU + '}';
    }
    

}
