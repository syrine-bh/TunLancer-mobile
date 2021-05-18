/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunlancer.Models;

import java.util.Map;

/**
 *
 * @author Hiba
 */
public class reponsetab {
    private String reponses;
    private boolean statu;
    private int id;
     private String label ; 
    private java.lang.Boolean flag , enabled; 
    

    public reponsetab(String reponses, boolean statu) {
        this.reponses = reponses;
        this.statu = statu;
    }

    public reponsetab() {
    }

   

    public String getReponses() {
        return reponses;
    }

    public void setReponses(String reponses) {
        this.reponses = reponses;
    }

    public boolean isStatu() {
        return statu;
    }

    public void setStatu(boolean statu) {
        this.statu = statu;
    }

    public String toString(char letter){
        return letter+":"+ reponses;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }






  public static reponsetab createanswer(Map<String,Object> mapanswer)
    {
        reponsetab a=new reponsetab();
        a.setLabel(mapanswer.get("label").toString());
        String flag=mapanswer.get("flag").toString();
        if(flag=="true")
        a.setFlag(true);
        else
        a.setFlag(false);
        return a;
    }

    @Override
    public String toString() {
        return "reponsetab{" + "reponses=" + reponses + ", statu=" + statu + ", id=" + id + ", label=" + label + ", flag=" + flag + ", enabled=" + enabled + '}';
    }










}
