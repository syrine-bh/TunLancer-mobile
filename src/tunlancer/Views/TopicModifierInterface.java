/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunlancer.Views;

import com.codename1.components.ImageViewer;
import com.codename1.io.Preferences;
import static com.codename1.push.PushContent.setTitle;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Image;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import tunlancer.Models.Topics;
import tunlancer.Services.ServiceTopic;

/**
 *
 * @author cyrinaa belguith
 */
public class TopicModifierInterface extends com.codename1.ui.Form{
    private Resources theme;
    private int user_id = 48;
    Image img, img1;
    static TextField tfIdM = new TextField();


    TopicModifierInterface(Topics g) {
         setTitle("Modifier");
        setLayout(BoxLayout.y());
        theme = UIManager.initFirstTheme("/theme2");
        //TextField titre = new TextField(null, "Titre");//textfield
        //TextField description = new TextField(null, "Description");//textfield
      
         Button savee = new Button("Enregistrer");
        
          
        TextField titre = new TextField(g.getTitre(), "Titre");//textfield
        TextField contenu = new TextField(g.getContenu(), "Description");//textfield
         TextField path = new TextField("");
        
        
        
        Button save = new Button("Enregistrer");
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Topics g = new Topics(titre.getText(),contenu.getText());
                if (ServiceTopic.getInstance().editTopic(g, Integer.parseInt(tfIdM.getText()))){
                    Dialog.show("Success", "Connection accepted", new Command("OK"));
                            Preferences.clearAll();
                                            new TopicInterface(theme).show();

                }
                
                else {
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        }
               // new ServiceTopic().editTopic(g, Integer.parseInt(tfIdM.getText()));

                
            }
        });
        
      setLayout(BoxLayout.y());
        addAll(titre,contenu,save);
      
         
        
        
        
//        this.getToolbar().addCommandToLeftBar("retour", theme.getImage("back-command.png"), ev->{
//               new TopicInterface(theme);
//            });
    }

    public TopicModifierInterface(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
    }
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.LayeredLayout());
        setInlineStylesTheme(resourceObjectInstance);
                setInlineStylesTheme(resourceObjectInstance);
        setTitle("ModifierInterface");
        setName("ModifierInterface");
    }// </editor-fold>
    
}
