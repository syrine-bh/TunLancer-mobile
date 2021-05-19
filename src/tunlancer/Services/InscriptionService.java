/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunlancer.Services;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import tunlancer.Models.Users;


/**
 *
 * @author asus
 */
public class InscriptionService {
     private static InscriptionService instance;
      public static InscriptionService getInstance(){
        if (instance == null)
            instance = new InscriptionService();
        return instance; 
}
        public void Inscription(Users m) {
        ConnectionRequest con = new ConnectionRequest();
       
        
       String Url = "http://localhost:8000/inscription?Nom=" +m.getNom() +"&Prenom=" +m.getPrenom()+"&Tel="+m.getTel()+"&Email="+m.getEmail()+"&Password="+m.getPassword()+"&Pays="+m.getPays()+"&Age="+m.getAge()+"&Sexe="+m.getSexe()+"&Role="+m.getRole();
        con.setUrl(Url);
       // con.setUrl("http://localhost:8000/inscription");
       // con.addArgument("Nom", m.getNom());
       // con.addArgument("Prenom", m.getPrenom());
       // con.addArgument("Tel", String.valueOf(m.getTel()));
       // con.addArgument("Email", m.getEmail());
      //  con.addArgument("Password", m.getPassword());
       // con.addArgument("Pays", m.getPays());
       // con.addArgument("Age", String.valueOf(m.getAge()));
      //  con.addArgument("Sexe", m.getSexe().toString());
         
       System.out.println("register sucess");

        con.addResponseListener((l) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }

    
}
