/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunlancer.Models;

import java.util.LinkedHashMap;
import java.util.List;
//import javafx.scene.image.ImageView;

/**
 *
 * @author Cyrina
 */
public class Publication {

    private int id;
    private LinkedHashMap<Object,Object> idU;
    private String description;
    private int type;
    private int archive;

    private String localisation;
    private String image_name;

    public Publication() {
    }

    public Publication(int id, LinkedHashMap<Object, Object> idU, String description, int type, int archive, String localisation, String image_name) {
        this.id = id;
        this.idU = idU;
        this.description = description;
        this.type = type;
        this.archive = archive;
        this.localisation = localisation;
        this.image_name = image_name;
    }

    


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LinkedHashMap<Object, Object> getIdU() {
        return idU;
    }

    public void setIdU(LinkedHashMap<Object, Object> idU) {
        this.idU = idU;
    }



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getArchive() {
        return archive;
    }

    public void setArchive(int archive) {
        this.archive = archive;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public String getImage_name() {
        return image_name;
    }

    public void setImage_name(String image_name) {
        this.image_name = image_name;
    }

    @Override
    public String toString() {
        return "Publication{" + "id=" + id +", description=" + description + ", type=" + type + ", archive=" + archive + ", localisation=" + localisation + ", image_name=" + image_name + '}';
    }
    
    
}
