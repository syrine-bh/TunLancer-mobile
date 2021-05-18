/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunlancer.Services;

import tunlancer.Models.Concour;
import java.util.List;

/**
 *
 * @author Hiba
 */
public interface Iconcours<T> {
    
     public void ajouter(T t);
     public void supprimer(T t);
     public void update(T t);
     public List<T> display();
      public List<Concour> ListConcours();
   
    public Concour getConcours(int idC);
//    public int getCountTask();
    public List<Concour> searchConcourByName(String nom);
}
