/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunlancer.Services;

import com.codename1.components.InfiniteProgress;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import tunlancer.Models.Replies;
import tunlancer.Models.Topics;
import tunlancer.MyApplication;
import static tunlancer.Services.ServiceTopic.instance;
import tunlancer.utils.Statics;

/**
 *
 * @author cyrinaa belguith
 */
public class ServiceReplies {
    public static ServiceReplies instance = null;
    private ConnectionRequest req;
    ArrayList<Replies> replies;
    boolean t;
    static Map h;

     public static ServiceReplies getInstance(){
        if (instance == null) {
            instance = new ServiceReplies();
        }
        return instance;
    }
     
     public ServiceReplies(){
        req = new ConnectionRequest();
        
    }
    
    public static Map<String, Object> getResponse(String url){
        url = Statics.BASE_URL+"/"+url;
        System.out.println("url---------------"+url);
        ConnectionRequest r = new ConnectionRequest();
        System.out.println("url ::::::::: "+url);
        r.setUrl(url);
        r.setPost(false);
        System.out.println("url   :   "+r);
        InfiniteProgress prog = new InfiniteProgress();
        Dialog dlg = prog.showInifiniteBlocking();
        r.setDisposeOnCompletion(dlg);
        r.addResponseListener((evt) -> {
            try {
                JSONParser p = new JSONParser();
                Reader targetReader = new InputStreamReader(new ByteArrayInputStream(r.getResponseData()));
                System.out.println(targetReader);
                h= p.parseJSON(targetReader);
                
            } catch (IOException ex) {
                //Logger.getLogger(MyApplication.class.getName()).log(Level.SEVERE, null, ex);
            }
 
        });
        NetworkManager.getInstance().addToQueueAndWait(r);
        return h; 
    }
    
    public void ajoutCommentaire(Replies r){
           
     String url=Statics.BASE_URL+"/addCommentaireJSON/" + r.getTopic_id() + "/" + r.getContenu() + "/" + r.getUser_id();
     req.setUrl(url);
     req.addRequestHeader("X-Requested-With", "XMLHttpRequest");
//     req.addArgument("topic", r.getTopic_id()+"");
//     req.addArgument("contenu", r.getContenu());
//     req.addArgument("user", MyApplication.id+"");
     System.out.println(url);
     req.addResponseListener((e)->{
            String str = new String(req.getResponseData()); //reponse JSON
            System.out.println("data "+str);
            Dialog.show("Confirmation", "success", "Ok", null);
        });
      
        NetworkManager.getInstance().addToQueueAndWait(req);
        
    }
    
    
    public ArrayList<Replies> getList(Replies r){
        ArrayList<Replies> listCommentaires= new ArrayList<>();
        String url=Statics.BASE_URL+ "/displayTopic/"+r.getId();
        req.addResponseListener(new ActionListener<NetworkEvent>() {
              @Override
            public void actionPerformed(NetworkEvent evt) {
            //listTasks = getListTask(new String(con.getResponseData()));
            JSONParser jsonp = new JSONParser();
            
            try {
                //renvoi une map avec clé = root et valeur le reste
                Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                System.out.println("root:" +tasks.get("root"));
                
                List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
                
                for (Map<String, Object> obj : list) {
                    Replies c = new Replies();
                c.setContenu(obj.get("contenu").toString());
                 float id = Float.parseFloat(obj.get("id").toString());
              c.setId((int)id);
              listCommentaires.add(c);
                    
                }
            } catch (IOException ex) {
            }
        }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return listCommentaires;
    }
    
    
    public  ArrayList<Replies> getListsComments(Map m){
        ArrayList<Replies> listDisponibilite = new ArrayList<>();
        ArrayList d = (ArrayList) m.get("root");
        for(int i = 0; i<d.size() ; i++){
            Map f =  (Map) d.get(i);
            Replies p = new Replies();
            p.setContenu((String)f.get("commentaire"));
            listDisponibilite.add(p);  
        }        
        System.out.println(listDisponibilite);
        return listDisponibilite;
    }
    
}
