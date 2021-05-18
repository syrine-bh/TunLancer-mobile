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
import tunlancer.Models.Replies;
import tunlancer.utils.Statics;


/**
 *
 * @author cyrinaa belguith
 */
public class ServiceReponse {
    
    public ArrayList<Replies> comments;

    public static ServiceReponse instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceReponse() {
        req = new ConnectionRequest();
    }

    public static ServiceReponse getInstance() {
        if (instance == null) {
            instance = new ServiceReponse();
        }
        return instance;
    }

    public ArrayList<Replies> parseTopic(String jsonText) {
        try {
            comments = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                Replies f = new Replies();

                float id = Float.parseFloat(obj.get("id").toString());
                f.setId((int) id);
                f.setContenu(obj.get("contenu").toString());

                
                
                

                System.out.print(obj);

                comments.add(f);
            }
        } catch (IOException ex) {

        }
        return comments;
    }

    
    
     public ArrayList<Replies> getComments(int id) {
        String url = Statics.BASE_URL + "/displayTopic/" + id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                comments = parseTopic(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return comments;
    }
      public boolean addComment(Replies c) {
        String url=Statics.BASE_URL+"/addCommentaireJSON?contenu=" + c.getContenu() + "&user=" + c.getUser_id() + "&topic=" + c.getTopic_id();

        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
      public void deleteComment(int id) {
        String url = Statics.BASE_URL + "/deleteCommentJSON/" + id;
        req.setUrl(url);
        
        req.addResponseListener((NetworkEvent evt) -> {
            String str = new String(req.getResponseData());
            
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
       
    }
    
}
