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
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.DateFormatPatterns;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import tunlancer.Models.Users;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import tunlancer.utils.Statics;
//import tunlancer.utils.UserSession;
//import java.util.logging.*;

/**
 *
 * @author Anis
 */
public class UserServices {

    public static UserServices instance;

   
    private final ConnectionRequest con;

    public UserServices() {
        con = new ConnectionRequest();
    }

    public static UserServices getInstance() {
        if (instance == null) {
            instance = new UserServices();
        }
        return instance;
    }
    boolean result;

    public boolean loginAction(String email, String password) {

        // création d'une nouvelle demande de connexion
        String url = Statics.BASE_URL + "/Loginnnn?email="+email+"&password="+password;
        con.setUrl(url);// Insertion de l'URL de notre demande de connexion

        con.addResponseListener((e) -> {
            result = con.getResponseCode() == 200;
            
            if (result) {
             //   try {
                    parseUser(new String(con.getResponseData()));
                    String str = new String(con.getResponseData());//Récupération de la réponse du serveur
                     System.out.println(str);//Affichage de la réponse serveur sur la console
                     
              //  } catch (ParseException ex) {

               // } catch (IOException ex) {
//                    Logger.getLogger(UserServices.class.getName()).log(Level.SEVERE, null, ex);
                }
           // }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
        return result;
    }

    private Users parseUser(String json){
        Users m = new Users();
        try {
            JSONParser j = new JSONParser();
            
            Map<String, Object> userMap = j.parseJSON(new CharArrayReader(json.toCharArray()));
            
            if(userMap != null){
//               m.setId(((Double)memberMap.get("id")).intValue());
                m.setNom((String)userMap.get("Nom"));
                m.setPrenom((String)userMap.get("Prenom"));
//                m.setTel((Integer)userMap.get("Tel"));
                m.setEmail((String)userMap.get("Email"));
                m.setpassword((String)userMap.get("Password"));
                m.setPays((String)userMap.get("Pays"));
                m.setRole((String)userMap.get("Role"));
                // UserSession z = UserSession.getInstance(m);
               //  m.setAge((Integer)userMap.get("Age"));
               // m.setDescription((String)userMap.get("description"));
//                m.setTel(((Double)userMap.get("Tel")).intValue());
               // m.setAge(((Double)userMap.get("Age")).intValue());
               
               //  m.setPath((String)userMap.get("path"));
                
              
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return m;
    }
 public boolean RegisterAction(String email ,String password) {

        // création d'une nouvelle demande de connexion
        String url = Statics.BASE_URL + "/Register/" + "?email=" + email + "&password="+password;
        con.setUrl(url);// Insertion de l'URL de notre demande de connexion

        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               result = con.getResponseCode() == 200;
            
            if (result) {
                
                    
                    String str = new String(con.getResponseData());//Récupération de la réponse du serveur
                     System.out.println(str);//Affichage de la réponse serveur sur la console
             

                
            }
            }
        });
            
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
        return result;
    }
}
