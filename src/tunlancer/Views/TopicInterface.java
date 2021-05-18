/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunlancer.Views;

import com.codename1.components.ImageViewer;
import com.codename1.components.ToastBar;
import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.AutoCompleteTextField;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import static com.codename1.ui.ComponentSelector.$;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import java.io.IOException;
import java.util.ArrayList;
import tunlancer.Models.Topics;
import tunlancer.Services.ServiceTopic;
import tunlancer.utils.ScrollBar;
/**
 *
 * @author cyrinaa belguith
 */
public class TopicInterface extends BaseForm{
         Form current;
 
    private Resources theme;
    private int user_id = 48;
    Topics pu = null;
    String searchString ; 
    ImageViewer iv = new ImageViewer();
    private Image img;
    String  param ; 
    public TopicInterface(Resources res)  {
        //this(com.codename1.ui.util.Resources.getGlobalResources());
        
       setTitle("Topics");
        setLayout(BoxLayout.y());
        
        //Tabs : toolbar
        Tabs tab = new Tabs();
        UIBuilder ui = new UIBuilder();
        Container cnt1 = new Container(BoxLayout.y());//ajouter graphiquement un GUI element
         
        Container cnt2 = new Container(BoxLayout.y());//ajouter graphiquement un GUI element
        Component scrollableCnt = makeScrollable(cnt1);
        Component scrollableCnt2 = makeScrollable(cnt2);
      
        tab.addTab("Forum", scrollableCnt);
        
      
        
       // hi.add(scrollableCnt);
        
    final DefaultListModel<String> list = new DefaultListModel<>();
    ArrayList<Topics> liste = new ServiceTopic().getAllTopics();
        for(Topics g: liste)
                 { 
                    
                    list.addItem(g.getTitre());
                              


                 }
        
  
        AutoCompleteTextField Textbarre = new AutoCompleteTextField(list);
        Textbarre.setMinimumElementsShownInPopup(5);
         
  

        
        Button ajouterBtn = new Button("Créer un topic");
         Button chercher = new Button("chercher");
        
         chercher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    // Media m =(Media) MediaManager.getAudioBuffer("a.mp3");
                    Media m =  MediaManager.createMedia("sound-effects-button.mp3", focusScrolling);
                    m.play();
                } catch (IOException ex) {
                }
               
                  //new PublicationChercherInterface(Textbarre.getText()).show();
               
                  
                    

                
      
                
                
             
            }
        });
        
           ajouterBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                 
                new AddTopicForm(res).show();
                
            }
        });
       
        this.add(tab).add(ajouterBtn);
        
     ArrayList<Topics> list_e = new ServiceTopic().getAllTopics();
                         

        cnt1.setLayout(BoxLayout.y());

        cnt1.add(Textbarre).add(chercher);
        for(Topics gmi : list_e )
        
                      //cnt1.add(addItemGroupe(gmi));
        cnt1.add(addItemGroupe(gmi));
             
                            
                                           
       
       
    }
     public Container addItemGroupe(Topics g){//pour remplir la liste
        Container cn1=new Container(new BorderLayout());
        Container cn2=new Container(BoxLayout.y());
        Container cn3=new Container(BoxLayout.x());
       // Component scrollableCnt = makeScrollable(cn1);
        Container cnt = new Container(BoxLayout.y());

       
       
        Label lab=new Label("Titre:       "+ g.getTitre());
        Label lab2=new Label("Description:       " +g.getContenu());
      
        
        System.out.println("topic id ==" + g.getId());
          Button Commenter = new Button("commenter");
          Commenter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                

                new AddReply(theme).show();
            }
          });
        
        Button Dislike = new Button();
        Dislike.setMaterialIcon(FontImage.MATERIAL_DANGEROUS);
        Dislike.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                  ArrayList<Topics> list_gm = new ServiceTopic().getAllTopics();
                ServiceTopic sgm = new ServiceTopic();
                for(Topics gmi : list_gm)
                    if(gmi.getId()==g.getId())
                        sgm.supprimer(gmi);
                //new ServiceTopic().dislike(g);
                new TopicInterface(theme).show();
                System.out.println("Topic supprimé");
            }
        });
//        Button Like = new Button();
//        Like.setMaterialIcon(FontImage.MATERIAL_FAVORITE);
//        Like.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent evt) {
//              //  cacher(Like);
//                ArrayList<Topics> list_gm = new ServiceTopic().getAllTopics();
//                ServiceTopic sgm = new ServiceTopic();
//                for(Topics gmi : list_gm)
//                    if(gmi.getId()==g.getId())
//                        sgm.supprimer(gmi);
//                //new ServicePublication().jaime(g);
//                new TopicInterface(theme).show();
//                System.out.println("ajouté au favoris");
//               // cn3.add(Dislike);
//                
//            }
//        });
       
        
        Button supprimerBtn = new Button("supprimer");
        supprimerBtn.setMaterialIcon(FontImage.MATERIAL_DELETE);
        
       
        supprimerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ArrayList<Topics> list_gm = new ServiceTopic().getAllTopics();
                ServiceTopic sgm = new ServiceTopic();
                for(Topics gmi : list_gm)
                    if(gmi.getId()==g.getId())
                        sgm.supprimer(gmi);
                new ServiceTopic().supprimer(g);
                new TopicInterface(theme).show();
            }
        });
         
        Button modifierBtn = new Button("modifier");
        modifierBtn.setMaterialIcon(FontImage.MATERIAL_UPDATE);

          modifierBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               
                new TopicModifierInterface(g).show();
            }
        });
      
        
        
        
//        ImageViewer imgv=new ImageViewer(theme.getImage("groupes.png"));
        cn2.add(lab).add(lab2);
        
        //selon user connecté +dislike
        cn3.add(modifierBtn);
//      if (g.getId() == Session.id_Session)
//      {
//        cn3.add(supprimerBtn).add(modifierBtn);
//      }
      
     
        cn3.add(Dislike).add(Commenter);

        
        //cn1.add(BorderLayout.CENTER,v );
        cn1.add(BorderLayout.NORTH,cn2);
        cn1.add(BorderLayout.SOUTH,cn3);
        

        
      //  cn1.add(modifierBtn);
        
          
  
  
        return cn1;
                
    }
       private void showToast(String text) {
        Image errorImage = FontImage.createMaterial(FontImage.MATERIAL_ERROR, UIManager.getInstance().getComponentStyle("Title"), 4);
        ToastBar.Status status = ToastBar.getInstance().createStatus();
        status.setMessage(text);
        status.setIcon(errorImage);
        status.setExpires(2000);
        status.show();
    }
       public static Component makeScrollable(final Component scrollable) {
        if(!Display.getInstance().isDesktop()) {
            return scrollable;
        }
        if (!(scrollable instanceof Container)) {
            return scrollable;
        }
        ScrollBar scroll = new ScrollBar((Container)scrollable, ScrollBar.Y_AXIS);
        Container sc = BorderLayout.center(scrollable).
                add(BorderLayout.EAST, scroll);
        $(sc).selectAllStyles().setBgColor(0xffffff).setBgTransparency(255);
        return sc;
    }
          private void cacher(Button B) {
              B.setHidden(true);
          }
           private void apparaitre(Button B) {
              B.setHidden(true);
          } 
   
 

    
}
