/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunlancer.Services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import tunlancer.Models.Categorie;
import tunlancer.Models.Publication;
import tunlancer.utils.Statics;

/**
 *
 * @author siwar
 */
public class ServiceCategorie {
    public static ServiceCategorie instance = null;
     public ArrayList<Categorie> tasks;
    
   
    public boolean resultOK;
    private ConnectionRequest req;

    private  ServiceCategorie() {
         req = new ConnectionRequest();
    }

    public static  ServiceCategorie getInstance() {
        if (instance == null) {
            instance = new ServiceCategorie() ;
        }
        return instance;
    }
     public void AjouterCategorie(Categorie cat){
          String url = Statics.BASE_URL + "/addcatjson?type="+cat.getType(); //création de l'URL
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener((e)-> {
            String str= new String(req.getResponseData());
            System.out.println("data ==" +str);
           
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
       
     }
     
      public ArrayList<Categorie> getAllcategorie(){
           ArrayList<Categorie> result =new ArrayList<>();
      
        String url = Statics.BASE_URL+"/GetAllcat";
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
               @Override
               public void actionPerformed(NetworkEvent evt) {
                  JSONParser json = new JSONParser();
                   try {
          
            Map<String, Object> categorielistJson = json.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
            
            List<Map<String, Object>> list = (List<Map<String, Object>>) categorielistJson .get("root");
            //parsing json in individual publications 
            for (Map<String, Object> obj : list) {
               Categorie cat = new Categorie();

                float id = Float.parseFloat(obj.get("id").toString());
               cat.setId((int) id);
               String type =obj.get("type").toString();
               cat.setType(type);
                
                result.add(cat);
                
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
               }
        });
         NetworkManager.getInstance().addToQueueAndWait(req);
        return result;
    }
      
       public void deleteCategorie(Categorie cat){
           
       }
}
