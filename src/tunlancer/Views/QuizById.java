/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunlancer.Views;

import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.LEFT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;
import java.util.Date;
import tunlancer.Models.Concour;
import tunlancer.Models.Quiz;
import tunlancer.Models.Users;
import tunlancer.Services.ServiceConcours;
import tunlancer.utils.UserSession;

/**
 *
 * @author Hiba
 */
public class QuizById extends BaseForm {
     ServiceConcours SC;
    ArrayList<Concour> concours;
    ArrayList<Quiz> quiz;
    Form current;
        Date t1 = new Date(System.currentTimeMillis());


    public QuizById(Quiz q, Form previous, Resources res,Concour c,Users u) {
        super("Quiz", BoxLayout.y());
        current = this;

        concours = new ArrayList<>();
        quiz=new ArrayList<>();
        SC = new ServiceConcours();
        concours = (ArrayList<Concour>) SC.getAllConcours();
        quiz=(ArrayList<Quiz>) SC.getAllQuiz();
                System.out.println("hedhi list quiz "+quiz);

        System.out.println(q);
//
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Publications");
        getContentPane().setScrollVisible(false);

        super.addSideMenu(res);
        tb.addSearchCommand(e -> {
        });

        Tabs swipe = new Tabs();

        Label spacer1 = new Label();
        Label spacer2 = new Label();
// if(c.getIsVideo()==true){
//        addTab(swipe, res.getImage("video.jpg"), spacer1, "Trouvez l'emploi parfait que vous méritez.");}else{
        addTab(swipe, res.getImage("quiz.jpg"), spacer1, "Trouvez l'emploi parfait que vous méritez.");
 
        swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();

        Component.setSameSize(spacer1, spacer2);
        add(LayeredLayout.encloseIn(swipe));

       setTitle("Quiz");
//        setLayout(BoxLayout.y());
//        addSideMenu(res);
//        getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_PUBLIC, e -> {
//        });
//        for(elearning_session es:ServiceFormation.getInstance().getAllTasks())
//        {  if(es.getUser_id()==fos.getId()){

        Button Participate = new Button("Participate", "LoginButton");
        Participate.getAllStyles().setBgColor(0x246A73);
                                  Button btnListQuiz = new Button("List Quiz");

        Participate.getAllStyles().setBgColor(0x246A73);
        Participate.addActionListener(e -> {
            System.out.println(c.getId());
            System.out.println(UserSession.instance.getU().getId());
//                      if(SC.alreadyParticipated(c.getId(), UserSession.instance.getU().getId())==true){
//                Dialog d1Dialog = new Dialog("Already Participated!");
//                d1Dialog.showPopupDialog(Participate);
//                }
//                      else if(c.getIsVideo()==true) { 
//                          new NewParticipationForm(res, c, this).show();
//
//                      }
//                     else{
                            new quiz(res, q, c, u).show();

//                      }
        
//        }}
//        Button btn=new Button("Liste des Reponses");
//        btn.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent evt) {
//                new Listeanswers(theme).show();
//            }
        });
        add(Participate);

            
                

        }
    

    private void updateArrowPosition(Button b, Label arrow) {
        arrow.getUnselectedStyle().setMargin(LEFT, b.getX() + b.getWidth() / 2 - arrow.getWidth() / 2);
        arrow.getParent().repaint();

    }
//

    private void addTab(Tabs swipe, Image img, Label spacer, String text) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        if (img.getHeight() < size) {
            img = img.scaledHeight(size);
        }

        if (img.getHeight() > Display.getInstance().getDisplayHeight() / 2) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        }
        ScaleImageLabel image = new ScaleImageLabel(img);
        image.setUIID("Container");
        image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        Label overlay = new Label(" ", "ImageOverlay");

        Container page1
                = LayeredLayout.encloseIn(
                        image,
                        overlay,
                        BorderLayout.south(
                                BoxLayout.encloseY(
                                        new SpanLabel(text, "LargeWhiteText"),
                                        spacer
                                )
                        )
                );

        swipe.addTab("", page1);
    }

    private void addConcoursToForm(Image img, String nom) {
        int height = Display.getInstance().convertToPixels(11.5f);
        int width = Display.getInstance().convertToPixels(14f);
        Button image = new Button(img.fill(width, height));
        image.setUIID("Label");
        Container cnt = BorderLayout.west(image);
        cnt.setLeadComponent(image);
        TextArea ta = new TextArea(nom);
        ta.setUIID("NewsTopLine");
        ta.setEditable(false);


        cnt.add(BorderLayout.CENTER,
                BoxLayout.encloseY(
                        ta
                ));
        add(cnt);
        image.addActionListener(e -> ToastBar.showMessage(nom, FontImage.MATERIAL_INFO));

    }

    private void bindButtonSelection(Button b, Label arrow) {
        b.addActionListener(e -> {
            if (b.isSelected()) {
                updateArrowPosition(b, arrow);
            }
        });
    }
}
