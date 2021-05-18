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
import tunlancer.Models.questiontab;
import tunlancer.utils.Statics;
import tunlancer.utils.UserSession;

/**
 *
 * @author Hiba
 */
public class QuizServices {
    
    public ArrayList<questiontab> Questions;
    
    public static QuizServices instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private QuizServices() {
         req = new ConnectionRequest();
    }

    public static QuizServices getInstance() {
        if (instance == null) {
            instance = new QuizServices();
        }
        return instance;
    }

//    public boolean addanswer(UserSession us) {
//        
//        String url = Statics.BASE_URL+"/addelearningusersession/"+us.getScore() ;//création de l'URL
//        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
//        req.addArgument("iduser",Integer.toString(ues.getUser_id()));
//        req.addArgument("sessionid",Integer.toString(ues.getElearning_sessioni_id()));
//        req.addArgument("time",Integer.toString(ues.getTime()));
//        req.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
//                req.removeResponseListener(this); //Supprimer cet actionListener
//             
//                
//            }
//        });
//        NetworkManager.getInstance().addToQueueAndWait(req);
//        return resultOK;
//    }

//    public ArrayList<questiontab> parseQuestions(String jsonText){
//        try {
//            Questions=new ArrayList<>();
//            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json
//
//            
//            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
//
//            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
//            
//            //Parcourir la liste des tâches Json
//            for(Map<String,Object> obj : list){
//                //Création des tâches et récupération de leurs données
//                questiontab t = new questiontab();
//                float id = Float.parseFloat(obj.get("id").toString());
//                t.setId((int)id);
//                float quiz_id = Float.parseFloat(obj.get("quiz_id").toString());
//                t.setId((int)quiz_id);
//                t.setQuestions(obj.get("questions").toString());
//                List<Map<String,Object>>reponse=(List<Map<String,Object>>)obj.get("answers");
//                for(Map<String,Object> obj2 :reponse)
//                {
//                    answer a=new answer();
//                    a=answer.createanswer(obj2);
//                    System.out.println(a);
//                    t.getAnswers().add(a);
//                }
//                Questions.add(t);
//            }
//            
//            
//        } catch (IOException ex) {
//            
//        }
//
//        return Questions;
//    }
//    
//    public ArrayList<question> getAllTasks(){
//        String url = Statics.BASE_URL+"/allquestion";
//        req.setUrl(url);
//        req.setPost(false);
//        req.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                Questions = parseTasks(new String(req.getResponseData()));
//                req.removeResponseListener(this);
//            }
//        });
//        NetworkManager.getInstance().addToQueueAndWait(req);
//        return Questions;
//    }
}
