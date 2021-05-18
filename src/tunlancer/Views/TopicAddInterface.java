/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunlancer.Views;

import com.codename1.capture.Capture;
import com.codename1.components.ImageViewer;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.LEFT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.Validator;
import java.io.IOException;
import java.util.ArrayList;
import tunlancer.Models.Topics;
import tunlancer.Services.ServiceTopic;

/**
 *
 * @author cyrinaa belguith
 */
public class TopicAddInterface extends com.codename1.ui.Form{
    private Resources theme2;
    private int user_id = 48;
    private ArrayList<Topics> list_m = new ServiceTopic().getAllTopics();
    String fileNameInServer;
    public String imgPath;
    private Image img;
    
    
    public TopicAddInterface(){
        setTitle("Ajouter les publications ");
        setLayout(BoxLayout.y());
        theme2 = UIManager.initFirstTheme("/MembreGroupes");
        
        //Tabs : toolbar
        Tabs tab = new Tabs();
        UIBuilder ui = new UIBuilder();
        Container cnt1 = ui.createContainer(theme2, "GUI 1");//ajouter graphiquement un GUI element
        
        tab.addTab("Nouveau Topic", cnt1);
        
        add(tab);
        //cnt1.add(new SpanLabel(new ServiceGroupeMembre().Afficher().toString()));
        ArrayList<Topics> list_gm = new ServiceTopic().getAllTopics();
   
        
        
        //ajouter un groupe
        TextField titre = new TextField(null, "Titre");//textfield
        TextField description = new TextField(null, "Description");//textfield
    
   
         Validator val = new Validator();
            val.addConstraint(titre, new LengthConstraint(1));
            val.addConstraint(description, new LengthConstraint(1));
       Button upload = new Button("upload");
       Button picture = new Button("parcourir");
        ImageViewer iv = new ImageViewer();
       picture.setMaterialIcon(FontImage.MATERIAL_CLOUD_UPLOAD);
       picture.addPointerReleasedListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try{
                    imgPath = Capture.capturePhoto();
                    System.out.println("ken haka" + imgPath);
                    String link =imgPath.toString();
                    int pod = link.indexOf("/", 2);
                    String news = link.substring(pod + 2, link.length());
                    System.out.println("ye5i wala haka" + news);
                    img = Image.createImage(imgPath);
                    
                    iv.setImage(img);
                   
                    //FileUploader fu = new FileUploader("http://localhost/PIDEV_Web/Nozelites/web");
                    
                    
                    //fileNameInServer = fu.upload(news);
                    
                  
                    
                    
                } catch (IOException ex){
                    ex.printStackTrace();
                } catch (Exception ex) {
                    
                }
            }
        });

       
       
       
       
        Button save = new Button("Enregistrer");
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //ajouter groupe
                ServiceTopic sg = new ServiceTopic();
                
                if ( val.isValid()==false ) {
                    
                 
                    
                     Dialog.show("Erreur", val.getErrorMessage(titre) + " and "+ val.getErrorMessage(description)  , "OK", "Cancel");
                    
                } else {
                    sg.addTopic(new Topics(titre.getText(), description.getText()));
                //sg.ajouter(new Topics(titre.getText(), description.getText());
                showToast("Votre Topic est ajouté avec succées ");
                InfiniteProgress bar = new InfiniteProgress();
                    Showbar(bar);
                
                }
               
                
                new TopicInterface(theme2).show();
                
            }
        });
        
        cnt1.setLayout(BoxLayout.y());
         
  
      
        cnt1.add(titre).add(description).add(picture).add(save).add(iv);
        
        
        
      
        
        //retour btn
        this.getToolbar().addCommandToLeftBar("Retour", null, (evt) -> {
           // new MembreGroupesInterface().show();
        });
    }
     private void showToast(String text) {
        Image errorImage = FontImage.createMaterial(FontImage.MATERIAL_ADD_ALARM, UIManager.getInstance().getComponentStyle("Title"), 4);
        ToastBar.Status status = ToastBar.getInstance().createStatus();
        status.setMessage(text);
        status.showDelayed(LEFT);
        status.setIcon(errorImage);
        status.setExpires(2000);
        status.show();
    }
     
     private void Showbar (InfiniteProgress b){
          b.showInfiniteBlocking();
      }
    
}
