

package tunlancer.Services;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import tunlancer.Models.Experiences;
import com.codename1.l10n.ParseException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;



public class ServiceExperience {
        private static ServiceExperience instance;
      public static ServiceExperience getInstance(){
        if (instance == null)
            instance = new ServiceExperience();
        return instance;
    }
private Experiences experience;
    public void ajoutExperience(Experiences ta) {
        ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
        String Url = "http://localhost:8000/addExperience?poste="+ta.getPoste()+"&societe="+ta.getSociete()+"&Datedeb="+ta.getDatedeb()+"&Datefin="+ta.getDatefin()+""   ;// création de l'URL
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
    }
    
     public Experiences getExperience(int id){
        ConnectionRequest con = new ConnectionRequest();
        String url = "http://localhost:8000/detailExperience?id="+id;
        con.setUrl(url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            //experience = parseExperience(str);
        });
       // con.addExceptionListener((ev) -> {
          //  experience = null;
       // });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return experience;
    }
       
       
       
    public Experiences editExperience(Experiences ta) {
        ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
      
        String url = "http://localhost:8000/updateExperience?id="+ta.getId();
        con.setUrl(url);
        con.setPost(true);
       //con.addArgument("id", ta.getId());
        con.addArgument("poste", ta.getSociete());
        con.addArgument("societe", ta.getPoste());
        con.addArgument("Datedeb", ta.getDatedeb());
        con.addArgument("Datefin", ta.getDatefin());
       
              con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            experience = getExperience(ta.getId());
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return experience;
    }
     
    
    
       private Experiences parseExperience(String json) throws java.text.ParseException{
        Experiences m = new Experiences();
        try {
            JSONParser j = new JSONParser();
            
            Map<String, Object> experienceMap = j.parseJSON(new CharArrayReader(json.toCharArray()));
           
            if(experienceMap != null){
//                m.setId(((Double)experienceMap.get("id")).intValue());
                m.setPoste((String)experienceMap.get("poste"));
                m.setSociete((String)experienceMap.get("societe"));
               String sDate1 = experienceMap.get("datedeb").toString();
            Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(sDate1);
            m.setDateDeb(date1);
                  m.setDatefin((String)experienceMap.get("datefin"));
              
               
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return m;
    }


    public ArrayList<Experiences> parseListTaskJson(String json) {

        ArrayList<Experiences> listTasks = new ArrayList<>();

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
                Experiences e = new Experiences();

                float id = Float.parseFloat(obj.get("id").toString());

                e.setId((int) id);
                e.setPoste(obj.get("Poste").toString());
                e.setSociete(obj.get("Societe").toString());
                 e.setDatedeb(obj.get("datedeb").toString());
                  e.setDatefin(obj.get("date_fin").toString());
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
    
    
    ArrayList<Experiences> listTasks = new ArrayList<>();
    
    public ArrayList<Experiences> getList2(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost:8000/experience");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               ServiceExperience ser = new ServiceExperience();
                listTasks = ser.parseListTaskJson(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }
 public void delete(int id) {
	ConnectionRequest con = new ConnectionRequest();
        String url = "http://localhost:8000/deleteExperience?id="+id;
        con.setUrl(url);
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
 

   public ArrayList<Experiences> getAllExperiences() {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost:8000/displayExperience";
        con.setUrl(Url);
        ArrayList<Experiences> mapEvent = new ArrayList<>();
        con.addResponseListener((e) -> {
            String jsonRes = new String(con.getResponseData());
            try {
                JSONParser j = new JSONParser();

                Map<String, Object> events = j.parseJSON(new CharArrayReader(jsonRes.toCharArray()));

                List<Map<String, Object>> list = (List<Map<String, Object>>) events.get("root");
              //   SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                for (Map<String, Object> obj : list) {
                    
                    
                

                    Experiences ev= new Experiences();
                    float id = Float.parseFloat(obj.get("id").toString());

                ev.setId((int) id);
                    
                    
                    ev.setPoste(obj.get("poste").toString());
                    ev.setSociete(obj.get("societe").toString());
                  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                  String gg = obj.get("datedeb").toString();
                  //  ev.setDatedeb(obj.get("datedeb").toString());
                   //  String sDate1 = obj.get("Datedeb").toString();
                   //try {
                      
                   // Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(sDate1);
                   // ev.setDatedeb(date1);
                  
               // } catch (ParseException ex) {
                  //  System.out.println(ex.getMessage());
               // }
            
                    mapEvent.add(ev);
              
                }

            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(con);

        return mapEvent;
    }
 }