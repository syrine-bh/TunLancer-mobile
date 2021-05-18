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
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import tunlancer.Models.Topics;
import tunlancer.utils.Statics;

/**
 *
 * @author cyrinaa belguith
 */
public class ServiceTopic {
    //singleton
    public static ServiceTopic instance = null;
    private ConnectionRequest req;
    ArrayList<Topics> topics;
    boolean t;
    
    
    public static ServiceTopic getInstance(){
        if (instance == null) {
            instance = new ServiceTopic();
        }
        return instance;
    }
    
    public ServiceTopic(){
        req = new ConnectionRequest();
        
    }
    
    //ajout
    public void addTopic(Topics topic){

        String url=Statics.BASE_URL+ "/addTopicJSON/new?titre="+topic.getTitre()+"&contenu=" +topic.getContenu();
        req.setUrl(url);
        req.addResponseListener((e)->{
            String str = new String(req.getResponseData()); //reponse JSON
            System.out.println("data "+str);
            Dialog.show("Confirmation", "success", "Ok", null);
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req); //execution de requete
        
    }
    
    public boolean ajouterTopic(Topics topic){
        
        String url=Statics.BASE_URL+"/addTopicJSON/new?titre="+topic.getTitre()+"&contenu="+topic.getContenu();
        ConnectionRequest con = new ConnectionRequest();
        
        con.addResponseListener((NetworkEvent evt) -> {

            byte[] data = (byte[]) evt.getMetaData();
            String s = new String(data);
            System.out.println(s);
            if (!s.contains("erreur")) {
               ArrayList<Topics> pr = parseTopic(s);
               topic.setId(pr.get(0).getId());
               System.out.println(topic.getId());
                 t= true;
                Dialog.show("Confirmation", "success", "Ok", null);
            } else {
                Dialog.show("Erreur", "date", "Ok", null);
                t=false;
            }

        });
        
        NetworkManager.getInstance().addToQueueAndWait(con);
       return t;
    }
    
    
    //parse topic
    public ArrayList<Topics> parseTopic(String jsonText) {
        try {
            topics = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> PostsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String, Object>> list = (List<Map<String, Object>>) PostsListJson.get("root");
            //parsing json  
            for (Map<String, Object> obj : list) {
                Topics topic = new Topics();

                float id = Float.parseFloat(obj.get("id").toString());
                topic.setId((int) id);
                
                Object idu = obj.get("idu");
//                LinkedHashMap<Object, Object> lhm = new LinkedHashMap<>();
//                lhm = (LinkedHashMap<Object, Object>) obj.get("User_id");
                topic.setUser_id(48);
                
                topic.setTitre(obj.get("titre").toString());
                topic.setContenu(obj.get("contenu").toString());
                

                
                
            }

        } catch (IOException ex) {
            System.out.println("Exception in parsing topic ");
        }

        return topics;
    }
    
     //affichage
    public ArrayList<Topics> getAllTopics() {
        ArrayList<Topics> result = new ArrayList<>();
        String url = Statics.BASE_URL + "/displayTopics";

        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp = new JSONParser();
                
                try{
                    Map<String,Object>mapTopics=jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String,Object>> listOfMaps = (List<Map<String,Object>>) mapTopics.get("root");
                    
                for (Map<String, Object> obj : listOfMaps) {
                    Topics topics = new Topics();

                    float id = Float.parseFloat(obj.get("id").toString());
                    topics.setId((int) id);
                    
                    Object user = obj.get("user_id");
//                    
                    
                    topics.setTitre(obj.get("titre").toString());
                    topics.setContenu(obj.get("contenu").toString());
                    
//                    Map<String, Object> mapDateCreation = (Map<String, Object>) obj.get("date");
//                    float dateC = Float.parseFloat(mapDateCreation.get("timestamp").toString());
//                    String date = new SimpleDateFormat("dd/MM/yyyy").format(new Date((long) (dateC* 1000L)));
//                    topics.setDate(date);
//                    
                    //insert data
                    result.add(topics);
                    

            }
                }catch(Exception ex){
                    ex.printStackTrace();
                }
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return result;
    }
    
    
    public boolean supprimer(Topics g){
        String url = Statics.BASE_URL + "/deleteTopicJSON/" + g.getId();
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                t = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
                
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return t;
    }
    
    //detail topic
//    public Topics DetailsTopic(int id, Topics topic){
//        String url = Statics.BASE_URL + "/displayTopic/{id}";
//
//        req.setUrl(url);
//        return null;
//    }

    public boolean modifier(Topics g) {
        
        String url = Statics.BASE_URL + "/updateTopicJSON/" + g.getId() + "?titre=" + g.getTitre() + "&contenu=" + g.getContenu();
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.setPost(true);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                t = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return t;
        
    }
    
    public boolean editTopic(Topics p, int id) {
        String url = Statics.BASE_URL+ "/updateTopicJSON/" + id + "?titre=" + p.getTitre()+ "&contenu=" + p.getContenu() ;
        req.setUrl(url);
        req.setPost(true);
        req.addResponseListener((NetworkEvent evt) -> {
            byte[] data = (byte[]) evt.getMetaData();
            String s = new String(data);
            System.out.println(s);
            if (!s.contains("erreur")) {
                Dialog.show("Confirmation", "success", "Ok", null);
                t=true;

            } else {
                Dialog.show("Erreur", "date", "Ok", null);
                t=false;

            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
return t ;
    }

    public boolean addReview(Topics r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
