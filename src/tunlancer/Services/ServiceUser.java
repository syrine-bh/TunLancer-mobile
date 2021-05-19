/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunlancer.services;


import com.codename1.db.Cursor;
import com.codename1.db.Database;
import com.codename1.db.Row;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.util.Resources;
import tunlancer.Models.Users;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;



public class ServiceUser {
    private Users user;
    TextField email;
    TextField password;
    String json;
    private static ServiceUser instance;
    
    public ServiceUser(){}
    
    public static ServiceUser getInstance(){
        if(instance == null)
            instance = new ServiceUser();
        return instance;
    }
    
    public void delete(int id) {
	ConnectionRequest con = new ConnectionRequest();
        String url = "http://localhost:8000/deleteuser?id="+id;
        con.setUrl(url);
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
 
    public Users getUsers(int id){
        ConnectionRequest con = new ConnectionRequest();
        String url = "http://localhost:8000/detailuser?id="+id;
        con.setUrl(url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            user = parseUser(str);
        });
       /* con.addExceptionListener((ev) -> {
            member = null;
        });*/
        NetworkManager.getInstance().addToQueueAndWait(con);
        return user;
    }
    
    public Users editUsers(Users m){
        ConnectionRequest con = new ConnectionRequest();
        String url = "http://localhost:8000/updateuser?id="+user.getId();
        con.setUrl(url);
        con.setPost(true);
        
        con.addArgument("Nom", m.getNom());
        con.addArgument("Prenom", m.getPrenom());
        con.addArgument("Tel", String.valueOf(m.getTel()));
        con.addArgument("Email", m.getEmail());
        con.addArgument("Password", m.getPassword());
        con.addArgument("Pays", m.getPays());
        con.addArgument("Role", m.getRole());
        con.addArgument("Photo", m.getPhoto());
        con.addArgument("Bibliography", m.getBibliography());
        con.addArgument("Age", String.valueOf(m.getAge()));
      //  con.addArgument("Sexe", m.getSexe());
     
       
       
        
       
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            user = getUsers(m.getId());
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return user;
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
               // m.setTel((Integer)userMap.get("Tel"));
                m.setEmail((String)userMap.get("Email"));
                m.setpassword((String)userMap.get("password"));
                m.setPays((String)userMap.get("Pays"));
                m.setRole((String)userMap.get("Role"));
               //  m.setAge((Integer)userMap.get("Age"));
               // m.setDescription((String)userMap.get("description"));
                m.setTel(((Double)userMap.get("Tel")).intValue());
                m.setAge(((Double)userMap.get("Age")).intValue());
               
               //  m.setPath((String)userMap.get("path"));
                
              
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return m;
    }
    public ArrayList<Users> getAllUsers() {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost:8000/displayuser";
        con.setUrl(Url);
        ArrayList<Users> mapEvent = new ArrayList<>();
        con.addResponseListener((e) -> {
            String jsonRes = new String(con.getResponseData());
            try {
                JSONParser j = new JSONParser();
                Map<String, Object> events = j.parseJSON(new CharArrayReader(jsonRes.toCharArray()));
                List<Map<String, Object>> list = (List<Map<String, Object>>) events.get("root");
                for (Map<String, Object> obj : list) {
                    Users ev= new Users();
                    float id = Float.parseFloat(obj.get("id").toString());
                    float Tel = Float.parseFloat(obj.get("tel").toString());
                    float Age = Float.parseFloat(obj.get("Age").toString());
                    ev.setId((int) id);
                    ev.setNom(obj.get("nom").toString());
                    ev.setPrenom(obj.get("prenom").toString());
                    ev.setTel((int)Tel);
                    ev.setEmail(obj.get("email").toString());
                    ev.setpassword(obj.get("pasword").toString());
                    ev.setRole(obj.get("role").toString());
                    ev.setPays(obj.get("pays").toString());
                //    ev.setSexe(obj.get("sexe").toString());
                    ev.setAge((int)Age);
                    mapEvent.add(ev);
              
                }

            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(con);

        return mapEvent;
    }
    public String getPasswordByEmail(String email,Resources res)
    {
     
    ConnectionRequest con = new ConnectionRequest();
    String Url = "http://localhost:8000/user/getPasswordByEmail?Email="+email;
    con.setUrl(Url);
    con.addResponseListener(a -> {
    JSONParser j=new JSONParser();
      json=new String(con.getResponseData())+"";
    try {
       
        
            System.out.println("data=="+json);
            Map <String,Object> password = j.parseJSON(new CharArrayReader(json.toCharArray()));
        // if(Userr.size()>0)
        //  new NewsfeedForm(res).show());
            
    }       catch (IOException ex) {
                ex.printStackTrace();
            }
    }
    );
     NetworkManager.getInstance().addToQueueAndWait(con);
        return json;
    
    }
    public void login (TextField email ,TextField password) {
        ConnectionRequest con = new ConnectionRequest();
    String Url = "http://localhost:8000/Loginnnn?Email="+email.getText()+"&password="+password.getText();
    con.setUrl(Url);
    con.addResponseListener(a -> {
    JSONParser j=new JSONParser();
    String json=new String(con.getResponseData())+"";
    try {
        if(json.equals("failed")){
      Dialog.show("Authentification echoué", "Email ou password Incorrect", "OK", "Cancel");
        }
        else
        {
            System.out.println("data=="+json);
            Map <String,Object> Userr = j.parseJSON(new CharArrayReader(json.toCharArray()));}
       // if(User.size()>0)
        //  / new NewsfeedForm(res).show());
            
    }       catch (IOException ex) {
                ex.printStackTrace();
            }
    }
    );
    
    }}
 


