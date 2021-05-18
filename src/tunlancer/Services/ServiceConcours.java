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
import com.codename1.io.Util;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import com.google.gson.Gson;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import tunlancer.Models.*;
import tunlancer.utils.Statics;
import java.util.Map;
/**
 *
 * @author Hiba
 */
public class ServiceConcours {

public ArrayList<Concour> concours;
public ArrayList<Quiz> quizs;
public ArrayList<reponsetab> reponses;
public ArrayList<Score> scores;
 public ArrayList<questiontab> Questions;
    
List<Video> rank;
List<Video> Videos;
    List<Participation> participations;
    public static ServiceConcours instance;
    private final ConnectionRequest con;

    public ServiceConcours() {
        con = new ConnectionRequest();
    }

    public static ServiceConcours getInstance() {
        if (instance == null) {
            instance = new ServiceConcours();
        }
        return instance;
    }


    
    public List<Concour> getAllConcours() {

        String url = Statics.BASE_URL + "/displayConcoursJson";
        con.setUrl(url);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    String str = new String(con.getResponseData());
                    concours = new ArrayList<>();
                    JSONParser j = new JSONParser();

                    Map<String, Object> CompMap = j.parseJSON(new CharArrayReader(str.toCharArray()));
                    List<Map<String, Object>> compList = (List<Map<String, Object>>) CompMap.get("root");
                    if (compList != null) {
                        for (Map<String, Object> map : compList) {
                            concours.add(parseConcours(map));
                        }
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (ParseException ex) {
                    System.out.println("failed");                }
                con.removeResponseListener(this);
            }

           

        });

        NetworkManager.getInstance().addToQueueAndWait(con);

        return concours;
    }

    private Concour parseConcours(Map<String, Object> map) throws ParseException {
        Concour c = new Concour();
//        c.setId(((Double) map.get("id")).intValue());
        c.setId(((Double) map.get("id")).intValue());

        c.setSujet(map.get("sujet").toString());
        c.setNom(map.get("nom").toString());
        c.setImageName(map.get("imageName").toString());
        c.setDescription(map.get("description").toString());
        c.setCategorie(map.get("categorie").toString());
        c.setCouleur(map.get("couleur").toString());
        String sDate1 = map.get("dateDebut").toString();
                try {
                    Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(sDate1);
                    c.setDateDebut(date1);
                } catch (ParseException ex) {
                    System.out.println(ex.getMessage());
                }
        String sDate2 = map.get("dateFin").toString();
                try {
                    Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(sDate2);
                    c.setDateFin(date2);
                } catch (ParseException ex) {
                    System.out.println(ex.getMessage());
                }
                if(map.get("isVideo").toString().equals("true"))
{c.setIsVideo(true);}
else
{c.setIsVideo(false);}

        return c;

    }
 
     public List<Quiz> getAllQuiz() {

        String url = Statics.BASE_URL + "/displayQuizJson";
        con.setUrl(url);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    String str = new String(con.getResponseData());
                    quizs = new ArrayList<>();
                    JSONParser j = new JSONParser();

                    Map<String, Object> CompMap = j.parseJSON(new CharArrayReader(str.toCharArray()));
                    List<Map<String, Object>> compList = (List<Map<String, Object>>) CompMap.get("root");
                    if (compList != null) {
                        for (Map<String, Object> map : compList) {
                            quizs.add(parseQuizs(map));
                        }
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (ParseException ex) {
                    System.out.println("failed");                }
                con.removeResponseListener(this);
            }

        });

        NetworkManager.getInstance().addToQueueAndWait(con);

        return quizs;
    }

    private Quiz parseQuizs(Map<String, Object> map) throws ParseException {
        Quiz q = new Quiz();
        q.setId(((Double) map.get("id")).intValue());
        q.setNb_questions(((Double) map.get("nbQuestions")).intValue());
        q.setNom(map.get("nom").toString());
        

        return q;

    }
    
    /**
     *
     * @return
     */
    public List<Quiz> getQuizByConcour(Concour c) {

        String url = Statics.BASE_URL + "/displayQuizByConcourJson/"+c.getId();
        con.setUrl(url);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    String str = new String(con.getResponseData());
                    quizs = new ArrayList<>();
                    JSONParser j = new JSONParser();

                    Map<String, Object> CompMap = j.parseJSON(new CharArrayReader(str.toCharArray()));
                    List<Map<String, Object>> compList = (List<Map<String, Object>>) CompMap.get("root");
                    if (compList != null) {
                        for (Map<String, Object> map : compList) {
                            quizs.add(parseQuizByConcour(map,c));
                        }
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (ParseException ex) {
                    System.out.println("failed");                }
                con.removeResponseListener(this);
            }

        });

        NetworkManager.getInstance().addToQueueAndWait(con);

        return quizs;
    }

    private Quiz parseQuizByConcour(Map<String, Object> map, Concour c) throws ParseException {
        Quiz q = new Quiz();
        q.setId(((Double) map.get("id")).intValue());
        q.setNb_questions(((Double) map.get("nbQuestions")).intValue());
        q.setNom(map.get("nom").toString());
        q.setConcour_id(c);

        return q;

    }
    
    /**
     *
     * @return
     */
    public List<reponsetab> getReponses(questiontab qus) {

        String url = Statics.BASE_URL + "/listReponsesJson/"+qus.getId();
        System.out.println(url);
        con.setUrl(url);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    String str = new String(con.getResponseData());
                    reponses = new ArrayList<>();
                    JSONParser j = new JSONParser();

                    Map<String, Object> CompMap = j.parseJSON(new CharArrayReader(str.toCharArray()));
                    List<Map<String, Object>> compList = (List<Map<String, Object>>) CompMap.get("root");
                    if (compList != null) {
                        for (Map<String, Object> map : compList) {
                            reponses.add(parseReponses(map));
                        }
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (ParseException ex) {
                    System.out.println("failed");                }
                con.removeResponseListener(this);
            }

        });

        NetworkManager.getInstance().addToQueueAndWait(con);

        return reponses;
    }

    private reponsetab parseReponses(Map<String, Object> map) throws ParseException {
        reponsetab r = new reponsetab();
        r.setId(((Double) map.get("id")).intValue());
        r.setReponses(map.get("reponses").toString());
 if(map.get("statu").toString().equals("true"))
{r.setStatu(true);}
else
{r.setStatu(false);}

        return r;

    }
    
    
        /**
     *
     * @return
     */
    public List<Score> getScores(Quiz q,Concour c) {

        String url = Statics.BASE_URL + "/getScoreByQuiz/"+q.getId();
        System.out.println(url);
        con.setUrl(url);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    String str = new String(con.getResponseData());
                    scores = new ArrayList<>();
                    JSONParser j = new JSONParser();

                    Map<String, Object> CompMap = j.parseJSON(new CharArrayReader(str.toCharArray()));
                    List<Map<String, Object>> compList = (List<Map<String, Object>>) CompMap.get("root");
                    if (compList != null) {
                        for (Map<String, Object> map : compList) {
                            scores.add(parseScores(map,c));
                        }
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (ParseException ex) {
                    System.out.println("failed");                }
                con.removeResponseListener(this);
            }

        });

        NetworkManager.getInstance().addToQueueAndWait(con);

        return scores;
    }

    private Score parseScores(Map<String, Object> map,Concour c) throws ParseException {
        Score scr = new Score();
        scr.setId(((Double) map.get("id")).intValue());
        scr.setEmail(map.get("email").toString());
        scr.setPseudo(map.get("pseudo").toString());
        scr.setImage_profil(map.get("image_profil").toString());


        //User
        Users u = new Users();
        u.setId((int) (double) ((((Map<String, Object>) map.get("user")).get("id"))));
        u.setNom(((((Map<String, Object>) map.get("user")).get("nom"))).toString());
        u.setEmail(((((Map<String, Object>) map.get("user")).get("email"))).toString());
        u.setRole(((((Map<String, Object>) map.get("user")).get("role"))).toString());
        u.setPhoto(((((Map<String, Object>) map.get("user")).get("photo"))).toString());
        u.setPrenom(((((Map<String, Object>) map.get("user")).get("prenom"))).toString());

System.out.println("verif user "+u);
        scr.setUser_id(u);
        //Video
        Quiz q = new Quiz();
        q.setId((int) (double) ((((Map<String, Object>) map.get("quiz")).get("id"))));
        q.setConcour_id(c);
        q.setNom((((Map<String, Object>) map.get("quiz")).get("nom")).toString());
       
        
       
        scr.setQuiz_id(q);

        return scr;

    }
    private Score score;
        public void ajoutScore(Score scr,String scrscore,int user ,int quiz)  {
      ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
        String Url = "http://localhost:8000/resultJson?pseudo="+scr.getPseudo()+
                "&score="+scrscore+"&email="+scr.getEmail()+"&imageProfil="+scr.getImage_profil()+"&quiz="+quiz+"&user="+user;
                System.out.println(Url);
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
    }
    
    public List<Participation> ConcourParticipationList(Concour c) {

        String url = Statics.BASE_URL + "/concour/participationJson/" + c.getId();
        con.setUrl(url);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    String str = new String(con.getResponseData());

                    participations = new ArrayList<>();
                    JSONParser j = new JSONParser();

                    Map<String, Object> ParticipationsMap = j.parseJSON(new CharArrayReader(str.toCharArray()));

                    List<Map<String, Object>> ParticipationsList = (List<Map<String, Object>>) ParticipationsMap.get("root");
                    if (ParticipationsList != null) {
                        for (Map<String, Object> map : ParticipationsList) {

                            participations.add(parseParticipations(map, c));
                        }
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                con.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(con);
        return participations;
    }

    private Participation parseParticipations(Map<String, Object> map, Concour c) {
        Participation p = new Participation();
        p.setId(((Double) map.get("id")).intValue());
        p.setConcour_id(c);
        p.setParticipation_date(new Date((((Double) ((Map<String, Object>) map.get("dateParticipation")).get("timestamp")).longValue() * 1000)));

        //User
        Users u = new Users();
        u.setId((int) (double) ((((Map<String, Object>) map.get("user")).get("id"))));
        u.setNom(((((Map<String, Object>) map.get("user")).get("nom"))).toString());
        u.setEmail(((((Map<String, Object>) map.get("user")).get("email"))).toString());
        u.setRole(((((Map<String, Object>) map.get("user")).get("role"))).toString());
        u.setPhoto(((((Map<String, Object>) map.get("user")).get("photo"))).toString());
        u.setPrenom(((((Map<String, Object>) map.get("user")).get("prenom"))).toString());

System.out.println("verif user "+u);
        p.setUser_id(u);
        //Video
        Video v = new Video();
        v.setId((int) (double) ((((Map<String, Object>) map.get("video")).get("id"))));
        v.setOwner(u);
        v.setPublish_date(new Date((((Double) ((Map<String, Object>) (((Map<String, Object>) map.get("video")).get("publishDate"))).get("timestamp")).longValue() * 1000)));
        v.setTitle((((Map<String, Object>) map.get("video")).get("title")).toString());
        v.setUrl((((Map<String, Object>) map.get("video")).get("url")).toString());
        Object m=(((Map<String, Object>) map.get("video")).get("votes"));
        List<Map<String,Double>> thisisnotok=(List<Map<String,Double>>) m;
        List<Integer> f=new ArrayList();
        
        for (Map<String,Double> entry : thisisnotok) {
            f.add((int)(double)entry.get("id"));
        }
        v.setVotes(f);
        p.setVideo_id(v);

        return p;
    }
 public List<Video> ConcourRanksList(Concour c) {

        String url = Statics.BASE_URL + "/concour/ranks/" + c.getId();
        con.setUrl(url);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    String str = new String(con.getResponseData());
                    rank = new ArrayList<>();
                    JSONParser j = new JSONParser();

                    Map<String, Object> ranksMap = j.parseJSON(new CharArrayReader(str.toCharArray()));

                    List<Map<String, Object>> ranksList = (List<Map<String, Object>>) ranksMap.get("root");
                    if (ranksList != null) {
                        for (Map<String, Object> map : ranksList) {
                            rank.add(parseRanks(map));
                        }
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                con.removeResponseListener(this);
            }

        });

        NetworkManager.getInstance().addToQueueAndWait(con);

        return rank;
    }

    private Video parseRanks(Map<String, Object> map) {
        //video
        Video v = new Video();
        v.setId(((Double) map.get("id")).intValue());

        v.setPublish_date(new Date((((Double) ((Map<String, Object>) map.get("publishDate")).get("timestamp")).longValue() * 1000)));

        //User
        Users u = new Users();
        u.setId((int) (double) ((((Map<String, Object>) map.get("owner")).get("id"))));
        System.out.println(u.getId());
        u.setNom(((((Map<String, Object>) map.get("owner")).get("nom"))).toString());
        u.setPrenom(((((Map<String, Object>) map.get("owner")).get("prenom"))).toString());
        u.setEmail(((((Map<String, Object>) map.get("owner")).get("email"))).toString());
        u.setRole(((((Map<String, Object>) map.get("owner")).get("role"))).toString());
        u.setPhoto(((((Map<String, Object>) map.get("owner")).get("photo"))).toString());
       



        v.setOwner(u);

        v.setTitle(map.get("title").toString());
        v.setUrl((map.get("url")).toString());
        Object m=map.get("votes");
        List<Map<String,Double>> thisisnotok=(List<Map<String,Double>>) m;
        List<Integer> f=new ArrayList();
        
        for (Map<String,Double> entry : thisisnotok) {
            f.add((int)(double)entry.get("id"));
        }
        v.setVotes(f);

        return v;
    }

    public void participate(Video v, Participation cp) {
        Gson gson = new Gson();
        String json = gson.toJson(v);
        String json2 = gson.toJson(cp);
        String url = Statics.BASE_URL + "/concour/participate?video=" + json+ "&participation=" + json2;

        con.setUrl(url);

        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String str = new String(con.getResponseData());
                System.out.println(str);
                Dialog.show("Confirmation", "Your Video has been successfully added", "Ok", null);
                con.removeResponseListener(this);
            }

        });
        NetworkManager.getInstance().addToQueueAndWait(con);

    }
    boolean result = false;

    public boolean alreadyParticipated(int c, int user) {

        String url = Statics.BASE_URL + "/concour/participated?concour=" + c + "&user=" + user;
        System.out.println(url);
        con.setUrl(url);

        con.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {
                String str = new String(con.getResponseData());
                result = Boolean.valueOf(str);
                System.out.println(result);

                con.removeResponseListener(this);
            }

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return result;
    }
    
     public void Vote(int user, int Video) {
      
        String url = Statics.BASE_URL + "/api/vote/?video=" + Video + "&user=" + user;
         System.out.println(url);
        con.setUrl(url);

        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                
                System.out.println("vote");
                con.removeResponseListener(this);
            }

        });
        NetworkManager.getInstance().addToQueueAndWait(con);

    }
     public void unVote(int user, int Video) {
      
        String url = Statics.BASE_URL + "/api/unvote/?video=" + Video + "&user=" + user;
         System.out.println(url);

        con.setUrl(url);

        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                
                 System.out.println("unvote");
                con.removeResponseListener(this);
            }

        });
        NetworkManager.getInstance().addToQueueAndWait(con);

    }
          public List<Video> VideoList() {

        String url = Statics.BASE_URL + "/Homepage/";
        con.setUrl(url);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    String str = new String(con.getResponseData());

                    Videos = new ArrayList<>();
                    JSONParser j = new JSONParser();

                    Map<String, Object> VideosMap = j.parseJSON(new CharArrayReader(str.toCharArray()));

                    List<Map<String, Object>> VideosList = (List<Map<String, Object>>) VideosMap.get("root");
                    if (VideosList != null) {
                        for (Map<String, Object> map : VideosList) {

                            Videos.add(parseRanks(map));
                        }
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                con.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(con);
        return Videos;
    }
     
      public List<Video> HomepageRanks() {

        String url = Statics.BASE_URL + "/Homepage/ranks";
        con.setUrl(url);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    String str = new String(con.getResponseData());
                    rank = new ArrayList<>();
                    JSONParser j = new JSONParser();

                    Map<String, Object> ranksMap = j.parseJSON(new CharArrayReader(str.toCharArray()));

                    List<Map<String, Object>> ranksList = (List<Map<String, Object>>) ranksMap.get("root");
                    if (ranksList != null) {
                        for (Map<String, Object> map : ranksList) {
                            rank.add(parseRanks(map));
                        }
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                con.removeResponseListener(this);
            }

        });

        NetworkManager.getInstance().addToQueueAndWait(con);

        return rank;
    }
      public void DeleteParticipation(Participation p){
       String url = Statics.BASE_URL + "/Participation/Delete/?participation=" + p.getId();

        con.setUrl(url);

        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               
                con.removeResponseListener(this);
            }

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
      
      
      }
      
  public ArrayList<questiontab> parseQuestions(String jsonText){
        try {
            Questions=new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                questiontab ques = new questiontab();
                float id = Float.parseFloat(obj.get("id").toString());
                ques.setId((int)id);
              
                ques.setAnswer(obj.get("answer").toString());
                ques.setQuestions(obj.get("questions").toString());
                Questions.add(ques);
            }
            
            
        } catch (IOException ex) {
            
        }

        return Questions;
    }
    
    public ArrayList<questiontab> getAllQuestions(Quiz q){
        
        String url = Statics.BASE_URL+"/questionQJson/"+q.getId();
        con.setUrl(url);
        con.setPost(false);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Questions = parseQuestions(new String(con.getResponseData()));
                con.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return Questions;
    }
//        public void ajoutScore(Score scr) {
//      ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
//        String Url = "http://localhost:8000/addCompetence?Titre="+.getTitre()+"&Domaine="+c.getDomaine()+""   ;// création de l'URL
//        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion
//
//        con.addResponseListener((e) -> {
//            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
//            System.out.println(str);//Affichage de la réponse serveur sur la console
//
//        });
//        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
//    }
}

  

    

