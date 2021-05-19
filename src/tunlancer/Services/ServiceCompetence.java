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
import java.util.List;
import java.util.Map;
import tunlancer.Models.Competence;

/**
 *
 * @author asus
 */
public class ServiceCompetence {
    private static ServiceCompetence instance;
      public static ServiceCompetence getInstance(){
        if (instance == null)
            instance = new ServiceCompetence();
        return instance; 
}
      private Competence Competence;
        public void ajoutCompetence(Competence c) {
      ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
        String Url = "http://localhost:8000/addCompetence?Titre="+c.getTitre()+"&Domaine="+c.getDomaine()+"&Userid="+c.getUser_id()+""   ;// création de l'URL
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
    }
    
     public Competence getFormation(int id){
        ConnectionRequest con = new ConnectionRequest();
        String url = "http://localhost:8000/detailCompetence?id="+id;
        con.setUrl(url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
           Competence= parseCompetence(str);
        });
       // con.addExceptionListener((ev) -> {
          //  experience = null;
       // });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return Competence;
    }
       
       
       
    public Competence editCompetence(Competence ta) {
        ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
      
        String url = "http://localhost:8000/updateCompetence?id="+ta.getId();
        con.setUrl(url);
        con.setPost(true);
      // con.addArgument("id", ta.getId());
      // con.addArgument("id", String.valueOf(ta.getId()));
       con.addArgument("titre", ta.getTitre());
        con.addArgument("domaine", ta.getDomaine());
       ;
       
              con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
           Competence = getFormation(ta.getId());
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return Competence;
    }
     
    
    
       private Competence parseCompetence(String json){
        Competence m = new Competence();
        try {
            JSONParser j = new JSONParser();
            
            Map<String, Object> experienceMap = j.parseJSON(new CharArrayReader(json.toCharArray()));
            
            if(experienceMap != null){
               m.setId(((Double)experienceMap.get("id")).intValue());
                m.setTitre((String)experienceMap.get("titre"));
                 m.setDomaine((String)experienceMap.get("domaine"));             
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return m;
    }


    public ArrayList<Competence> parseListTaskJson(String json) {

        ArrayList<Competence> listTasks = new ArrayList<>();

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
               Competence e = new Competence();

                float id = Float.parseFloat(obj.get("id").toString());

                e.setId((int) id);
                e.setTitre(obj.get("titre").toString());
                e.setDomaine(obj.get("domaine").toString());
                
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
    
    
    ArrayList<Competence> listTasks = new ArrayList<>();
    
  
 public void delete(int id) {
	ConnectionRequest con = new ConnectionRequest();
        String url = "http://localhost:8000/deleteCompetence?id="+id;
        con.setUrl(url);
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
 

   public ArrayList<Competence> getAllCompetences() {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost:8000/displayCompetence";
        con.setUrl(Url);
        ArrayList<Competence> mapEvent = new ArrayList<>();
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
                    

                    Competence ev= new Competence();
                    float id = Float.parseFloat(obj.get("id").toString());

                ev.setId((int) id);
                    
                    
                    ev.setTitre(obj.get("Titre").toString());
                    ev.setDomaine(obj.get("Domaine").toString());
                   
 

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