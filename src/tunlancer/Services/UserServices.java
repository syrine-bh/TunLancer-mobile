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
import tunlancer.utils.UserSession;
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
        String url = Statics.BASE_URL + "/loginJson?email="+email+"&password="+password;
        con.setUrl(url);// Insertion de l'URL de notre demande de connexion

        con.addResponseListener((e) -> {
            result = con.getResponseCode() == 200;
            
            if (result) {
                try {
                    parseListUserJson(new String(con.getResponseData()));
                    String str = new String(con.getResponseData());//Récupération de la réponse du serveur
                     System.out.println(str);//Affichage de la réponse serveur sur la console
                     
                } catch (ParseException ex) {

                } catch (IOException ex) {
//                    Logger.getLogger(UserServices.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
        return result;
    }

    public Users parseListUserJson(String json) throws ParseException, IOException {

        Users u = new Users();
        try {
            JSONParser j = new JSONParser();

            Map<String, Object> obj = j.parseJSON(new CharArrayReader(json.toCharArray()));
            u.setId((int) (double) obj.get("id"));
            u.setNom(obj.get("Nom").toString());
            u.setPrenom(obj.get("Prenom").toString());
            u.setEmail(obj.get("Email").toString());
            u.setRole(obj.get("Role").toString());

            u.setPhoto(obj.get("Photo").toString());

            UserSession z = UserSession.getInstance(u);
            

        } catch (IOException ex) {
        }

        return u;
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
