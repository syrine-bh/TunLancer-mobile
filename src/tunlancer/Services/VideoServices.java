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

import java.util.Date;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import tunlancer.Models.Video;
import tunlancer.utils.Statics;


/**
 *
 * @author alaa
 */
public class VideoServices {

    public ArrayList<Video> videos;
    public static VideoServices instance;
    public boolean resultOK;
    private ConnectionRequest req;
    UserServices us = new UserServices();

    public VideoServices() {
        req = new ConnectionRequest();

    }

    public static VideoServices getInstance() {
        if (instance == null) {
            instance = new VideoServices();
        }
        return instance;
    }

    public ArrayList<Video> parseVideos(String jsonText) {
        try {
            videos = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> videosListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) videosListJson.get("root");

            for(Map<String,Object> obj : list){
                Video v = new Video();
          
                v.setId((int)Float.parseFloat(obj.get("id").toString()));
                v.setUrl(obj.get("url").toString());
                v.setTitle(obj.get("title").toString());
//                v.setPublish_date(new Date((((Double) ((Map<String, Object>) obj.get("publish_date")).get("timestamp")).longValue() * 1000)));
//                v.setOwner(new User(
//                        (int)Float.parseFloat(obj.get("id").toString()),
//                        obj.get("username").toString(),
//                        obj.get("email").toString(),
//                        "",
//                        obj.get("sexe").toString(),
//                        obj.get("adresse").toString(),
//                        obj.get("firstName").toString(),
//                        obj.get("name").toString(),
//                        obj.get("telephoneNumber").toString(),
//                        obj.get("bio").toString(),
//                        obj.get("roles").toString(),
//                        new Date((((Double) ((Map<String, Object>) obj.get("birthday")).get("timestamp")).longValue() * 1000)),
//                        obj.get("profilePic").toString()
//                ));              

                videos.add(v);
            }
        } catch (IOException ex) {
            
        }
        return videos;

    }
    
    public ArrayList<Video> getVideo(int owner){
        String url = Statics.BASE_URL+"/videos/owner/"+owner;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                videos = parseVideos(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return videos;
    }
    
    

}
