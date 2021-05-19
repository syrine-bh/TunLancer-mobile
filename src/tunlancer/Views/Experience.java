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
public class Experience{

    Form f;
    TextField poste;
    TextField titre;
    TextField tpseudo;
    TextField tphone;
      private Form parentForm;
    private Experiences member;

    Picker datedeb;
      Picker datefin;
   
    Button btnajout;

    private boolean selected;

//    Picker body;
    public Experience() {
        f = new Form(" Ajout Experience", BoxLayout.y());
         f.getToolbar().addCommandToLeftBar("Back", tunlancer.MyApplication.theme.getImage("back-command.png"), (e) -> {
        parentForm.showBack();
        });
       // i.dispose();
       poste = new TextField();
       poste.setHint("Firstname");
        Label labelname = new Label();
        labelname.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM));
        labelname.getAllStyles().setFgColor(0xFF0000);
       titre = new TextField();
        titre.setHint("Lastname");
        Label labelLast = new Label();
        labelLast.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM));
        labelLast.getAllStyles().setFgColor(0xFF0000);
        TextField email = new TextField("", "E-Mail", 30, TextField.EMAILADDR);
        Label labelemail = new Label();
        labelemail.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM));
        labelemail.getAllStyles().setFgColor(0xFF0000);
        
   
      //  Container cntBirth = new Container(BoxLayout.x());
        datedeb = new Picker();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(this.datedeb.getDate());
        Label birthText = new Label("Birth Date :");
        //cntBirth.add(birthText).add(datedeb);
        birthText.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
        birthText.getAllStyles().setFgColor(0x09345E);
      //  Container datefinn = new Container(BoxLayout.x());
        datefin = new Picker();
        SimpleDateFormat sdff = new SimpleDateFormat("yyyy-MM-dd");
        String date1 = sdff.format(this.datefin.getDate());
        Label birthTextt = new Label("Birth Date :");
    // datefinn.add(birthText).add(datefin);
     
       
        btnajout = new Button("Continuer");
        f.add(poste);
        f.add(labelname);
        f.add(titre);
        f.add(labelLast);
        f.add(datedeb);
        f.add(labelemail);
        
       f.add(datefin);
       
      
       
        f.add(btnajout);


        btnajout.addActionListener((l) -> {
         

         


            ServiceExperience ins = new    ServiceExperience();
    Experiences t = new Experiences( 0,poste.getText(), titre.getText(), datedeb.getText(),datefin.getText());            
        ins.ajoutExperience(t);
          Dialog.show("Experience ajouté", "Votre experience est ajouté avec succés", "OK", "Cancel");
            int memberId = 0;
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
