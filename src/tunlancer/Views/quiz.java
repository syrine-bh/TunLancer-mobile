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
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
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
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import static com.codename1.ui.layouts.BorderLayout.SOUTH;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;
import tunlancer.Models.Concour;
import tunlancer.Models.Quiz;
import tunlancer.Models.Score;
import tunlancer.Models.Users;
import tunlancer.Models.questiontab;
import tunlancer.Models.reponsetab;
import tunlancer.Services.ServiceConcours;
import tunlancer.utils.UserSession;

/**
 *
 * @author Hiba
 */
public class quiz extends BaseForm {

    Form current;
    Resources theme;
    int score = 0;
    int Timer = 0;

    public quiz(Resources res, Quiz q, Concour c,Users u) {
        super("Concours", BoxLayout.y());
             ServiceConcours SC = new ServiceConcours();

        current = this;
        setTitle("Quiz");
        
        Timer = Timer++;
//       

        current = this;

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
        if (c.getIsVideo() == true) {
            addTab(swipe, res.getImage("video.jpg"), spacer1, "Trouvez l'emploi parfait que vous méritez.");
        } else {
            addTab(swipe, res.getImage("quiz.jpg"), spacer1, "Trouvez l'emploi parfait que vous méritez.");
        }
        
        swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();
        
        Component.setSameSize(spacer1, spacer2);
        add(LayeredLayout.encloseIn(swipe));
                        Label lbl = new Label("Veuillez choisir la bonne réponse");

        Button submit = new Button("Submit");
        submit.addActionListener(new ActionListener() {
//            @Override
            public void actionPerformed(ActionEvent evt) {
                UserSession us=new UserSession(u) ;
                Score scr = new Score();
               
                String strscore = String.valueOf(score);
                System.out.println("strscore= "+strscore);
                Users u =UserSession.instance.getU();
             
                scr.setPseudo(UserSession.instance.getU().getNom() + "-" + UserSession.instance.getU().getPrenom() + UserSession.instance.getU().getId());
                scr.setImage_profil(UserSession.instance.getU().getPhoto());
                scr.setEmail(UserSession.instance.getU().getEmail());
                scr.setQuiz_id(q);
                scr.setUser_id(u);
                System.out.println(q.getNom());
        
   System.out.println("user f score"+u);
                System.out.println("quiz f score"+q);
                        SC.ajoutScore(scr,strscore, q.getId(), u.getId());
                Dialog.show("Hi", "Form Submitted", new Command("OK"));
            }
        });
        
        for (questiontab qus : ServiceConcours.getInstance().getAllQuestions(q)) {
            if (q != null) {
                Container question = new Container(new BorderLayout());
                question.setUIID("Questions !");
                Label ques = new Label(qus.getQuestions());
                Container answers = new Container(BoxLayout.y());
                if (qus.getAnswer() != null) {
                    
                    for (reponsetab rep : ServiceConcours.getInstance().getReponses(qus)) {
                        System.out.println(ServiceConcours.getInstance().getReponses(qus));
                        CheckBox cb1 = new CheckBox(rep.getReponses());
                        cb1.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent evt) {
//                       if(cb1.isSelected()==true&&rep.getFlag()==true)
                                if (cb1.isSelected() == true && cb1.getText().toString().equals(qus.getAnswer().toString())) {
                                    System.out.println("la reponse sélectionnée " + cb1.getText().toString());
                                    System.out.println("La réponse correcte " + qus.getAnswer().toString());
                                    score = score + 1;                                    
                                }
                                System.out.println("score= " + score);
                                
                            }
                        });
                        
                        answers.add(cb1);
                    }
                    
                }
                question.add(CENTER, ques);
                question.add(SOUTH, answers);
                
                add(question);
            }
        }
        add(submit);
        
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
