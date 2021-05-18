/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunlancer.Views;

import ca.weblite.codename1.components.ckeditor.CKeditor;
import com.codename1.components.InfiniteProgress;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;
import tunlancer.Models.Replies;
import tunlancer.Models.Topics;
import tunlancer.Services.ServiceReplies;
import static tunlancer.Views.TopicsForm.res;

/**
 *
 * @author cyrinaa belguith
 */
public class AddReply extends BaseForm{
    
    private Resources theme;

    public static int id ; 
    
        Topics topics;

    
    public AddReply(Resources res){
        setName("Ajouter commentaire");
        setTitle("Ajouter commentaire");
        this.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        Style s = UIManager.getInstance().getComponentStyle("");
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_EVENT_AVAILABLE, s);
        FontImage icone = FontImage.createMaterial(FontImage.MATERIAL_IMAGE, s);
        FontImage icons = FontImage.createMaterial(FontImage.MATERIAL_EVENT_NOTE, s);
        
//        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> {
//               RepliesInterface.id = id ; 
//            RepliesInterface lc = new RepliesInterface(res);
//        });
//        getToolbar().addCommandToRightSideMenu("Ajouter topic", icon, (e) -> {
//          AddTopicForm ae = new AddTopicForm(res);
//           
//        });
//         getToolbar().addCommandToRightSideMenu("Liste des topics", icon, (e) -> {
//           
//           TopicInterface le = new TopicInterface(res);
//        });
            

            Container photos = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            TextField contenu = new TextField("");
            contenu.setUIID("TextFieldBlack");
            contenu.setHint("Commentaire");         
            Button b = new Button("Ajouter");
            //ServiceReplies ws = new ServiceReplies();
            photos.add(contenu);
            photos.add(b);
            add(photos);
            b.addActionListener((ActionEvent ev)->{
               
                InfiniteProgress ip = new InfiniteProgress(); //loading after insert data
                final Dialog iDialog = ip.showInfiniteBlocking();
                Replies c = new Replies();
                c.setContenu(contenu.getText());
                c.setUser_id(48);
                c.setTopic_id(4);
                System.out.println("data =======" +c);
                ServiceReplies.getInstance().ajoutCommentaire(c);
                iDialog.dispose();
                refreshTheme();

                //ws.ajoutCommentaire(c);
                
                //RepliesInterface.id = id;
                //RepliesInterface lc = new RepliesInterface(res);
                //lc.show();
                });
        show();
        
    }
    
    
}
