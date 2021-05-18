/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunlancer.Services;

import com.codename1.io.ConnectionRequest;
import java.util.ArrayList;
import tunlancer.Models.Annonce;

/**
 *
 * @author siwar
 */
public class ServiceAnnonce {
    public static ServiceAnnonce instance = null;
     public ArrayList<Annonce> tasks;
    
   
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceAnnonce() {
         req = new ConnectionRequest();
    }

    public static  ServiceAnnonce getInstance() {
        if (instance == null) {
            instance = new ServiceAnnonce() ;
        }
        return instance;
    }
    
   /* String sDate1 = map.get("dateDebut").toString();
                try {
                    Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(sDate1);
                    c.setDateDebut(date1);
                } catch (ParseException ex) {
                    System.out.println(ex.getMessage());
                }*/
    
}
