/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunlancer.Views;


import com.codename1.components.Accordion;
import com.codename1.components.ImageViewer;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.SpanLabel;
import com.codename1.db.Cursor;
import com.codename1.db.Database;
import com.codename1.db.Row;
import com.codename1.facebook.Photo;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.RoundBorder;
import static com.codename1.ui.plaf.Style.BACKGROUND_NONE;
import com.codename1.ui.util.Resources;

import tunlancer.Models.Users;



import tunlancer.Models.Experiences;
import tunlancer.Models.Formation;
//import tunlancer.Models.Photo;
import tunlancer.Models.Competence;
//import tunlancer.Models.MembreService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import tunlancer.MyApplication;

//import tunlancer.Services.ServiceExperience;
import tunlancer.Services.ServiceFormation;
//import tunlancer.services.PhotoService;
//import tunlancer.Services.ServiceCompetence;
import static tunlancer.MyApplication.MemberId;
import static tunlancer.MyApplication.firstForm;
import static tunlancer.MyApplication.theme;
import tunlancer.Services.ServiceCompetence;
import tunlancer.Services.ServiceExperience;

public final class ProfileOther {
    
   TextField supp;
    private Form form;
    private Form parentForm;
    private Users member;
    private Experiences ex;
     SpanLabel lb;
       ServiceCompetence cs3 = new ServiceCompetence();
      ArrayList<Competence> lstC3 = cs3.getAllCompetences();
      ServiceExperience cs = new ServiceExperience();
      ArrayList<Experiences> lstC = cs.getAllExperiences();
        ServiceFormation cs2 = new ServiceFormation();
       ArrayList<Formation> lstC2 = cs2.getAllFormations();
      
    
    private static ProfileOther instance;
    
    public static void update(){
        if(instance != null)
            instance.updateView();
    }
      public ProfileOther() {
          
             form.add(ProjetInfo());
           form.add(ExperienceInfo());
             form.add(FormationInfo());

           
    }
  public void show() {
 
    }
 
    private void updateView(){
         Dialog i = new InfiniteProgress().showInifiniteBlocking();
      // member = ServiceUser.getInstance().getUsers(member.getId());
        
        buildContainer();
        form.revalidate();
        i.dispose();
    }
    
    public ProfileOther(Form parentForm, int memberId){
        Dialog ip = new InfiniteProgress().showInifiniteBlocking();
        instance = this;
        this.parentForm = parentForm;
        form = new Form("Profil", new BorderLayout());
         form.getToolbar().addCommandToOverflowMenu("Profil", MyApplication.theme.getImage("edit_black.png"), (e) -> {
           (new EditProfil(form,  member)).getF().show();
        });
         Image icon = theme.getImage("icon.png");
            Container topBar = BorderLayout.east(new Label(icon));
            Toolbar tb = form.getToolbar();
        topBar.add(BorderLayout.SOUTH, new Label("Profil", "SidemenuTagline"));
        topBar.setUIID("SideCommand");
       tb.addComponentToSideMenu(topBar);
        tb.addMaterialCommandToSideMenu("Profil", FontImage.MATERIAL_ACCOUNT_CIRCLE, e -> {
    //       Form profileForm = (new ProfileView());
                     new ProfileView(parentForm, MemberId).getContainer().show();
          //  profileForm.show();
        });
        
        tb.addMaterialCommandToSideMenu("Concours", FontImage.MATERIAL_LIST, e -> {
                       new PublicationsForm(theme).show();

        });
       
       tb.addMaterialCommandToSideMenu("Publications", FontImage.MATERIAL_LIST, e -> {
                       

        });
        tb.addMaterialCommandToSideMenu("Forum", FontImage.MATERIAL_LIST, e -> {
                      

        });
         tb.addMaterialCommandToSideMenu("Recrutement", FontImage.MATERIAL_LIST, e -> {
                       

        });
          tb.addMaterialCommandToSideMenu("Offre", FontImage.MATERIAL_LIST, e -> {
                       

        });
           tb.addMaterialCommandToSideMenu("Quiz", FontImage.MATERIAL_LIST, e -> {
                       // new MyFront().getF().show();

        });
        tb.addMaterialCommandToSideMenu("Logout", FontImage.MATERIAL_EXIT_TO_APP, e -> {});

        
//       this.member = MembreService.getInstance().getFosuser(memberId);
     //  this.member = MembreService.getInstance().getFosuser(memberId);
       
       /* if(member == null){
         getMemberFromLocal();
        }else{
     populateBd();
        }*/
        buildContainer();
        ip.dispose();
    }
    
private void populateBd(){
        
              try {
            Database database = Database.openOrCreate("tunlancer_bd");
            database.execute("create table if not exists Users(id INTEGER, Nom text, Prenom text,"
                    + "Tel int, email text , password text,"
                    + "pays text, role text, photo text, bibiliography text, is_enabled int,super_admin int ,age int,"
                    + "sexe string);");
            String deleteQuery = "delete from Users";
            database.execute(deleteQuery);
            String insertQuery = "insert into Users values("+member.getId()+", '"+member.getNom()+"', '"+member.getPrenom()
                    +"', '"+member.getTel()+"', "+(member.getEmail()+", '"+"', "+member.getPassword()
                    +", "+member.getPays()+", "+member.getRole()+", "+member.getPhoto()+", "+member.getBibliography()
                    +", "+member.getis_Enabled()+", "+member.getsuper_admin()+", "+(member.getAge())+", "+(member.getSexe())
                    )+"')";
            database.execute(insertQuery);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    private void getMemberFromLocal(){
        try {
                       Database database = Database.openOrCreate("tunlancer_bd");
      Cursor c = database.executeQuery("select * from Users");
            c.next();
            Row r = c.getRow();
            member = new Users();
            member.setId(r.getInteger(0));
          member.setNom(r.getString(1));
           member.setPrenom(r.getString(2));
              member.setTel(r.getInteger(3));
             member.setEmail(r.getString(4));
           member.setPays(r.getString(5));
            member.setRole(r.getString(6));
            member.setRole(r.getString(9));

           
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void buildContainer(){
        Container c = new Container(BoxLayout.y());
        c.getAllStyles().setMarginBottom(20);
        
        //Cover Picture
        Label coverImg;
        Container coverContainer = new Container();
        Button bCover = new Button();
        coverContainer.setLeadComponent(bCover);
   
           Image i = MyApplication.theme.getImage("cover.jpg");
           i = i.scaledHeight(350);
          coverImg = new Label(i);
   //     }
        coverImg.getAllStyles().setMarginLeft(0);
        coverContainer.add(coverImg);
        
        //About
        Container aboutContainer = new Container(BoxLayout.y());
        aboutContainer.getAllStyles().setMarginLeft(40);
        
       Label aboutLabel = new Label("A propos");
       aboutLabel.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
        
    SpanLabel aboutSpan = new SpanLabel("A talented professional with an academic background in IT");
      aboutSpan.getAllStyles().setMarginLeft(20);
      aboutSpan.getTextAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL));
        
          EncodedImage contact;
          EncodedImage phone;

         try {
             contact = EncodedImage.create("/mail.png");
              ImageViewer iv2=new ImageViewer(contact);
               
              // phone = EncodedImage.create("/back.png");
              //ImageViewer sms=new ImageViewer(phone);
           iv2.addPointerReleasedListener(e2 -> 
{
         new ContactView(parentForm).getF().show();
        });     
        aboutContainer.add(aboutLabel);
        aboutContainer.add(aboutSpan);
        Button btn=new Button("Contacter");
        Label l1=new Label("                                              ");
        Label l2=new Label(" ");
        c.add(coverContainer);
        c.add(buildTopProfileInfo());
        c.add(BoxLayout.encloseX(l1,iv2,l2));
        c.add(buildProfileInfo());
      c.add(aboutContainer);
       c.add(ProjetInfo());
        c.add(ExperienceInfo());
        c.add(FormationInfo());
       

         } catch (IOException ex) {
       System.out.println(ex.getMessage());
          }  
        
        c.setScrollableY(true);
        form.add(BorderLayout.CENTER, c);
    }
    
    public Form getContainer(){
        return this.form;
    }
    
    private Container buildProfileInfo(){
        Container c = new Container(BoxLayout.y());
        c.getAllStyles().setMarginLeft(40);
        c.getAllStyles().setPaddingTop(20);
        
        Label infoLabel = new Label("A propos");
        infoLabel.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
        
        Container mainContainer = new Container(BoxLayout.y());
        mainContainer.getAllStyles().setMarginLeft(30);
     //   mainContainer.add(buildPairLabel("Sex", member.isGender()?"Homme":"Femme"));
       // mainContainer.add(buildPairLabel("Date de naissance", (new SimpleDateFormat("dd MMMM, yyy").format(member.getBirth_date()))));
       
        
       // c.add(infoLabel);
        //c.add(mainContainer);
        return c;
    }
       private Container ExperienceInfo() {
           

        // ImageViewer iv=new ImageViewer(enc);
         
        Container c = new Container(BoxLayout.y());
        c.getAllStyles().setMarginLeft(40);
        c.getAllStyles().setPaddingTop(20);
         Image i = MyApplication.theme.getImage("experience1.png");
  EncodedImage removef;
         EncodedImage editf;
         EncodedImage addf;
          try {
              removef = EncodedImage.create("/delete_photo.png");
               ImageViewer iv2=new ImageViewer(removef);
               
                 editf = EncodedImage.create("/edit_black.png");
               ImageViewer ive=new ImageViewer(editf);
               
                 addf = EncodedImage.create("/add_photo.png");
               ImageViewer iva=new ImageViewer(addf);       
       
                 
                  
      
       // EncodedImage enc= EncodedImage.create("/delete_photo.png");
 // ImageViewer iv=new ImageViewer(); 
        

   
         for (Experiences c2 : lstC) {
     Label infoLabel = new Label("Experiences ");
        infoLabel.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
        
        Container mainContainer = new Container(BoxLayout.y());
        mainContainer.getAllStyles().setMarginLeft(30);
           Container bt = new Container(BoxLayout.x());
        bt.setX(1);
        bt.getAllStyles().setMarginLeft(170);
        infoLabel.getAllStyles().setMarginLeft(30);
        infoLabel.getAllStyles().setMarginRight(30);
        infoLabel.getAllStyles().setBorder(Border.createEmpty());
       infoLabel.getAllStyles().setBackgroundType(BACKGROUND_NONE);
       infoLabel.getAllStyles().setBgTransparency(255);
        infoLabel.getAllStyles().setBgColor(0x99CCCCCC);
        mainContainer.add(buildPairLabel("Poste:", c2.getPoste()));
        mainContainer.add(buildPairLabel("Societe:", c2.getSociete()));
       // mainContainer.add(buildPairLabel("Datedeb:", c2.getDatedeb()));
       //  mainContainer.add(buildPairLabel("DateFin:", c2.getDatefin()));
        mainContainer.add(buildPairLabel("       ","             "));
          
      
       
                  int memberId = 0;
         //siv.setImage(enc);
         //Button btn =new Button("edit");
       iv2.addPointerReleasedListener(e -> {
		ServiceExperience.getInstance().delete(c2.getId());
		Profile a=new Profile(parentForm, memberId);
        a.getF().show();
      
                 });
    ive.addPointerReleasedListener(e2 -> 
{
         new EditExperience(form, c2).getF().show();
        });     
    
      iva.addPointerReleasedListener(e3 -> {
     new Experience().getF().show();		
                 });
        
        bt.add(iva);
        bt.add(ive);
        bt.add(iv2);
        bt.getAllStyles().setMarginLeft(1000);    
      
  c.add(infoLabel);
 //c.addComponent(bt2);
 c.add(bt);
 c.add(mainContainer);}
        

        
        
            } catch (IOException ex) {
              System.out.println(ex.getMessage());
            
            }
        
//    form.add(c);
      return c;
    }
    ////////////////////////////////formation//////////////////////
        private Container FormationInfo() {
           

        // ImageViewer iv=new ImageViewer(enc);
         
        Container c = new Container(BoxLayout.y());
        c.getAllStyles().setMarginLeft(40);
        c.getAllStyles().setPaddingTop(20);
         
           
   
         
                 
                  
      
       // EncodedImage enc= EncodedImage.create("/delete_photo.png");
 // ImageViewer iv=new ImageViewer(); 
     
         Image i = MyApplication.theme.getImage("formation2.png");
ImageViewer iv=new ImageViewer(); 
              Image i2 = MyApplication.theme.getImage("projet2.png");
      
          EncodedImage removef;
         EncodedImage editf;
         EncodedImage addf;
          try {
              removef = EncodedImage.create("/delete_photo.png");
               ImageViewer iv2=new ImageViewer(removef);
               
                 editf = EncodedImage.create("/edit_black.png");
               ImageViewer ive=new ImageViewer(editf);
               
                 addf = EncodedImage.create("/add_photo.png");
               ImageViewer iva=new ImageViewer(addf);
            
              
      for (Formation c3 : lstC2) {
      Label infoLabel = new Label("Formations ");
       infoLabel.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
         infoLabel.getAllStyles().setMarginLeft(30);
        infoLabel.getAllStyles().setMarginRight(30);
        infoLabel.getAllStyles().setBorder(Border.createEmpty());
       infoLabel.getAllStyles().setBackgroundType(BACKGROUND_NONE);
       infoLabel.getAllStyles().setBgTransparency(255);
       infoLabel.getAllStyles().setBgColor(0x99CCCCCC);
        Container mainContainer = new Container(BoxLayout.y());
        mainContainer.getAllStyles().setMarginLeft(30);
        mainContainer.add(buildPairLabel("Titre:", c3.getTitre()));
       // mainContainer.add(buildPairLabel("Periode:", c3.getPeriode()));
       mainContainer.add(buildPairLabel("Specialité:", c3.getSpecialite()));
         mainContainer.add(buildPairLabel("       ","             "));
            //siv.setImage(enc); 
            c.add(infoLabel);
            
        Container bt = new Container(BoxLayout.x());
       // bt.setX(1);
         bt.add(iva);
          bt.add(ive);
       
        bt.add(iv2);
        bt.getAllStyles().setMarginLeft(1000);  
                  int memberId = 0;
         iv2.addPointerReleasedListener(e -> {
		ServiceFormation.getInstance().delete(c3.getId());
		ProfileView a=new ProfileView(parentForm, memberId);
        a.getF().show();
      
                 });
    ive.addPointerReleasedListener(e2 -> 
{
      new ModifierFormView(form, c3).getF().show();        });     
    
      iva.addPointerReleasedListener(e3 -> {
     new AjouterFormationView(memberId).getF().show();		
                 });
       c.add(bt);
 //c.addComponent(btn);
 //c.add(iv);
 c.add(mainContainer);}
       
            } catch (IOException ex) {
              System.out.println(ex.getMessage());
        

}
        
      return c;
    }
    
     private Container ProjetInfo() {
           

        // ImageViewer iv=new ImageViewer(enc);
         
        Container c = new Container(BoxLayout.y());
        c.getAllStyles().setMarginLeft(40);
        c.getAllStyles().setPaddingTop(20);
         
            ImageViewer iv=new ImageViewer(); 
              Image i2 = MyApplication.theme.getImage("projet2.png");
      
          EncodedImage removef;
         EncodedImage editf;
         EncodedImage addf;
          try {
              removef = EncodedImage.create("/delete_photo.png");
               ImageViewer iv2=new ImageViewer(removef);
               
                 editf = EncodedImage.create("/edit_black.png");
               ImageViewer ive=new ImageViewer(editf);
               
                 addf = EncodedImage.create("/add_photo.png");
               ImageViewer iva=new ImageViewer(addf);
       for (Competence c3 : lstC3) {
        
      Label infoLabel = new Label("Competences  ");
      infoLabel.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
        infoLabel.getAllStyles().setMarginLeft(30);
        infoLabel.getAllStyles().setMarginRight(30);
        infoLabel.getAllStyles().setBorder(Border.createEmpty());
       infoLabel.getAllStyles().setBackgroundType(BACKGROUND_NONE);
       infoLabel.getAllStyles().setBgTransparency(255);
       infoLabel.getAllStyles().setBgColor(0x99CCCCCC); 
        Container mainContainer = new Container(BoxLayout.y());
        mainContainer.getAllStyles().setMarginLeft(30);
        mainContainer.add(buildPairLabel("Titre:", c3.getTitre()));
        mainContainer.add(buildPairLabel("Domaine:", c3.getDomaine()));
   
         mainContainer.add(buildPairLabel("       ","             "));
            c.add(infoLabel);
            
        Container bt = new Container(BoxLayout.x());
        bt.setX(1);
         bt.add(iva);
          bt.add(ive);
       
        bt.add(iv2);
        bt.getAllStyles().setMarginLeft(1000);  
                  int memberId = 0;
      iv2.addPointerReleasedListener(e -> {
		ServiceCompetence.getInstance().delete(c3.getId());
		Profile a=new Profile(parentForm, memberId);
                
        a.getF().show();
      
                 });
    ive.addPointerReleasedListener(e2 -> 
{
        new EditCompetence(form, c3).getF().show();
        });     
    
      iva.addPointerReleasedListener(e3 -> {
   new AjoutCompetence(memberId).getF().show();			
                 });

      
//c.addComponent(btn);
// c.add(i2);
  c.add(bt);
 c.add(mainContainer);
       }
            } catch (IOException ex) {
              System.out.println(ex.getMessage());
            


      //accr.getAllStyles().setPaddingTop(40);    
            //.setImage(enc);
 // Button btn =new Button("Edit");
  //  btn.addActionListener(e -> new EditCompetence().getF().show());      
     }
        


        




        

      return c;
    }
    
    private Container buildPairLabel(String label, String value){
        Container c = new Container(BoxLayout.x());
        
        Label labelLabel = new Label(label+"");
        labelLabel.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL));
        
        Label labelValue = new Label(value);
        labelValue.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL));
        
        c.add(labelLabel);
        c.add(labelValue);
        return c;
    }
    
    private Container buildTopProfileInfo(){
         Label profileImg;
        Container profileContainer = new Container();
        Button bProfile = new Button();
        profileContainer.setLeadComponent(bProfile);
    //  Photo ProfilePhoto = PhotoService.getInstance().getProfilePhoto(member.getId());
      //if(ProfilePhoto != null){
         //  EncodedImage enc = EncodedImage.createFromImage(MyApplication.theme.getImage("loading.png"), false);
        //  URLImage urlImage = URLImage.createToStorage(enc, (new Random()).nextInt()+"", ProfilePhoto.getPhotoUri());
        //   profileImg = new Label(urlImage);
            bProfile.addActionListener((e) -> {
             //  (new PhotoDetailsView(form, ProfilePhoto)).getForm().show();
            });
        //}else{
            Image i = MyApplication.theme.getImage("Profile.jpg");
           i = i.scaledHeight(250);
            profileImg = new Label(i);
       // }
       profileImg.getAllStyles().setMarginLeft(0);
        profileContainer.add(profileImg);
        
        Container profileSideContainer = new Container(BoxLayout.y());
        
        //Name
        Container nameAgeContainer = new Container(BoxLayout.x());
        nameAgeContainer.getAllStyles().setMarginLeft(5);
        
//   Label nameLabel = new Label(member.getNom()+""+member.getPrenom());
       Label nameLabel2 = new Label("Rihab Haddad");
       nameLabel2.getAllStyles().setPaddingTop(0);
       nameLabel2.getAllStyles().setPaddingRight(0);
       nameLabel2.getAllStyles().setPaddingBottom(0);
       nameLabel2.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
            nameAgeContainer.add(nameLabel2);

         //Label ageLabel = new Label("("+member.getAge()+")");
         Label ageLabel = new Label("(23)");
        ageLabel.getAllStyles().setPaddingTop(0);
        ageLabel.getAllStyles().setPaddingBottom(0);
        ageLabel.getAllStyles().setMarginRight(0);
        ageLabel.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM));
        nameAgeContainer.add(ageLabel);
        
        profileSideContainer.add(nameAgeContainer);
        
        
        //Since
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM, yyyy");
      //  Label sinceLabel = new Label("Memebre depuis: "+sdf.format(member.getCreated_at()));
       Label sinceLabel = new Label("Tunisie");
        sinceLabel.getAllStyles().setMarginLeft(20);
       sinceLabel.getAllStyles().setPaddingTop(0);
        sinceLabel.getAllStyles().setPaddingBottom(0);
       sinceLabel.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL));
       profileSideContainer.add(sinceLabel);
        
        Container profileAddressContainer = new Container(BoxLayout.x());
        profileAddressContainer.add(profileContainer);
        profileAddressContainer.add(profileSideContainer);
        
        return profileAddressContainer;
    }
     public Form getF() {
        return form;
    }

    public void setF(Form f) {
        this.form = f;
}
}
