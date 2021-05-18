/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunlancer.Views;

import com.codename1.capture.Capture;
import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.components.ScaleImageLabel;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.io.Util;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import tunlancer.Models.Replies;
import tunlancer.Models.Topics;
import tunlancer.Services.ServiceReplies;
import tunlancer.Services.ServiceTopic;

/**
 *
 * @author cyrinaa belguith
 */
public class RepliesInterface extends BaseForm{
        public static int id ;




   public RepliesInterface(Resources res){
        setName("liste des commentaires");
        setTitle("liste des Commentaires");
        this.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_EVENT_AVAILABLE, s);
        FontImage icons = FontImage.createMaterial(FontImage.MATERIAL_EVENT_NOTE, s);
//        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> {
//           TopicInterface le = new TopicInterface(res);
//           le.show();
//        });
//           getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_ADD, e -> {
//          AddReply.id = id ;
//          AddReply ac = new AddReply();
//          ac.show();
//        });
//
//        getToolbar().addCommandToRightSideMenu("Ajouter topic", icon, (e) -> {
//          AddTopicForm ae = new AddTopicForm(res);
//
//        });
//         getToolbar().addCommandToRightSideMenu("Liste des topics", icon, (e) -> {
//
//           TopicInterface le = new TopicInterface(res);
//        });




             ServiceReplies sr = new ServiceReplies();
int idr = 4;
    Map x = sr.getResponse("displayTopic/"+idr);
    ArrayList<Replies> list = sr.getListsComments(x);
             for (Replies e : list) {
            Label a = new Label("Commentaire : "+e.getContenu());
        }
        show();
    }
   }


