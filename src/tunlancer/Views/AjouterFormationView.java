
    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunlancer.Views;

import com.codename1.components.InfiniteProgress;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Font;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import java.text.SimpleDateFormat;

import tunlancer.Services.ServiceExperience;
import tunlancer.Models.Experiences;
import tunlancer.Models.Formation;
import tunlancer.Services.ServiceFormation;

//import com.sun.mail.smtp.SMTPTransport;
//import java.util.Date;
////import java.util.Properties;
//import javax.mail.MessagingException;
//import javax.mail.Session;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;

/**
 *
 * @author asus
 */
public class AjouterFormationView{

    Form f;
    TextField titre;
    TextField specialite;
    TextField description;
      private Form parentForm;
    private Experiences member;

    Picker datedeb;
      Picker periode;
   
    Button btnajout;

    private boolean selected;

//    Picker body;
    public AjouterFormationView(int memberId) {
        f = new Form(" Ajout Fomration", BoxLayout.y());
         f.getToolbar().addCommandToLeftBar("Back", tunlancer.MyApplication.theme.getImage("back-command.png"), (e) -> {
        parentForm.showBack();
        });
       // i.dispose();
       titre = new TextField();
       titre.setHint("Titre");
        Label labelname = new Label();
        labelname.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM));
        labelname.getAllStyles().setFgColor(0xFF0000);
       specialite= new TextField();
        specialite.setHint("Spcialite");
        Label labelLast = new Label();
        labelLast.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM));
        labelLast.getAllStyles().setFgColor(0xFF0000);
        TextField email = new TextField("", "Description", 30, TextField.EMAILADDR);
        Label labelemail = new Label();
        labelemail.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM));
        labelemail.getAllStyles().setFgColor(0xFF0000);
        
   
      //  Container cntBirth = new Container(BoxLayout.x());
        periode = new Picker();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(this.periode.getDate());
        Label birthText = new Label("Birth Date :");
        //cntBirth.add(birthText).add(datedeb);
        birthText.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
        birthText.getAllStyles().setFgColor(0x09345E);
      //  Container datefinn = new Container(BoxLayout.x());
      
     
       
        btnajout = new Button("Continuer");
        f.add(titre);
        f.add(labelname);
         f.add(periode);
        f.add(specialite);
        f.add(labelLast);
       
        f.add(labelemail);
         f.add(email);
       
       
      
       
        f.add(btnajout);


        btnajout.addActionListener((l) -> {
         

         


            ServiceFormation ins = new    ServiceFormation();
    Formation t = new Formation( 0,titre.getText(), periode.getText(), specialite.getText(),email.getText());            
        ins.ajoutFormation(t);
          Dialog.show("Fomration ajoutée", "Votre formation est ajoutée avec succés", "OK", "Cancel");
           
ProfileOther a=new ProfileOther(parentForm, memberId);
        a.getF().show();
        });  
          //  Dialog.show("Vous etes maintenant inscris à smartstart","Votre inscription est effectuée avec succés", "OK", "Cancel");
            // Login1 a=new Login1(Resources.getGlobalResources());
       // a.getSmartstart().show();

             
            }
    
    
                
                
    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

}


