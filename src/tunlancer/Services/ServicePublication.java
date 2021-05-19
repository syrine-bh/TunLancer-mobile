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
import java.util.*;
import tunlancer.Models.Publication;
import tunlancer.utils.Statics;

/**
 *
 * @author Cyrina
 */
public class ServicePublication {

    public ArrayList<Publication> publications;
    public static ServicePublication instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServicePublication() {
        req = new ConnectionRequest();
    }

    public static ServicePublication getInstance() {
        if (instance == null) {
            instance = new ServicePublication();
        }
        return instance;
    }

    //parse publication
    public ArrayList<Publication> parsePublication(String jsonText) {
        try {
            publications = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> PostsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) PostsListJson.get("root");
            //parsing json in individual publications 
            for (Map<String, Object> obj : list) {
                Publication publication = new Publication();

                float id = Float.parseFloat(obj.get("id").toString());
                publication.setId((int) id);

                Object idu = obj.get("idu");
                LinkedHashMap<Object, Object> lhm = new LinkedHashMap<>();
                lhm = (LinkedHashMap<Object, Object>) obj.get("idU");
                publication.setIdU(lhm);

                publication.setDescription(obj.get("description").toString());
                publication.setLocalisation(obj.get("localisation").toString());
                publication.setImage_name(obj.get("imageName").toString());

                float archive = Float.parseFloat(obj.get("archive").toString());
                publication.setArchive((int) archive);
                float type = Float.parseFloat(obj.get("type").toString());
                publication.setType((int) type);

                if (obj.get("imageName").toString().contains(".png") || obj.get("imageName").toString().contains(".jpg") || obj.get("imageName").toString().contains(".gif")) {
                    publications.add(publication);
                }
            }

        } catch (IOException ex) {
            System.out.println("Exception in parsing publications ");
        }

        return publications;
    }

    //consommation du webservice getAllPublications
    public ArrayList<Publication> getAllPublications() {
        String url = Statics.BASE_URL + "publications/" + "getAllPublications";

        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                publications = parsePublication(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return publications;
    }

    
    //consommation du webservice getAllPublications
    public boolean addPublication(Publication p) {

        String url = Statics.BASE_URL + "publications/AddPublicationMobile/" + p.getType()+ "/" + p.getArchive() + "/" + p.getDescription()+ "/" + p.getImage_name()+ "/" + p.getLocalisation()+ "/" + p.getIdUser();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
    
    
    public boolean ModifierPublication(Publication p) {

        String url =  Statics.BASE_URL +  "/publications/EditPublicationMobile"   + p.getType()+ "/" + p.getArchive() + "/" + p.getDescription()+ "/" + p.getImage_name()+ "/" + p.getLocalisation();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public boolean supprimerPublication(int id) {

        String url = Statics.BASE_URL + "publications/DeletePublicationMobile/" + id ;
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
}
