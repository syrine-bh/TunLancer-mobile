/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunlancer.Views;

import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Font;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.validation.RegexConstraint;
import com.codename1.ui.validation.Validator;
import tunlancer.Models.Enumerations;
import tunlancer.Models.Users;
import tunlancer.Services.InscriptionService;
import tunlancer.services.ServiceUser;

/**
 *
 * @author asus
 */
public class EditProfil {
    


    Form f;
    Picker role;
    TextField id;
    TextField tfNom;
    TextField tfPrenom;
    TextField tfTel;
    TextField email;
    TextField tfpassword;
    TextField tfPays;
    TextField tfAge;
    TextField tfSexe;
    RadioButton maleRadio;
    RadioButton femaleRadio;
    ButtonGroup group;
     private RadioButton maleField;
    private RadioButton femaleField;
    Button btnajout;
    public static Users u1;
    private ComboBox<Enumerations.Role> roleField;
  private  String[] tabs = {"Freelancer","Entreprise"};
  // private  ComboBox<Map<String, Object>> combo = new ComboBox<> ;
  //  private ComboBox<Enumerations.BodyType> bodyField;
            
          
    private boolean selected;

//    Picker body;
    public EditProfil(Form form, Users member)  {
        f = new Form("Edit Informations Personnels", BoxLayout.y());
        tfNom = new TextField();
        tfNom.setHint("Nom");
        Label Nom = new Label();
        Nom.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM));
        Nom.getAllStyles().setFgColor(0xFF0000);
        tfPrenom = new TextField();
        tfPrenom.setHint("Prenom");
        Label Prenom = new Label();
        Prenom.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM));
        Prenom.getAllStyles().setFgColor(0xFF0000);
        email = new TextField("", "E-Mail", 30, TextField.EMAILADDR);
        Label labelemail = new Label();
        labelemail.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM));
        labelemail.getAllStyles().setFgColor(0xFF0000);
        tfpassword = new TextField("", "Password", 20, TextField.PASSWORD);
        Label labelPassword = new Label();
        labelPassword.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM));
        labelPassword.getAllStyles().setFgColor(0xFF0000);
         tfTel= new TextField();
         tfTel.setHint("Tel");
        Label Tel = new Label();
        Tel.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM));
        Tel.getAllStyles().setFgColor(0xFF0000);
        tfPays = new TextField();
        tfPays.setHint("Pays");
        Label Pays = new Label();
        Pays.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM));
        Pays.getAllStyles().setFgColor(0xFF0000);
        tfAge = new TextField();
        tfAge.setHint("Age");
        Label Age = new Label();
       Age.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM));
        Age.getAllStyles().setFgColor(0xFF0000);
        role = new Picker();
        String[] Roles = {"Freelancer", "Entreprise"};
        Label Bodytext = new Label("Role :");
        Bodytext.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
        Bodytext.getAllStyles().setFgColor(0x09345E);
       role.setStrings(Roles);
        Container genderContainer = new Container(BoxLayout.x());
        Label Gender = new Label("Sexe :");
        Gender.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
        Gender.getAllStyles().setFgColor(0x09345E);
        Label male = new Label("Homme");
        male.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
        Label female = new Label("Femme");
        female.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
        maleRadio = new RadioButton();
        femaleRadio = new RadioButton();
       // new ButtonGroup(maleField, femaleField);
       // if(u1.exe()) maleField.setSelected(true); else femaleField.setSelected(true);
        Label Vgender = new Label();
        Vgender.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM));
        Vgender.getAllStyles().setFgColor(0xFF0000);
        genderContainer.add(Gender).add(male).add(maleRadio).add(female).add(femaleRadio);
        group = new ButtonGroup(maleRadio, femaleRadio);
       // roleField = new ComboBox<>(Enumerations.Role.values());
       // roleField.setSelectedItem(Users.getRole());
        //ComboBox roles = new ComboBox(tabs);
         id = new TextField();
         id.setHint("Id");
        
      //  Component cntBody;
       // cntBody.add(Bodytext).add(body);
       
        btnajout = new Button("Continuer");
        f.add(tfNom);
        f.add(Nom);
        f.add(tfPrenom);
        f.add(Prenom);
        f.add(tfTel);
        f.add(Tel);
        f.add(email);
        f.add(labelemail);
        f.add(tfpassword);
        f.add(labelPassword);
        f.add(tfPays);
        f.add(Pays); 
        f.add(tfAge);
        f.add(role);
        f.add(Age);
        f.add(id);
        f.add(genderContainer);
        f.add(Vgender);
        
        maleRadio.isSelected();
       //  f.add(cntBody);
        
       // f.add(cntBirth);
       
      
       
        f.add(btnajout);

       Validator v = new Validator();
        v.addConstraint(email, RegexConstraint.validEmail("Unvalid email!"));
       // v.addSubmitButtons(btnajout);

        btnajout.addActionListener((l) -> {
            boolean valid = true;
            if (tfNom.getText().equals("")) {
                Nom.setText("Field is empty !");
                Nom.setVisible(true);
                valid = false;
            } else {
                Nom.setText("");

            }
            if (tfPrenom.getText().equals("")) {
                Prenom.setText("Field is empty !");
                Prenom.setVisible(true);
                valid = false;
            } else {
                Prenom.setText("");

            }
            if (email.getText().equals("")) {
                labelemail.setText("Field is empty !");
                labelemail.setVisible(true);
                valid = false;
            } else {
                labelemail.setText("");

            }
            if (!email.getText().equals("") && !email.getText().contains("@") && !email.getText().contains(".")) {
                labelemail.setText("Mail not valid !");
                labelemail.setVisible(true);
                valid = false;
            } else {
                 labelemail.setText("");
            }
           /* if (!email.getText().equals("")) {
                labelemailV.setText("Mail not valid !");
                labelemailV.setVisible(true);
                valid = false;
            } else {
                labelemailV.setText("");

            }*/

            if (tfTel.getText().equals("")) {
                Tel.setText("Field is empty !");
               Tel.setVisible(true);
                valid = false;
            } else {
              Tel.setText("");

            }
            if(tfTel.getText().length() != 8){
            Dialog.show("Error", "Phone number must contain exactly 8 digits!", "Ok", null);
            
            }   
            if (tfpassword.getText().equals("")) {
                labelPassword.setText("Field is empty !");
                labelPassword.setVisible(true);
                valid = false;
            } else {
               labelPassword.setText("");

            }
         

            
            
            if (tfPays.getText().equals("")) {
                Pays.setText("Field is empty !");
                Pays.setVisible(true);
                valid = false;
            } else {
                Pays.setText("");
            }

            if (tfAge.getText().equals("")) {
                Age.setText("Field is empty !");
                Age.setVisible(true);
                valid = false;
            }

          
            if (!maleRadio.isSelected() && !femaleRadio.isSelected()) {
                Vgender.setText("You should select a choice !");
                Vgender.setVisible(true);
                valid = false;
            } else {
                Vgender.setText("");
            }
          
            if (!valid) {
                return;
            }
           ServiceUser ins = new ServiceUser();
            Users use = new Users(Integer.parseInt(id.getText()),tfNom.getText(), tfPrenom.getText(), Integer.parseInt(tfTel.getText()), tfpassword.getText(), email.getText(), tfPays.getText(),group.isSelected(),role.getText(),Integer.valueOf(tfAge.getText()));
             ins.editUsers(use);
            Dialog.show("Modification avec succes","Votre Modification est effectuée avec succés", "OK", "Cancel");
           // SMS s=new SMS();
           // s.sendSms();
         //  System.out.println("Message Reçu");
           
             //Login1 a=new Login1(Resources.getGlobalResources());
       // a.getSmartstart().show();
      //     Mail sm = new Mail(u1.getEmail(), " Confirmation d'inscription ", " Bonjour " + u1.getFirstname() + "Felicitations! Vous etes maintenant inscrit à smartstart");
      //                     try {
//              
//                Properties props = new Properties();
//                props.put("mail.transport.protocol", "smtp");
//                props.put("mail.smtps.host", "smtp.gmail.com");
//                props.put("mail.smtps.auth", "true");
//                Session session = Session.getDefaultInstance(null, null);
//                
//                MimeMessage msg = new MimeMessage(session);
//                
//                msg.setFrom(new InternetAddress("SocialPro <my_email@myDomain.com>"));
//                msg.setRecipients(Message.RecipientType.TO, email.getText());
//                msg.setSubject("Bienvenue sur SocialPro ");
//                msg.setSentDate(new Date(System.currentTimeMillis()));
//                
//                String txt = "Bonjour,\n "+tpseudo.getText()+"\n Cordialement";
//                
//                msg.setText(txt);
//                SMTPTransport st = (SMTPTransport)session.getTransport("smtps");
//                st.connect("smtp.gmail.com","nihelhaddad5@gmail.com","nihelhaddad23");
//                st.sendMessage(msg, msg.getAllRecipients());
//                System.out.println("ServerResponse : " + st.getLastServerResponse());
//                 
//            } catch (MessagingException ex) {
//            }
             });
            }           
                
    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

   

  

}
