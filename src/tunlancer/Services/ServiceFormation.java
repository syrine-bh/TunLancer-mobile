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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import tunlancer.Models.Formation;

/**
 *
 * @author asus
 */
public class ServiceFormation {
    private static ServiceFormation instance;
      public static ServiceFormation getInstance(){
        if (instance == null)
            instance = new ServiceFormation();
        return instance; 
}
      private Formation formation;
        public void ajoutFormation(Formation f) {
      ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
        String Url = "http://localhost:8000/addFormation?titre="+f.getTitre()+"&periode="+f.getPeriode()+"&specialite="+f.getSpecialite()+"&description="+f.getDescription()+""   ;// création de l'URL
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
    }
    
     public Formation getFormation(int id){
        ConnectionRequest con = new ConnectionRequest();
        String url = "http://localhost:8000/detailFormation?id="+id;
        con.setUrl(url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
          
             //   formation= parseFormation(str);
           
        });
       // con.addExceptionListener((ev) -> {
          //  experience = null;
       // });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return formation;
    }
       
       
       
    public Formation editFormation(Formation ta) {
        ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
      
        String url = "http://localhost:8000/updateFormation?id="+ta.getId();
        con.setUrl(url);
        con.setPost(true);
       //con.addArgument("id", ta.getId());
       con.addArgument("titre", ta.getTitre());
         con.addArgument("periode", ta.getPeriode());
        con.addArgument("specialite", ta.getSpecialite());
       
          con.addArgument("description", ta.getDescription());
       
              con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
           formation = getFormation(ta.getId());
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return formation;
    }
     
    
    
       private Formation parseFormation(String json) throws ParseException{
        Formation m = new Formation();
        try {
            JSONParser j = new JSONParser();
            
            Map<String, Object> experienceMap = j.parseJSON(new CharArrayReader(json.toCharArray()));
            
            if(experienceMap != null){
             //   m.setId(((Double)experienceMap.get("id")).intValue());
                m.setTitre((String)experienceMap.get("titre"));
                 String sDate1 = experienceMap.get("periode").toString();
            Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(sDate1);
            m.setPeriode(date1);

                m.setSpecialite((String)experienceMap.get("specialite"));
                  m.setDescription((String)experienceMap.get("description"));
              
               
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return m;
    }


    public ArrayList<Formation> parseListTaskJson(String json) {

        ArrayList<Formation> listTasks = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            /*
                On doit convertir notre réponse texte en CharArray à fin de
            permettre au JSONParser de la lire et la manipuler d'ou vient 
            l'utilité de new CharArrayReader(json.toCharArray())
            
            La méthode parse json retourne une MAP<String,Object> ou String est 
            la clé principale de notre résultat.
            Dans notre cas la clé principale n'est pas définie cela ne veux pas
            dire qu'elle est manquante mais plutôt gardée à la valeur par defaut
            qui est root.
            En fait c'est la clé de l'objet qui englobe la totalité des objets 
                    c'est la clé définissant le tableau de tâches.
            */
            Map<String, Object> tasks = j.parseJSON(new CharArrayReader(json.toCharArray()));
                       
            
            /* Ici on récupère l'objet contenant notre liste dans une liste 
            d'objets json List<MAP<String,Object>> ou chaque Map est une tâche                
            */
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");

            //Parcourir la liste des tâches Json
            for (Map<String, Object> obj : list) {
                //Création des tâches et récupération de leurs données
                Formation e = new Formation();

                float id = Float.parseFloat(obj.get("id").toString());

                e.setId((int) id);
                e.setTitre(obj.get("titre").toString());
              
                 e.setSpecialite(obj.get("specialite").toString());
                   e.setPeriode(obj.get("periode").toString());
                  e.setDescription(obj.get("description").toString());
                System.out.println(e);
                
                listTasks.add(e);
 System.out.println( listTasks.add(e));
            }

        } catch (IOException ex) {
        }
        
        /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
        System.out.println(listTasks);
        return listTasks;

    }
    
    
    ArrayList<Formation> listTasks = new ArrayList<>();
    
    public ArrayList<Formation> getList2(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost:8000/formation");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceFormation ser = new ServiceFormation();
                listTasks = ser.parseListTaskJson(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }
 public void delete(int id) {
	ConnectionRequest con = new ConnectionRequest();
        String url = "http://localhost:8000/deleteFormation?id="+id;
        con.setUrl(url);
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
 

   public ArrayList<Formation> getAllFormations() {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost:8000/displayFormation";
        con.setUrl(Url);
        ArrayList<Formation> mapEvent = new ArrayList<>();
        con.addResponseListener((e) -> {
            String jsonRes = new String(con.getResponseData());
            try {
                JSONParser j = new JSONParser();

                Map<String, Object> events = j.parseJSON(new CharArrayReader(jsonRes.toCharArray()));

                List<Map<String, Object>> list = (List<Map<String, Object>>) events.get("root");
                // SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                for (Map<String, Object> obj : list) {
                    
                     /*double t = (double) obj.get("dateLivraison");
                        long x = (long) (t * 1000L);
                    String format = new SimpleDateFormat("MM/dd/yyyy").format(new Date(x));*/
                    

                    Formation ev= new Formation();
                    float id = Float.parseFloat(obj.get("id").toString());

                ev.setId((int) id);
                    
                    
                    ev.setTitre(obj.get("titre").toString());
                    
                    ev.setSpecialite(obj.get("specialite").toString());
                    
                    ev.setPeriode(obj.get("periode").toString());
                    ev.setDescription(obj.get("description").toString());
                   
                 
              

                    mapEvent.add(ev);
               //    System.out.println("id commande : "+lcp.getPrixTotal());
                }

            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(con);

        return mapEvent;
    }
 }



       
    