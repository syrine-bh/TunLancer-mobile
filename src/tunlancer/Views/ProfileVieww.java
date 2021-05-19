package tunlancer.Views;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.SpanLabel;
import com.codename1.db.Cursor;
import com.codename1.db.Database;
import com.codename1.db.Row;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;

import java.io.IOException;
import java.util.Random;
import tunlancer.Models.Photo;
import tunlancer.Models.Users;
import tunlancer.MyApplication;
import tunlancer.Services.PhotoService;
import tunlancer.services.ServiceUser;

public class ProfileVieww {
    private Form form;
    private Form parentForm;
    private Users member;
    
    private static ProfileVieww instance;
    
    public static void update(){
        if(instance != null)
            instance.updateView();
    }
    
    private void updateView(){
        Dialog i = new InfiniteProgress().showInifiniteBlocking();
      //  member = ServiceUser.getInstance().getUsers(member.getId());
        buildContainer();
        form.revalidate();
        i.dispose();
    }
    
    public ProfileVieww(Form parentForm, int memberId){
        Dialog ip = new InfiniteProgress().showInifiniteBlocking();
        instance = this;
        this.parentForm = parentForm;
        form = new Form("Profile", new BorderLayout());
//        this.member = ServiceUser.getInstance().getUsers(memberId);
        
        if(member == null){
            getMemberFromLocal();
        }else{
            populateBd();
            form.getToolbar().addCommandToOverflowMenu("Edit", MyApplication.theme.getImage("edit_black.png"), (e) -> {
                (new EditProfil(form, member)).getF().show();
            });
           // form.getToolbar().addCommandToOverflowMenu("Likes", MyApplication.theme.getImage("like.png"), (e) -> {
                //(new LikesListView(form, member.getId())).getForm().show();
           // });
          //  form.getToolbar().addCommandToOverflowMenu("Photos", MyApplication.theme.getImage("photos.png"), (e) -> {
              //  (new PhotosListView(form)).getForm().show();
           // });
        }
        form.getToolbar().addCommandToLeftBar("Back", MyApplication.theme.getImage("back-command.png"), (e) -> {
            parentForm.showBack();
        });
        buildContainer();
        ip.dispose();
    }
    
    private void populateBd(){
        try {
            Database database = Database.openOrCreate("tunlancer_bd");
            database.execute("create table if not exists Users(id INTEGER, Nom text, Prenom text,"
                    + "Tel int, email text , password text,"
                    + "pays text, role text, photo text, bibiliography text, is_enabled int, age int,"
                    + "sexe string);");
            String deleteQuery = "delete from Users";
            database.execute(deleteQuery);
            String insertQuery = "insert into Users values("+member.getId()+", '"+member.getNom()+"', '"+member.getPrenom()
                    +"', '"+member.getTel()+"', "+(member.getEmail()+", '"+"', "+member.getPays()
                    +", "+member.getRole()+", "+member.getPassword()+", "+member.getPhoto()+", "+member.getBibliography()
                    +", "+member.getAge()+", "+member.getSexe()+", "+(member.getis_Enabled())
                    )+"')";
            database.execute(insertQuery);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    private void getMemberFromLocal(){
        try {
            Database database = Database.openOrCreate("tunlancer_bd");
      Cursor c = database.executeQuery("select * from users");
           // c.next();
           // Row r = c.getRow();
          //  member = new Users();
          //  member.setId(r.getInteger(0));
           //member.setNom(r.getString(1));
          //  member.setPrenom(r.getString(2));
           //   member.setTel(r.getInteger(3));
           //  member.setEmail(r.getString(4));
          //  member.setPays(r.getString(5));
          //  member.setRole(r.getString(6));
          //  member.setGender(r.getShort(4)==1?true:false);
           
          
            
           

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
//        Photo coverPhoto = PhotoService.getInstance().getCoverPhoto(member.getId());
       // if(coverPhoto != null){
         //   EncodedImage enc = EncodedImage.createFromImage(MyApplication.theme.getImage("loading_cover.png"), false);
          //  URLImage urlImage = URLImage.createToStorage(enc, (new Random()).nextInt()+"", coverPhoto.getPhotoUri());
           // coverImg = new Label(urlImage);
           // bCover.addActionListener((e) -> {
               // (new PhotoDetailsView(form, coverPhoto)).getForm().show();
          //  });
       // }else{
            Image i = MyApplication.theme.getImage("default_banner.png");
            i = i.scaledHeight(200);
            coverImg = new Label(i);
       // }
      //  coverImg.getAllStyles().setMarginLeft(0);
      //  coverContainer.add(coverImg);
        
        //About
        Container aboutContainer = new Container(BoxLayout.y());
        aboutContainer.getAllStyles().setMarginLeft(40);
        
        Label aboutLabel = new Label("About");
        aboutLabel.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
        
//        SpanLabel aboutSpan = new SpanLabel(member.getBibliography());
       // aboutSpan.getAllStyles().setMarginLeft(20);
       // aboutSpan.getTextAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL));
        
        aboutContainer.add(aboutLabel);
       // aboutContainer.add(aboutSpan);
        
        c.add(coverContainer);
//        c.add(buildTopProfileInfo());
        c.add(buildProfileInfo());
        c.add(aboutContainer);
        
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
        
        Label infoLabel = new Label("Info");
        infoLabel.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
        
        Container mainContainer = new Container(BoxLayout.y());
        mainContainer.getAllStyles().setMarginLeft(30);
//        mainContainer.add(buildPairLabel("Sexe", member.getSexe()?"Male":"Female"));
       // mainContainer.add(buildPairLabel("Birthdate", (new SimpleDateFormat("dd MMMM, yyy").format(member.getBirthDate()))));
      //  mainContainer.add(buildPairLabel("Pays", member.getPays()+""));
      //  mainContainer.add(buildPairLabel("Age", member.getAge()+""));
       // mainContainer.add(buildPairLabel("Tel", member.getPays()+""));
     
        
        c.add(infoLabel);
        c.add(mainContainer);
        return c;
    }
    
    private Container buildPairLabel(String label, String value){
        Container c = new Container(BoxLayout.x());
        
        Label labelLabel = new Label(label+":");
        labelLabel.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL));
        
        Label labelValue = new Label(value);
        labelValue.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL));
        
        c.add(labelLabel);
        c.add(labelValue);
        return c;
    }
    
    private Container buildTopProfileInfo(){
        //Profile Picture
        Label profileImg;
        Container profileContainer = new Container();
        Button bProfile = new Button();
        profileContainer.setLeadComponent(bProfile);
//        Photo ProfilePhoto = PhotoService.getInstance().getProfilePhoto(member.getId());
       // if(ProfilePhoto != null){
          //  EncodedImage enc = EncodedImage.createFromImage(MyApplication.theme.getImage("loading.png"), false);
          //  URLImage urlImage = URLImage.createToStorage(enc, (new Random()).nextInt()+"", ProfilePhoto.getPhotoUri());
           // profileImg = new Label(urlImage);
          //  bProfile.addActionListener((e) -> {
                //(new PhotoDetailsView(form, ProfilePhoto)).getForm().show();
         //   });
      //  }else{
            Image i = MyApplication.theme.getImage("default.png");
            i = i.scaledHeight(70);
            profileImg = new Label(i);
        //}
      //  profileImg.getAllStyles().setMarginLeft(0);
       // profileContainer.add(profileImg);
        
        Container profileSideContainer = new Container(BoxLayout.y());
        
        //Name
        Container nameAgeContainer = new Container(BoxLayout.x());
        nameAgeContainer.getAllStyles().setMarginLeft(5);
        
//        Label nameLabel = new Label(member.getNom()+" "+member.getPrenom());
        //nameLabel.getAllStyles().setPaddingTop(0);
       // nameLabel.getAllStyles().setPaddingRight(0);
       // nameLabel.getAllStyles().setPaddingBottom(0);
       // nameLabel.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
       // nameAgeContainer.add(nameLabel);
        //Age
       // Label ageLabel = new Label("("+member.getAge()+")");
       // ageLabel.getAllStyles().setPaddingTop(0);
       // ageLabel.getAllStyles().setPaddingBottom(0);
      //  ageLabel.getAllStyles().setMarginRight(0);
       // ageLabel.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM));
       // nameAgeContainer.add(ageLabel);
        
        profileSideContainer.add(nameAgeContainer);
        return null;
        
      
        
       
    }
}
